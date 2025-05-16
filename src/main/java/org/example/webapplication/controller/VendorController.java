package org.example.webapplication.controller;

import org.example.webapplication.model.Vendor;
import org.example.webapplication.service.VendorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        List<Vendor> vendors = vendorService.filterVendors(category, location, priceRange, sortBy);

        model.addAttribute("vendors", vendors);
        model.addAttribute("categories", vendorService.getCategories());
        model.addAttribute("locations", vendorService.getLocations());
        model.addAttribute("priceRanges", vendorService.getPriceRanges());
        model.addAttribute("sortOptions", vendorService.getSortOptions());

        // Add selected values for form preservation
        model.addAttribute("selectedCategory", category);
        model.addAttribute("selectedLocation", location);
        model.addAttribute("selectedPriceRange", priceRange);
        model.addAttribute("selectedSortBy", sortBy);
        model.addAttribute("vendorCount", vendors.size());

        return "index";
    }

    @GetMapping("/admin")
    public String adminDashboard(
            @RequestParam(required = false, defaultValue = "All Categories") String category,
            @RequestParam(required = false, defaultValue = "All Locations") String location,
            @RequestParam(required = false, defaultValue = "All Prices") String priceRange,
            @RequestParam(required = false, defaultValue = "recommended") String sortBy,
            Model model) {

        List<Vendor> vendors = vendorService.filterVendors(category, location, priceRange, sortBy);

        model.addAttribute("vendors", vendors);
        model.addAttribute("categories", vendorService.getCategories());
        model.addAttribute("locations", vendorService.getLocations());
        model.addAttribute("priceRanges", vendorService.getPriceRanges());
        model.addAttribute("sortOptions", vendorService.getSortOptions());

        // Add selected values for form preservation
        model.addAttribute("selectedCategory", category);
        model.addAttribute("selectedLocation", location);
        model.addAttribute("selectedPriceRange", priceRange);
        model.addAttribute("selectedSortBy", sortBy);
        model.addAttribute("vendorCount", vendors.size());

        return "admin";
    }

    @GetMapping("/vendors/add")
    public String showAddForm(Model model) {
        model.addAttribute("vendor", new Vendor());
        return "add-vendor";
    }

    @PostMapping("/admin/add")
    public String addVendor(@ModelAttribute Vendor vendor) {
        vendorService.addVendor(vendor);
        return "redirect:/admin";
    }
}