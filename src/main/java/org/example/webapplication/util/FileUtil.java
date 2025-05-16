//package org.example.webapplication.util;
//
//import org.example.webapplication.model.Vendor;
//import org.springframework.stereotype.Service;
//
//import java.io.*;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//public class FileUtil {
//
//    private static final String FILE_PATH = "D:\\webapp new\\webapplication\\vendors.txt";
//
//    public List<Vendor> readVendors() throws IOException {
//        List<Vendor> vendors = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split("\\|");
//                if (parts.length == 10) {
//                    Vendor vendor = new Vendor();
//                    vendor.setId(parts[0]);
//                    vendor.setName(parts[1]);
//                    vendor.setCategory(parts[2]);
//                    vendor.setLocations(Arrays.asList(parts[3].split(",")));
//                    vendor.setDescription(parts[4]);
//                    vendor.setPriceLevel(Integer.parseInt(parts[5]));
//                    vendor.setRating(Integer.parseInt(parts[6]));
//                    vendor.setReviewCount(Integer.parseInt(parts[7]));
//                    vendor.setImageUrl(parts[8]);
//                    vendor.setTags(Arrays.asList(parts[9].split(",")));
//                    vendors.add(vendor);
//                }
//            }
//        }
//        return vendors;
//    }
//
//    public void writeVendor(Vendor vendor) throws IOException {
//        // Add a log to check if the method is being called
//        System.out.println("Writing vendor: " + vendor.getName());
//
//        List<Vendor> vendors = readVendors();
//        vendor.setId(String.valueOf(vendors.size() + 1));  // Assign a new ID based on the size of the list
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
//            writer.write(formatVendor(vendor));
//            writer.newLine();
//            System.out.println("Vendor written to file: " + vendor.getName());  // Log to confirm writing
//        }
//    }
//
//    private String formatVendor(Vendor vendor) {
//        return vendor.getId() + "|"
//                + vendor.getName() + "|"
//                + vendor.getCategory() + "|"
//                + String.join(",", vendor.getLocations()) + "|"
//                + vendor.getDescription() + "|"
//                + vendor.getPriceLevel() + "|"
//                + vendor.getRating() + "|"
//                + vendor.getReviewCount() + "|"
//                + vendor.getImageUrl() + "|"
//                + String.join(",", vendor.getTags());
//    }
//}