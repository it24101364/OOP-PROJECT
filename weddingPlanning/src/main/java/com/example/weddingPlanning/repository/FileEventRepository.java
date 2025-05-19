package com.example.weddingPlanning.repository;

import com.example.weddingPlanning.model.Event;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class FileEventRepository implements EventRepository {

    private static final String DATA_FILE = "events.txt";
    private List<Event> events = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong(0);

    public FileEventRepository() {
        loadEventsFromFile();
    }

    private void loadEventsFromFile() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return; // File doesn't exist yet, start with empty list
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Simple CSV format: id,name,dateTime,location,description
                if (parts.length == 5) {
                    try {
                        Long id = Long.parseLong(parts[0]);
                        String name = parts[1];
                        LocalDateTime dateTime = LocalDateTime.parse(parts[2]); // Requires proper date/time format
                        String location = parts[3];
                        String description = parts[4];

                        Event event = new Event(name, dateTime, location, description);
                        event.setId(id);
                        events.add(event);

                        // Update ID counter to be greater than any loaded ID
                        if (id >= idCounter.get()) {
                            idCounter.set(id + 1);
                        }

                    } catch (NumberFormatException | DateTimeParseException e) {
                        System.err.println("Skipping invalid line in " + DATA_FILE + ": " + line);
                        e.printStackTrace(); // Log the error
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading events from file: " + DATA_FILE);
            e.printStackTrace();
        }
    }

    private void saveEventsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Event event : events) {
                // Simple CSV format: id,name,dateTime,location,description
                writer.write(event.getId() + "," +
                             event.getName() + "," +
                             event.getDateTime() + "," + // Requires proper date/time format
                             event.getLocation() + "," +
                             event.getDescription());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving events to file: " + DATA_FILE);
            e.printStackTrace();
        }
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(events); // Return a copy to prevent external modification
    }

    @Override
    public Optional<Event> findById(Long id) {
        return events.stream()
                     .filter(event -> event.getId().equals(id))
                     .findFirst();
    }

    @Override
    public Event save(Event event) {
        if (event.getId() == null) {
            // This is a new event
            event.setId(idCounter.getAndIncrement());
            events.add(event);
        } else {
            // This is an update to an existing event
            Optional<Event> existingEvent = findById(event.getId());
            if (existingEvent.isPresent()) {
                Event existing = existingEvent.get();
                existing.setName(event.getName());
                existing.setDateTime(event.getDateTime());
                existing.setLocation(event.getLocation());
                existing.setDescription(event.getDescription());
            } else {
                // If the ID doesn't exist, add it as a new event
                events.add(event);
            }
        }
        saveEventsToFile();
        return event;
    }

    @Override
    public void deleteById(Long id) {
        boolean removed = events.removeIf(event -> event.getId().equals(id));
        if (removed) {
            saveEventsToFile();
        }
    }
}