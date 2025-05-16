// Script for ForeverWed

document.addEventListener("DOMContentLoaded", function() {
    // Toggle between grid and list view
    const gridBtn = document.getElementById("grid-view");
    const listBtn = document.getElementById("list-view");
    const vendorContainer = document.querySelector(".vendor-container");

    if (gridBtn && listBtn) {
        gridBtn.addEventListener("click", function() {
            vendorContainer.classList.remove("list-view");
            vendorContainer.classList.add("grid-view");
            gridBtn.classList.add("active");
            listBtn.classList.remove("active");
            // Save preference to localStorage
            localStorage.setItem("viewPreference", "grid");
        });

        listBtn.addEventListener("click", function() {
            vendorContainer.classList.remove("grid-view");
            vendorContainer.classList.add("list-view");
            listBtn.classList.add("active");
            gridBtn.classList.remove("active");
            // Save preference to localStorage
            localStorage.setItem("viewPreference", "list");
        });

        // Load user preference from localStorage
        const viewPreference = localStorage.getItem("viewPreference") || "grid";
        if (viewPreference === "grid") {
            gridBtn.click();
        } else {
            listBtn.click();
        }
    }

    // Filter form submission
    const filterForm = document.getElementById("filter-form");
    const resetBtn = document.getElementById("reset-filters");

    if (resetBtn) {
        resetBtn.addEventListener("click", function() {
            const selects = filterForm.querySelectorAll("select");
            selects.forEach(select => {
                // Reset to first option
                select.selectedIndex = 0;
            });

            // Submit the form to apply reset filters
            filterForm.submit();
        });
    }

    // Star rating display
    const ratingContainers = document.querySelectorAll(".rating-stars");
    ratingContainers.forEach(container => {
        const rating = parseFloat(container.dataset.rating);
        let stars = '';

        // Full stars
        for (let i = 1; i <= Math.floor(rating); i++) {
            stars += '<i class="fas fa-star"></i>';
        }

        // Half star
        if (rating % 1 !== 0) {
            stars += '<i class="fas fa-star-half-alt"></i>';
        }

        // Empty stars
        for (let i = Math.ceil(rating); i < 5; i++) {
            stars += '<i class="far fa-star"></i>';
        }

        container.innerHTML = stars;
    });
});