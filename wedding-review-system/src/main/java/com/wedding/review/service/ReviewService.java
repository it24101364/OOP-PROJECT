package com.wedding.review.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wedding.review.model.Review;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReviewService {
    private final String DATA_FILE = "src/main/resources/reviews.json";
    private final ObjectMapper objectMapper;
    private final AtomicLong idCounter = new AtomicLong(1);
    private List<Review> reviews;

    public ReviewService() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        this.reviews = loadReviews();
        System.out.println("Reviews file location: " + DATA_FILE); // Debug line
    }

    private List<Review> loadReviews() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            try {
                // Create parent directories if they don't exist
                file.getParentFile().mkdirs();
                // Create empty file with empty array
                objectMapper.writeValue(file, new ArrayList<Review>());
                System.out.println("Created new reviews file at: " + DATA_FILE); // Debug line
                return new ArrayList<>();
            } catch (IOException e) {
                System.err.println("Failed to create reviews file: " + e.getMessage()); // Debug line
                throw new RuntimeException("Failed to create reviews file", e);
            }
        }
        try {
            List<Review> loadedReviews = new ArrayList<>(Arrays.asList(objectMapper.readValue(file, Review[].class)));
            System.out.println("Loaded " + loadedReviews.size() + " reviews from file"); // Debug line
            return loadedReviews;
        } catch (IOException e) {
            System.err.println("Failed to load reviews: " + e.getMessage()); // Debug line
            return new ArrayList<>();
        }
    }

    private void saveReviews() {
        try {
            File file = new File(DATA_FILE);
            // Ensure parent directories exist
            file.getParentFile().mkdirs();
            objectMapper.writeValue(file, reviews);
            System.out.println("Saved " + reviews.size() + " reviews to file"); // Debug line
        } catch (IOException e) {
            System.err.println("Failed to save reviews: " + e.getMessage()); // Debug line
            throw new RuntimeException("Failed to save reviews", e);
        }
    }

    public List<Review> getAllReviews() {
        return new ArrayList<>(reviews);
    }

    public Review getReview(String id) {
        return reviews.stream()
                .filter(review -> review.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public Review createReview(Review review) {
        review.setId(String.valueOf(idCounter.getAndIncrement()));
        review.setCreatedAt(LocalDateTime.now());
        review.setUpdatedAt(LocalDateTime.now());
        reviews.add(review);
        saveReviews();
        return review;
    }

    public Review updateReview(String id, Review updatedReview) {
        Review existingReview = getReview(id);
        existingReview.setStars(updatedReview.getStars());
        existingReview.setDescription(updatedReview.getDescription());
        existingReview.setActivityType(updatedReview.getActivityType());
        existingReview.setVendorName(updatedReview.getVendorName());
        existingReview.setUpdatedAt(LocalDateTime.now());
        saveReviews();
        return existingReview;
    }

    public void deleteReview(String id) {
        reviews.removeIf(review -> review.getId().equals(id));
        saveReviews();
    }
} 