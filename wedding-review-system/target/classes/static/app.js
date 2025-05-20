const API_URL = 'http://localhost:8080/api/reviews';

// DOM Elements
const reviewForm = document.getElementById('reviewForm');
const reviewsList = document.getElementById('reviewsList');

// Create delete confirmation modal
const deleteModal = document.createElement('div');
deleteModal.className = 'modal';
deleteModal.innerHTML = `
    <div class="modal-content">
        <h3>Confirm Delete</h3>
        <p>Are you sure you want to delete this review?</p>
        <div class="modal-actions">
            <button class="btn-cancel">Cancel</button>
            <button class="btn-confirm-delete">Delete</button>
        </div>
    </div>
`;
document.body.appendChild(deleteModal);

// Initialize star rating if on create-review page
if (document.querySelector('.star-rating')) {
    initializeStarRating();
    // Check for editing mode
    checkEditMode();
}

// Load reviews when on index page
if (reviewsList) {
    document.addEventListener('DOMContentLoaded', loadReviews);
}

// Star Rating Initialization
function initializeStarRating() {
    const stars = document.querySelectorAll('.star-rating i');
    const starsInput = document.getElementById('stars');

    stars.forEach(star => {
        star.addEventListener('click', () => {
            const rating = star.getAttribute('data-rating');
            starsInput.value = rating;
            
            // Update star display
            stars.forEach(s => {
                const sRating = s.getAttribute('data-rating');
                if (sRating <= rating) {
                    s.classList.remove('far');
                    s.classList.add('fas');
                    s.classList.add('active');
                } else {
                    s.classList.remove('fas');
                    s.classList.add('far');
                    s.classList.remove('active');
                }
            });
        });

        // Hover effects
        star.addEventListener('mouseover', () => {
            const rating = star.getAttribute('data-rating');
            stars.forEach(s => {
                const sRating = s.getAttribute('data-rating');
                if (sRating <= rating) {
                    s.classList.remove('far');
                    s.classList.add('fas');
                } else {
                    s.classList.remove('fas');
                    s.classList.add('far');
                }
            });
        });

        star.addEventListener('mouseout', () => {
            const currentRating = starsInput.value;
            stars.forEach(s => {
                const sRating = s.getAttribute('data-rating');
                if (sRating <= currentRating) {
                    s.classList.remove('far');
                    s.classList.add('fas');
                } else {
                    s.classList.remove('fas');
                    s.classList.add('far');
                }
            });
        });
    });
}

