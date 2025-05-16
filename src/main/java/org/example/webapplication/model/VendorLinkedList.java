package org.example.webapplication.model;

import java.util.ArrayList;
import java.util.List;

public class VendorLinkedList {

    private Node head;
    private int size;

    // Node class for the linked list
    private static class Node {
        Vendor vendor;
        Node next;

        Node(Vendor vendor) {
            this.vendor = vendor;
            this.next = null;
        }
    }

    public VendorLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Add a vendor to the linked list
    public void add(Vendor vendor) {
        Node newNode = new Node(vendor);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Add all vendors from a list
    public void addAll(List<Vendor> vendors) {
        for (Vendor vendor : vendors) {
            add(vendor);
        }
    }

    // Get size of the linked list
    public int size() {
        return size;
    }

    // Check if linked list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Convert linked list to ArrayList
    public List<Vendor> toList() {
        List<Vendor> list = new ArrayList<>();
        Node current = head;

        while (current != null) {
            list.add(current.vendor);
            current = current.next;
        }

        return list;
    }

    // Bubble sort vendors by price
    public void bubbleSortByPrice() {
        if (head == null || head.next == null) {
            return; // No need to sort if empty or has only one element
        }

        boolean swapped;
        Node current;
        Node last = null;

        do {
            swapped = false;
            current = head;

            while (current.next != last) {
                if (current.vendor.getPriceLevel() > current.next.vendor.getPriceLevel()) {
                    // Swap vendors
                    Vendor temp = current.vendor;
                    current.vendor = current.next.vendor;
                    current.next.vendor = temp;
                    swapped = true;
                }
                current = current.next;
            }
            last = current;
        } while (swapped);
    }

    // Bubble sort vendors by rating (descending)
    public void bubbleSortByRating() {
        if (head == null || head.next == null) {
            return;
        }

        boolean swapped;
        Node current;
        Node last = null;

        do {
            swapped = false;
            current = head;

            while (current.next != last) {
                if (current.vendor.getRating() < current.next.vendor.getRating()) {
                    // Swap vendors (descending order for ratings)
                    Vendor temp = current.vendor;
                    current.vendor = current.next.vendor;
                    current.next.vendor = temp;
                    swapped = true;
                }
                current = current.next;
            }
            last = current;
        } while (swapped);
    }

    // Filter vendors by category
    public VendorLinkedList filterByCategory(String category) {
        if (category == null || category.isEmpty() || category.equalsIgnoreCase("All Categories")) {
            return this; // Return all vendors if no category filter
        }

        VendorLinkedList filtered = new VendorLinkedList();
        Node current = head;

        while (current != null) {
            if (current.vendor.getCategory().equalsIgnoreCase(category)) {
                filtered.add(current.vendor);
            }
            current = current.next;
        }

        return filtered;
    }

    // Filter vendors by location
    public VendorLinkedList filterByLocation(String location) {
        if (location == null || location.isEmpty() || location.equalsIgnoreCase("All Locations")) {
            return this; // Return all vendors if no location filter
        }

        VendorLinkedList filtered = new VendorLinkedList();
        Node current = head;

        while (current != null) {
            if (current.vendor.getLocations().stream()
                    .anyMatch(loc -> loc.equalsIgnoreCase(location))) {
                filtered.add(current.vendor);
            }
            current = current.next;
        }

        return filtered;
    }

    // Filter vendors by price range
    public VendorLinkedList filterByPriceRange(int minPrice, int maxPrice) {
        VendorLinkedList filtered = new VendorLinkedList();
        Node current = head;

        while (current != null) {
            int priceLevel = current.vendor.getPriceLevel();
            if (priceLevel >= minPrice && priceLevel <= maxPrice) {
                filtered.add(current.vendor);
            }
            current = current.next;
        }

        return filtered;
    }
}
