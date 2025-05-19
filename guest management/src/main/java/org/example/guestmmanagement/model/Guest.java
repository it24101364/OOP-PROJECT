package org.example.guestmmanagement.model;

import java.util.UUID;

public class Guest {
    private String id;
    private String name;
    private String email;
    private String group;
    private boolean plusOne;
    private String status;

    public Guest() {
        this.id = UUID.randomUUID().toString();
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getGroup() { return group; }
    public boolean isPlusOne() { return plusOne; }
    public String getStatus() { return status; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setGroup(String group) { this.group = group; }
    public void setPlusOne(boolean plusOne) { this.plusOne = plusOne; }
    public void setStatus(String status) { this.status = status; }

    // CSV Methods
    public String toCSV() {
        return String.join(",",
                id,
                name == null ? "" : name.replace(",", " "),
                email == null ? "" : email.replace(",", " "),
                group == null ? "" : group.replace(",", " "),
                Boolean.toString(plusOne),
                status == null ? "" : status.replace(",", " ")
        );
    }

    public static Guest fromCSV(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length < 6) return null;
        Guest g = new Guest();
        g.setId(parts[0]);
        g.setName(parts[1]);
        g.setEmail(parts[2]);
        g.setGroup(parts[3]);
        g.setPlusOne(Boolean.parseBoolean(parts[4]));
        g.setStatus(parts[5]);
        return g;
    }
}
