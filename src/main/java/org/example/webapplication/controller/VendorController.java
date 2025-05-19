package org.example.webapplication.controller;

import org.example.webapplication.model.Vendor;
import org.example.webapplication.service.VendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }


    @GetMapping("/")
    public String home() {
        return "redirect:/vendors";
    }

    @GetMapping("/vendors")
    public String listVendors(
            @RequestParam(required = false, defaultValue = "All Categories") String category,
            @RequestParam(required = false, defaultValue = "All Locations") String location,
            @RequestParam(required = false, defaultValue = "All Prices") String priceRange,
            @RequestParam(required = false, defaultValue = "recommended") String sortBy,
            Model model) {

        List<Vendor> vendors = vendorService.filterVendors(
                category.equals("All Categories") ? "" : category,
                location.equals("All Locations") ? "" : location,
                priceRange.equals("All Prices") ? "" : priceRange,
                sortBy
        );

        model.addAttribute("vendors", vendors);
        model.addAttribute("categories", vendorService.getCategories());
        model.addAttribute("locations", vendorService.getLocations());
        model.addAttribute("priceRanges", vendorService.getPriceRanges());
        model.addAttribute("sortOptions", vendorService.getSortOptions());

        model.addAttribute("selectedCategory", category);
        model.addAttribute("selectedLocation", location);
        model.addAttribute("selectedPriceRange", priceRange);
        model.addAttribute("selectedSortBy", sortBy);
        model.addAttribute("vendorCount", vendors.size());

        return "index";
    }

    @PostMapping("/book/{vendorId}")
    public String bookVendor(
            @PathVariable String vendorId,
            @RequestParam String eventDate,
            @RequestParam int guestCount,
            @RequestParam(required = false) String notes,
            Model model) {

        // In a real application, you would:
        // 1. Validate the booking request
        // 2. Process the payment
        // 3. Save the booking to database
        // 4. Send confirmation emails

        Vendor vendor = vendorService.getVendorById(vendorId);
        model.addAttribute("vendor", vendor);
        model.addAttribute("eventDate", eventDate);
        model.addAttribute("guestCount", guestCount);

        return "booking-confirmation";
    }

    @GetMapping("/admin")
    public String adminDashboard(
            @RequestParam(required = false, defaultValue = "All Categories") String category,
            @RequestParam(required = false, defaultValue = "All Locations") String location,
            @RequestParam(required = false, defaultValue = "All Prices") String priceRange,
            @RequestParam(required = false, defaultValue = "recommended") String sortBy,
            Model model) {

        List<Vendor> vendors = vendorService.filterVendors(
                category.equals("All Categories") ? "" : category,
                location.equals("All Locations") ? "" : location,
                priceRange.equals("All Prices") ? "" : priceRange,
                sortBy
        );

        model.addAttribute("vendors", vendors);
        model.addAttribute("categories", vendorService.getCategories());
        model.addAttribute("locations", vendorService.getLocations());
        model.addAttribute("priceRanges", vendorService.getPriceRanges());
        model.addAttribute("sortOptions", vendorService.getSortOptions());

        model.addAttribute("selectedCategory", category);
        model.addAttribute("selectedLocation", location);
        model.addAttribute("selectedPriceRange", priceRange);
        model.addAttribute("selectedSortBy", sortBy);
        model.addAttribute("vendorCount", vendors.size());

        return "admin";
    }



    @GetMapping("/admin/vendors/{id}")
    @ResponseBody
    public Vendor getVendorByIdForAdmin(@PathVariable String id) {
        return vendorService.getVendorById(id);
    }

    @GetMapping("/admin/vendors/edit/{id}")
    public String showEditVendorForm(@PathVariable String id, Model model) {
        Vendor vendor = vendorService.getVendorById(id);
        if (vendor == null) {
            return "redirect:/admin";
        }

        model.addAttribute("vendor", vendor);
        model.addAttribute("categories", vendorService.getCategories());
        model.addAttribute("locations", vendorService.getLocations());
        return "edit-vendor";
    }

    @PostMapping("/admin/add")
    public String addVendor(@ModelAttribute Vendor vendor) {
        vendorService.addVendor(vendor);
        return "redirect:/admin";
    }

    @PostMapping("/admin/vendors/edit")
    public String updateVendor(@ModelAttribute Vendor vendor) {
        vendorService.updateVendor(vendor.getId(), vendor);
        return "redirect:/admin";
    }

    @PostMapping("/admin/vendors/delete")
    public String deleteVendor(@RequestParam String id) {
        vendorService.deleteVendor(id);
        return "redirect:/admin";
    }
}