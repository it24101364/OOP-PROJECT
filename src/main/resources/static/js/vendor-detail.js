/**
 * Vendor Detail Page JavaScript
 */
document.addEventListener('DOMContentLoaded', function() {
    // Generate star ratings for all elements with the rating-stars class
    generateStarRatings();

    // Initialize image gallery functionality
    initializeGallery();

    // Set up booking modal interactions
    setupBookingModal();
});

/**
 * Generate star ratings for all elements with the rating-stars class
 * Uses the data-rating attribute to determine the rating value
 */
function generateStarRatings() {
    const ratingElements = document.querySelectorAll('.rating-stars');

    ratingElements.forEach(element => {
        const rating = parseFloat(element.getAttribute('data-rating')) || 0;
        element.innerHTML = createStarRating(rating);
    });
}

/**
 * Create HTML for star rating display
 * @param {number} rating - The rating value (out of 5)
 * @returns {string} HTML for the star rating
 */
function createStarRating(rating) {
    let starsHtml = '';

    // Calculate full stars, half stars, and empty stars
    const fullStars = Math.floor(rating);
    const hasHalfStar = rating % 1 >= 0.5;
    const emptyStars = 5 - fullStars - (hasHalfStar ? 1 : 0);

    // Add full stars
    for (let i = 0; i < fullStars; i++) {
        starsHtml += '<i class="fas fa-star"></i>';
    }

    // Add half star if needed
    if (hasHalfStar) {
        starsHtml += '<i class="fas fa-star-half-alt"></i>';
    }

    // Add empty stars
    for (let i = 0; i < emptyStars; i++) {
        starsHtml += '<i class="far fa-star"></i>';
    }

    return starsHtml;
}

/**
 * Initialize gallery functionality for the vendor detail page
 */
function initializeGallery() {
    const mainImage = document.querySelector('.main-image img');
    const thumbnails = document.querySelectorAll('.gallery-thumbnails .thumbnail img');

    if (!mainImage || thumbnails.length === 0) {
        return; // No gallery to initialize
    }

    // Add click event to thumbnails
    thumbnails.forEach(thumbnail => {
        thumbnail.addEventListener('click', function() {
            // Update main image with the thumbnail source
            mainImage.src = this.src;

            // Add active class to selected thumbnail and remove from others
            thumbnails.forEach(t => t.parentElement.classList.remove('active'));
            this.parentElement.classList.add('active');
        });
    });
}

/**
 * Set up booking modal interactions
 */
function setupBookingModal() {
    const checkAvailabilityBtn = document.getElementById('checkAvailability');
    const bookingModal = document.getElementById('bookingModal');
    const submitBookingBtn = document.getElementById('submitBooking');

    if (!checkAvailabilityBtn || !bookingModal || !submitBookingBtn) {
        return; // Elements not found
    }

    // Set minimum date for event date input to today
    const eventDateInput = document.getElementById('eventDate');
    if (eventDateInput) {
        const today = new Date();
        const yyyy = today.getFullYear();
        const mm = String(today.getMonth() + 1).padStart(2, '0');
        const dd = String(today.getDate()).padStart(2, '0');
        eventDateInput.min = `${yyyy}-${mm}-${dd}`;
    }

    // Handle booking submission
    submitBookingBtn.addEventListener('click', function() {
        const form = document.getElementById('bookingForm');

        if (form && form.checkValidity()) {
            // Here you would typically send the form data to the server via AJAX
            // For this example, we'll just show a success message

            // Get vendor name for the success message
            const vendorName = document.querySelector('.vendor-detail-name').textContent;

            // Create and show success alert
            const alertHTML = `
                <div class="alert alert-success alert-dismissible fade show mt-3" role="alert">
                    <strong>Booking Request Sent!</strong> Thank you for your inquiry with ${vendorName}. We'll contact you shortly to confirm availability.
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            `;

            // Insert alert before the form
            form.insertAdjacentHTML('beforebegin', alertHTML);

            // Close the modal
            const bsModal = bootstrap.Modal.getInstance(bookingModal);
            bsModal.hide();

            // Reset the form
            form.reset();
        } else if (form) {
            // Trigger browser's native form validation UI
            form.reportValidity();
        }
    });

    // Write a review button functionality
    const writeReviewBtn = document.getElementById('writeReview');
    if (writeReviewBtn) {
        writeReviewBtn.addEventListener('click', function() {
            // This could open a review form modal or redirect to a review page
            alert('Review functionality would be implemented here.');
        });
    }
}