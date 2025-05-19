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

    private int getPriceLevelFromRange(String priceRange) {
        switch (priceRange.toLowerCase()) {
            case "low":
                return 1;
            case "medium":
                return 2;
            case "high":
                return 3;
            default:
                return 0;  // Default to a neutral level if price range is unknown
        }
    }

    public void addVendor(Vendor vendor) {
        String id = getNextVendorId();
        vendor.setId(id); // Set the new vendor's ID
        writeVendorToFile(vendor);
    }

    public Vendor getVendorById(String id) {
        return getAllVendors().stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void updateVendor(String id, Vendor updatedVendor) {
        List<Vendor> vendors = getAllVendors();
        for (int i = 0; i < vendors.size(); i++) {
            if (vendors.get(i).getId().equals(id)) {
                updatedVendor.setId(id);
                vendors.set(i, updatedVendor);
                writeVendorsToFile(vendors);  // Rewrite the file with updated list
                break;
            }
        }
    }

    public void deleteVendor(String id) {
        List<Vendor> vendors = getAllVendors();
        vendors.removeIf(v -> v.getId().equals(id));
        writeVendorsToFile(vendors);  // Rewrite the file with updated list
    }

    private void writeVendorToFile(Vendor vendor) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line = String.format(
                    "%s|%s|%s|%s|%s|%d|%d|%d|%s",
                    vendor.getId(),
                    vendor.getName(),
                    vendor.getCategory(),
                    String.join(",", vendor.getLocations()),
                    vendor.getDescription(),
                    vendor.getPriceLevel(),
                    vendor.getRating(),
                    vendor.getReviewCount(),
                    vendor.getImageUrl()
                   // String.join(",", vendor.getTags())
            );
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing vendor to file: " + e.getMessage());
        }
    }

    private void writeVendorsToFile(List<Vendor> vendors) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Vendor vendor : vendors) {
                writeVendorToFile(vendor);
            }
        } catch (IOException e) {
            System.err.println("Error writing vendors to file: " + e.getMessage());
        }
    }

    private String getNextVendorId() {
        int id = 1;
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return String.valueOf(id);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            String lastLine = null;
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }

            if (lastLine != null) {
                String[] parts = lastLine.split("\\|");
                id = Integer.parseInt(parts[0]) + 1;
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading vendor file for ID: " + e.getMessage());
        }

        return String.valueOf(id);
    }

    public List<String> getCategories() {
        return getAllVendors().stream()
                .map(Vendor::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getLocations() {
        return getAllVendors().stream()
                .flatMap(v -> v.getLocations().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getPriceRanges() {
        return Arrays.asList("Low", "Medium", "High");
    }

    public List<String> getSortOptions() {
        return Arrays.asList("Recommended", "Rating", "Reviews", "Price-Asc", "Price-Desc");
    }

    public List<Vendor> readVendorsFromFile() {
        List<Vendor> vendors = new ArrayList<>();
        File file = new File(FILE_PATH);

        // Ensure file exists, if not create a new file.
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating new file: " + e.getMessage());
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                // Check if the line has exactly 10 parts
                if (parts.length != 9) {
                    System.err.println("Skipping malformed line: " + line);
                    continue;  // Skip the malformed line
                }

                try {
                    String id = parts[0];
                    String name = parts[1];
                    String category = parts[2];
                    List<String> locations = Arrays.asList(parts[3].split(","));
                    String description = parts[4];
                    int priceLevel = Integer.parseInt(parts[5]);
                    int rating = Integer.parseInt(parts[6]);
                    int reviewCount = Integer.parseInt(parts[7]);
                    String imageUrl = parts[8];
                    //List<String> tags = Arrays.asList(parts[9].split(","));

                    Vendor vendor = new Vendor(id, name, category, locations, description, priceLevel, rating, reviewCount, imageUrl);
                    vendors.add(vendor);
                } catch (NumberFormatException e) {
                    System.err.println("Skipping malformed data in line: " + line + " due to " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading vendor file: " + e.getMessage());
        }

        return vendors;
    }
}
