package org.example.webapplication.model;

import java.util.ArrayList;
import java.util.List;

public class Vendor {
    private String id;
    private String name;
    private String category;
    private List<String> locations;
    private String description;
    private int priceLevel; // 1-4
    private int rating;     // 1-5
    private int reviewCount;
    private String imageUrl;


    public Vendor() {
        this.locations = new ArrayList<>();
    }

    public Vendor(String id, String name, String category, List<String> locations,
                  String description, int priceLevel, int rating, int reviewCount, String imageUrl) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.locations = locations != null ? locations : new ArrayList<>();
        this.description = description;
        this.priceLevel = priceLevel;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.imageUrl = imageUrl;

    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(int priceLevel) {
        this.priceLevel = priceLevel;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Display price level as dollar signs ($)
    public String getPriceLevelDisplay() {
        return "$".repeat(Math.max(0, priceLevel));
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", priceLevel=" + priceLevel +
                '}';
    }
}
