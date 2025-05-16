package org.example.webapplication.service;

import org.example.webapplication.model.Vendor;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VendorService {

    // Use relative path instead of absolute path
    private static final String FILE_PATH = "data/vendors.txt";

    private List<Vendor> vendorCache;

    @PostConstruct
    public void init() {
        System.out.println("Initializing VendorService...");
        ensureDataFileExists();
        this.vendorCache = readVendorsFromFile();
        System.out.println("Loaded " + vendorCache.size() + " vendors");
    }

    private void ensureDataFileExists() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("Created new data file: " + file.getAbsolutePath());

                // Add sample data if file was just created
                if (file.length() == 0) {
                    addSampleData();
                }
            } catch (IOException e) {
                System.err.println("Error creating data file: " + e.getMessage());
            }
        }
    }

    private void addSampleData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write("1|Perfect Venues|Venue|New York,Los Angeles|Beautiful wedding venues in major cities|3|5|120|https://example.com/venue.jpg|luxury,outdoor,indoor\n");
            writer.write("2|Dream Photography|Photography|Chicago,Miami|Professional wedding photographers|2|4|85|https://example.com/photo.jpg|portrait,candid,traditional\n");
            System.out.println("Added sample vendor data");
        } catch (IOException e) {
            System.err.println("Error writing sample data: " + e.getMessage());
        }
    }

    public List<Vendor> getAllVendors() {
        this.vendorCache = readVendorsFromFile();
        return this.vendorCache;
    }

    public List<Vendor> filterVendors(String category, String location, String priceRange, String sortBy) {
        List<Vendor> filteredVendors = new ArrayList<>(getAllVendors());

        // Apply filters
        if (category != null && !category.isEmpty() && !category.equals("All Categories")) {
            filteredVendors = filteredVendors.stream()
                    .filter(v -> v.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
        }

        if (location != null && !location.isEmpty() && !location.equals("All Locations")) {
            filteredVendors = filteredVendors.stream()
                    .filter(v -> v.getLocations().contains(location))
                    .collect(Collectors.toList());
        }

        if (priceRange != null && !priceRange.isEmpty() && !priceRange.equals("All Prices")) {
            int priceLevel = getPriceLevelFromRange(priceRange);
            filteredVendors = filteredVendors.stream()
                    .filter(v -> v.getPriceLevel() == priceLevel)
                    .collect(Collectors.toList());
        }

        // Apply sorting
        if (sortBy != null) {
            switch (sortBy.toLowerCase()) {
                case "rating":
                    filteredVendors.sort(Comparator.comparing(Vendor::getRating).reversed());
                    break;
                case "reviews":
                    filteredVendors.sort(Comparator.comparing(Vendor::getReviewCount).reversed());
                    break;
                case "priceasc":
                case "price-asc":
                    filteredVendors.sort(Comparator.comparing(Vendor::getPriceLevel));
                    break;
                case "pricedesc":
                case "price-desc":
                    filteredVendors.sort(Comparator.comparing(Vendor::getPriceLevel).reversed());
                    break;
                default: // recommended
                    filteredVendors.sort(Comparator.comparing(Vendor::getRating).reversed()
                            .thenComparing(Comparator.comparing(Vendor::getReviewCount).reversed()));
            }
        }

        return filteredVendors;
    }

    // Rest of the methods remain the same...
    // (getPriceLevelFromRange, addVendor, updateVendor, deleteVendor,
    // getVendorById, writeVendorsToFile, getNextVendorId,
    // getCategories, getLocations, getPriceRanges, getSortOptions)
}