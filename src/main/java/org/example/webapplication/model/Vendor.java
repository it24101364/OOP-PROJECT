package org.example.webapplication.model;

import java.util.ArrayList;
import java.util.List;

public class Vendor {
    private String id;  // ID as a String now
    private String name;
    private String category;
    private List<String> locations;
    private String description;
    private int priceLevel; // 1-4, represented as $ to $$$$
    private int rating; // Out of 5
    private int reviewCount;
    private String imageUrl;
    private List<String> tags;  // Changed from keywords to tags

    // Default constructor
    public Vendor() {
        this.locations = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    // Constructor with parameters
    public Vendor(String id, String name, String category, List<String> locations,
                  String description, int priceLevel, int rating, int reviewCount,
                  String imageUrl, List<String> tags) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.locations = locations;
        this.description = description;
        this.priceLevel = priceLevel;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.imageUrl = imageUrl;
        this.tags = tags;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getPriceLevelDisplay() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < priceLevel; i++) {
            sb.append("$");
        }
        return sb.toString();
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
