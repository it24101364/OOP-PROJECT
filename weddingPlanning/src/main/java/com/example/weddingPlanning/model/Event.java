package com.example.weddingPlanning.model;

import java.time.LocalDateTime;

public class Event {

    private Long id;
    private String name;
    private LocalDateTime dateTime;
    private String location;
    private String description;

    // Constructors
    public Event() {
    }

    public Event(String name, LocalDateTime dateTime, String location, String description) {
        this.name = name;
        this.dateTime = dateTime;
        this.location = location;
        this.description = description;
    }

    // Getters and Setters (You can use Lombok for this)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", dateTime=" + dateTime +
               ", location='" + location + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}