package org.example.guestmmanagement.service;

import org.example.guestmmanagement.model.Guest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GuestService {
    private List<Guest> guests = new ArrayList<>();

    @Value("${app.data.file:guest.txt}")
    private String dataFile;

    @PostConstruct
    public void init() {
        loadGuests();
    }

    public List<Guest> getAllGuests() {
        return new ArrayList<>(guests);
    }

    public Guest getGuestById(String id) {
        return guests.stream().filter(g -> g.getId().equals(id)).findFirst().orElse(null);
    }

    public void addGuest(Guest guest) {
        guests.add(guest);
        saveGuests();
    }

    public void updateGuest(Guest updatedGuest) {
        for (int i = 0; i < guests.size(); i++) {
            if (guests.get(i).getId().equals(updatedGuest.getId())) {
                guests.set(i, updatedGuest);
                saveGuests();
                return;
            }
        }
    }

    public void deleteGuest(String id) {
        guests.removeIf(g -> g.getId().equals(id));
        saveGuests();
    }

    private void loadGuests() {
        guests.clear();
        File file = new File(dataFile);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Guest guest = Guest.fromCSV(line);
                if (guest != null) guests.add(guest);
            }
        } catch (IOException e) {
            System.err.println("Error loading guests: " + e.getMessage());
        }
    }

    private void saveGuests() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
            for (Guest guest : guests) {
                writer.write(guest.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving guests: " + e.getMessage());
        }
    }

    // Add these two missing methods
    public Set<String> getAllGroups() {
        return guests.stream()
                .map(Guest::getGroup)
                .filter(group -> group != null && !group.isEmpty())
                .collect(Collectors.toSet());
    }

    public Set<String> getAllStatuses() {
        Set<String> statuses = new HashSet<>();
        statuses.add("Pending");
        statuses.add("Confirmed");
        statuses.add("Declined");

        // Also include any custom statuses from guests
        guests.stream()
                .map(Guest::getStatus)
                .filter(status -> status != null && !status.isEmpty())
                .forEach(statuses::add);

        return statuses;
    }
}