// Form submit handler
if (reviewForm) {
    reviewForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const editingReviewId = sessionStorage.getItem('editingReviewId');
        const review = {
            vendorName: document.getElementById('vendorName').value,
            activityType: document.getElementById('activityType').value,
            stars: parseInt(document.getElementById('stars').value),
            description: document.getElementById('description').value
        };

        try {
            let response;
            if (editingReviewId) {
                // Update existing review
                response = await fetch(`${API_URL}/${editingReviewId}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(review)
                });
            } else {
                // Create new review
                response = await fetch(API_URL, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(review)
                });
            }

            if (response.ok) {
                // Clear editing state
                sessionStorage.removeItem('editingReviewId');
                sessionStorage.removeItem('editingReview');
                window.location.href = 'index.html';
            } else {
                const errorData = await response.json();
                alert(editingReviewId ? 
                    `Failed to update review: ${errorData.message || 'Unknown error'}` : 
                    `Failed to create review: ${errorData.message || 'Unknown error'}`);
            }
        } catch (error) {
            console.error('Error:', error);
            alert(editingReviewId ? 'Failed to update review' : 'Failed to create review');
        }
    });
}

// Load all reviews
async function loadReviews() {
    try {
        const response = await fetch(API_URL);
        const reviews = await response.json();
        displayReviews(reviews);
    } catch (error) {
        console.error('Error:', error);
        reviewsList.innerHTML = '<p class="text-danger">Failed to load reviews</p>';
    }
}

// Display reviews in the list
function displayReviews(reviews) {
    reviewsList.innerHTML = '';
    
    reviews.forEach(review => {
        const reviewElement = createReviewElement(review);
        reviewsList.appendChild(reviewElement);
    });
}

// Create review element
function createReviewElement(review) {
    const div = document.createElement('div');
    div.className = 'review-card';
    
    const stars = '★'.repeat(review.stars) + '☆'.repeat(5 - review.stars);
    
    div.innerHTML = `
        <div class="review-header">
            <div>
                <span class="review-vendor">${review.vendorName}</span>
                <span class="review-activity"> - ${review.activityType}</span>
            </div>
            <div class="review-stars">${stars}</div>
        </div>
        <div class="review-description">${review.description}</div>
        <div class="review-date">
            Created: ${new Date(review.createdAt).toLocaleString()}
        </div>
        <div class="review-actions">
            <button class="btn-edit" onclick="editReview('${review.id}')">Edit</button>
            <button class="btn-delete" onclick="deleteReview('${review.id}')">Delete</button>
        </div>
    `;
    
    return div;
}

// Edit review
async function editReview(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        if (!response.ok) {
            throw new Error('Failed to fetch review');
        }
        const review = await response.json();
        
        // Store review ID and data in session storage
        sessionStorage.setItem('editingReviewId', id);
        sessionStorage.setItem('editingReview', JSON.stringify(review));
        
        // Redirect to create-review page
        window.location.href = 'create-review.html';
    } catch (error) {
        console.error('Error:', error);
        alert('Failed to load review for editing');
    }
}

// Delete review
async function deleteReview(id) {
    const modal = document.querySelector('.modal');
    const confirmBtn = modal.querySelector('.btn-confirm-delete');
    const cancelBtn = modal.querySelector('.btn-cancel');
    
    // Show modal
    modal.style.display = 'flex';
    
    // Handle confirm delete
    const handleConfirm = async () => {
        try {
            const response = await fetch(`${API_URL}/${id}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                loadReviews();
            } else {
                alert('Failed to delete review');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Failed to delete review');
        } finally {
            // Hide modal
            modal.style.display = 'none';
            // Clean up event listeners
            confirmBtn.removeEventListener('click', handleConfirm);
            cancelBtn.removeEventListener('click', handleCancel);
        }
    };

    // Handle cancel
    const handleCancel = () => {
        modal.style.display = 'none';
        // Clean up event listeners
        confirmBtn.removeEventListener('click', handleConfirm);
        cancelBtn.removeEventListener('click', handleCancel);
    };

    // Add event listeners
    confirmBtn.addEventListener('click', handleConfirm);
    cancelBtn.addEventListener('click', handleCancel);
}

// Check for editing mode
function checkEditMode() {
    const editingReviewId = sessionStorage.getItem('editingReviewId');
    const editingReview = sessionStorage.getItem('editingReview');
    
    if (editingReviewId && editingReview) {
        try {
            const review = JSON.parse(editingReview);
            
            // Update page title
            document.querySelector('h1').textContent = 'Edit Review';
            
            // Populate form
            document.getElementById('vendorName').value = review.vendorName || '';
            document.getElementById('activityType').value = review.activityType || 'VENUE';
            document.getElementById('description').value = review.description || '';
            document.getElementById('stars').value = review.stars || 0;
            
            // Set star rating
            const stars = document.querySelectorAll('.star-rating i');
            stars.forEach(star => {
                const rating = star.getAttribute('data-rating');
                if (rating <= review.stars) {
                    star.classList.remove('far');
                    star.classList.add('fas');
                    star.classList.add('active');
                } else {
                    star.classList.remove('fas', 'active');
                    star.classList.add('far');
                }
            });
        } catch (error) {
            console.error('Error parsing review data:', error);
            // Clear invalid data
            sessionStorage.removeItem('editingReviewId');
            sessionStorage.removeItem('editingReview');
            resetForm();
        }
    } else {
        resetForm();
    }
}

// Reset form to initial state
function resetForm() {
    document.querySelector('h1').textContent = 'Create New Review';
    document.getElementById('vendorName').value = '';
    document.getElementById('activityType').value = 'VENUE';
    document.getElementById('description').value = '';
    document.getElementById('stars').value = '0';
    
    // Reset star display
    const stars = document.querySelectorAll('.star-rating i');
    stars.forEach(star => {
        star.classList.remove('fas', 'active');
        star.classList.add('far');
    });
} 