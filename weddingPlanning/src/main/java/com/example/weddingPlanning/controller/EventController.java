package com.example.weddingPlanning.controller;

import com.example.weddingPlanning.model.Event;
import com.example.weddingPlanning.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_HTML_VALUE})
    public Object getAllEvents(@RequestHeader(value = "Accept", required = false) String acceptHeader) {
        List<Event> events = eventService.getAllEvents();
        
        // If the request accepts HTML (browser request), return HTML content
        if (acceptHeader != null && acceptHeader.contains(MediaType.TEXT_HTML_VALUE)) {
            StringBuilder html = new StringBuilder();
            html.append("<!DOCTYPE html>");
            html.append("<html lang='en'>");
            html.append("<head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            html.append("<title>Wedding Planning - Events</title>");
            html.append("<link href='https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Roboto:wght@300;400;500&display=swap' rel='stylesheet'>");
            html.append("<style>");
            html.append("    :root {");
            html.append("        --primary-color: #e8b7d4;");
            html.append("        --secondary-color: #a5d8ff;");
            html.append("        --accent-color: #6a0572;");
            html.append("        --text-color: #333333;");
            html.append("        --light-color: #ffffff;");
            html.append("        --background-color: #f9f7f9;");
            html.append("    }");
            html.append("    * { margin: 0; padding: 0; box-sizing: border-box; }");
            html.append("    body {");
            html.append("        font-family: 'Roboto', sans-serif;");
            html.append("        color: var(--text-color);");
            html.append("        background-color: var(--background-color);");
            html.append("        line-height: 1.6;");
            html.append("    }");
            html.append("    .container {");
            html.append("        max-width: 1200px;");
            html.append("        margin: 0 auto;");
            html.append("        padding: 0 20px;");
            html.append("    }");
            html.append("    header {");
            html.append("        background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));");
            html.append("        color: var(--light-color);");
            html.append("        text-align: center;");
            html.append("        padding: 60px 20px;");
            html.append("        margin-bottom: 40px;");
            html.append("        border-radius: 0 0 30px 30px;");
            html.append("        box-shadow: 0 4px 12px rgba(0,0,0,0.1);");
            html.append("    }");
            html.append("    h1 {");
            html.append("        font-family: 'Playfair Display', serif;");
            html.append("        font-size: 2.5rem;");
            html.append("        margin-bottom: 20px;");
            html.append("        color: var(--accent-color);");
            html.append("    }");
            html.append("    .event-list {");
            html.append("        display: grid;");
            html.append("        grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));");
            html.append("        gap: 30px;");
            html.append("        margin: 40px 0;");
            html.append("    }");
            html.append("    .event-card {");
            html.append("        background-color: var(--light-color);");
            html.append("        border-radius: 15px;");
            html.append("        padding: 25px;");
            html.append("        box-shadow: 0 5px 15px rgba(0,0,0,0.05);");
            html.append("        transition: transform 0.3s ease, box-shadow 0.3s ease;");
            html.append("    }");
            html.append("    .event-card:hover {");
            html.append("        transform: translateY(-10px);");
            html.append("        box-shadow: 0 15px 30px rgba(0,0,0,0.1);");
            html.append("    }");
            html.append("    .event-name {");
            html.append("        font-family: 'Playfair Display', serif;");
            html.append("        font-size: 1.5rem;");
            html.append("        margin-bottom: 15px;");
            html.append("        color: var(--accent-color);");
            html.append("    }");
            html.append("    .event-info {");
            html.append("        margin-bottom: 20px;");
            html.append("    }");
            html.append("    .event-date, .event-location {");
            html.append("        display: flex;");
            html.append("        align-items: center;");
            html.append("        margin-bottom: 8px;");
            html.append("        color: #666;");
            html.append("    }");
            html.append("    .event-icon {");
            html.append("        margin-right: 10px;");
            html.append("        color: var(--primary-color);");
            html.append("    }");
            html.append("    .event-description {");
            html.append("        margin-top: 15px;");
            html.append("        color: var(--text-color);");
            html.append("        line-height: 1.6;");
            html.append("    }");
            html.append("    .event-actions {");
            html.append("        display: flex;");
            html.append("        justify-content: flex-end;");
            html.append("        gap: 10px;");
            html.append("        margin-top: 20px;");
            html.append("    }");
            html.append("    .btn {");
            html.append("        display: inline-block;");
            html.append("        padding: 10px 20px;");
            html.append("        border-radius: 50px;");
            html.append("        text-decoration: none;");
            html.append("        font-weight: 500;");
            html.append("        font-size: 0.9rem;");
            html.append("        transition: all 0.3s ease;");
            html.append("        cursor: pointer;");
            html.append("        border: none;");
            html.append("    }");
            html.append("    .btn-primary {");
            html.append("        background-color: var(--accent-color);");
            html.append("        color: var(--light-color);");
            html.append("        box-shadow: 0 4px 12px rgba(106, 5, 114, 0.3);");
            html.append("    }");
            html.append("    .btn-primary:hover {");
            html.append("        background-color: #7d0685;");
            html.append("        transform: translateY(-3px);");
            html.append("        box-shadow: 0 6px 15px rgba(106, 5, 114, 0.4);");
            html.append("    }");
            html.append("    .btn-secondary {");
            html.append("        background-color: var(--light-color);");
            html.append("        color: var(--accent-color);");
            html.append("        border: 2px solid var(--accent-color);");
            html.append("    }");
            html.append("    .btn-secondary:hover {");
            html.append("        background-color: var(--accent-color);");
            html.append("        color: var(--light-color);");
            html.append("        transform: translateY(-3px);");
            html.append("    }");
            html.append("    .btn-warning {");
            html.append("        background-color: #FF9800;");
            html.append("        color: var(--light-color);");
            html.append("    }");
            html.append("    .btn-warning:hover {");
            html.append("        background-color: #e68a00;");
            html.append("        transform: translateY(-3px);");
            html.append("    }");
            html.append("    .btn-danger {");
            html.append("        background-color: #f44336;");
            html.append("        color: var(--light-color);");
            html.append("    }");
            html.append("    .btn-danger:hover {");
            html.append("        background-color: #d32f2f;");
            html.append("        transform: translateY(-3px);");
            html.append("    }");
            html.append("    .no-events {");
            html.append("        text-align: center;");
            html.append("        padding: 50px;");
            html.append("        background-color: var(--light-color);");
            html.append("        border-radius: 15px;");
            html.append("        box-shadow: 0 5px 15px rgba(0,0,0,0.05);");
            html.append("    }");
            html.append("    .no-events-icon {");
            html.append("        font-size: 4rem;");
            html.append("        margin-bottom: 20px;");
            html.append("        color: var(--primary-color);");
            html.append("    }");
            html.append("    .no-events-text {");
            html.append("        font-size: 1.2rem;");
            html.append("        color: #666;");
            html.append("        margin-bottom: 20px;");
            html.append("    }");
            html.append("    .buttons {");
            html.append("        display: flex;");
            html.append("        justify-content: space-between;");
            html.append("        margin-top: 30px;");
            html.append("    }");
            html.append("    .modal {");
            html.append("        display: none;");
            html.append("        position: fixed;");
            html.append("        z-index: 1;");
            html.append("        left: 0;");
            html.append("        top: 0;");
            html.append("        width: 100%;");
            html.append("        height: 100%;");
            html.append("        overflow: auto;");
            html.append("        background-color: rgba(0,0,0,0.4);");
            html.append("    }");
            html.append("    .modal-content {");
            html.append("        background-color: var(--light-color);");
            html.append("        margin: 15% auto;");
            html.append("        padding: 30px;");
            html.append("        border-radius: 15px;");
            html.append("        box-shadow: 0 5px 15px rgba(0,0,0,0.2);");
            html.append("        width: 80%;");
            html.append("        max-width: 500px;");
            html.append("    }");
            html.append("    .close {");
            html.append("        color: #aaa;");
            html.append("        float: right;");
            html.append("        font-size: 28px;");
            html.append("        font-weight: bold;");
            html.append("        cursor: pointer;");
            html.append("    }");
            html.append("    .close:hover, .close:focus {");
            html.append("        color: black;");
            html.append("        text-decoration: none;");
            html.append("    }");
            html.append("    .modal h2 {");
            html.append("        font-family: 'Playfair Display', serif;");
            html.append("        color: var(--accent-color);");
            html.append("        margin-bottom: 20px;");
            html.append("    }");
            html.append("    .modal p {");
            html.append("        margin-bottom: 20px;");
            html.append("    }");
            html.append("    footer {");
            html.append("        text-align: center;");
            html.append("        padding: 30px 0;");
            html.append("        color: #777;");
            html.append("        font-size: 0.9rem;");
            html.append("        margin-top: 50px;");
            html.append("        border-top: 1px solid #eee;");
            html.append("    }");
            html.append("    @media (max-width: 768px) {");
            html.append("        .event-list { grid-template-columns: 1fr; }");
            html.append("        .buttons { flex-direction: column; align-items: center; }");
            html.append("        .btn { width: 100%; text-align: center; margin-bottom: 10px; }");
            html.append("    }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");
            html.append("<header>");
            html.append("    <div class='container'>");
            html.append("        <h1>Your Wedding Events</h1>");
            html.append("    </div>");
            html.append("</header>");
            
            html.append("<div class='container'>");
            
            if (events.isEmpty()) {
                html.append("<div class='no-events'>");
                html.append("<p>No events found. Create your first event!</p>");
                html.append("</div>");
            } else {
                html.append("<div class='event-list'>");
                for (Event event : events) {
                    html.append("<div class='event-card'>");
                    html.append("<div class='event-name'>").append(event.getName()).append("</div>");
                    html.append("<div class='event-date'>Date: ").append(event.getDateTime()).append("</div>");
                    html.append("<div class='event-location'><span class='event-icon'>📍</span> Venue: ").append(event.getLocation()).append("</div>");
                    html.append("<div class='event-description'>").append(event.getDescription()).append("</div>");
                    html.append("<div class='event-actions'>");
                    html.append("<button class='btn btn-warning' onclick='editEvent(").append(event.getId()).append(")'>Edit</button>");
                    html.append("<button class='btn btn-danger' onclick='deleteEvent(").append(event.getId()).append(")'>Delete</button>");
                    html.append("</div>");
                    html.append("</div>");
                }
                html.append("</div>");
            }
            
            html.append("<div class='buttons'>");
            html.append("<a href='/' class='btn btn-secondary'>Back to Home</a>");
            html.append("<a href='/create-event' class='btn btn-primary'>Create New Event</a>");
            html.append("</div>");
            
            // Add modal for delete confirmation
            html.append("<div id='deleteModal' class='modal'>");
            html.append("<div class='modal-content'>");
            html.append("<span class='close' onclick='closeDeleteModal()'>&times;</span>");
            html.append("<h2>Confirm Delete</h2>");
            html.append("<p>Are you sure you want to delete this event? This action cannot be undone.</p>");
            html.append("<div class='buttons'>");
            html.append("<button class='btn btn-secondary' onclick='closeDeleteModal()'>Cancel</button>");
            html.append("<button class='btn btn-danger' id='confirmDelete'>Delete</button>");
            html.append("</div>");
            html.append("</div>");
            html.append("</div>");
            
            // Add JavaScript for edit and delete functionality
            html.append("<script>");
            
            // Function to navigate to edit page
            html.append("function editEvent(id) {");
            html.append("  window.location.href = '/edit-event?id=' + id;");
            html.append("}");
            
            // Functions for delete confirmation modal
            html.append("let eventIdToDelete = null;");
            html.append("const deleteModal = document.getElementById('deleteModal');");
            
            html.append("function deleteEvent(id) {");
            html.append("  eventIdToDelete = id;");
            html.append("  deleteModal.style.display = 'block';");
            html.append("}");
            
            html.append("function closeDeleteModal() {");
            html.append("  deleteModal.style.display = 'none';");
            html.append("}");
            
            html.append("document.getElementById('confirmDelete').addEventListener('click', function() {");
            html.append("  if (eventIdToDelete) {");
            html.append("    fetch('/api/events/' + eventIdToDelete, {");
            html.append("      method: 'DELETE'");
            html.append("    })");
            html.append("    .then(response => {");
            html.append("      if (response.ok) {");
            html.append("        window.location.reload();");
            html.append("      } else {");
            html.append("        alert('Error deleting event');");
            html.append("      }");
            html.append("    })");
            html.append("    .catch(error => {");
            html.append("      alert('Error: ' + error.message);");
            html.append("    });");
            html.append("  }");
            html.append("  closeDeleteModal();");
            html.append("});");
            
            // Close modal when clicking outside of it
            html.append("window.onclick = function(event) {");
            html.append("  if (event.target == deleteModal) {");
            html.append("    closeDeleteModal();");
            html.append("  }");
            html.append("};");
            
            html.append("</script>");
            
            html.append("</div>");
            html.append("</body>");
            html.append("</html>");
            
            return html.toString();
        }
        
        // Otherwise, return JSON (for API clients)
        return events;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        return event.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        Event updatedEvent = eventService.updateEvent(id, eventDetails);
        if (updatedEvent != null) {
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<String> getEditEventForm(@PathVariable Long id) {
        Optional<Event> optionalEvent = eventService.getEventById(id);
        
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            StringBuilder html = new StringBuilder();
            
            html.append("<!DOCTYPE html>");
            html.append("<html lang='en'>");
            html.append("<head>");
            html.append("<meta charset='UTF-8'>");
            html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            html.append("<title>Edit Event - Wedding Planning</title>");
            html.append("<link href='https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Roboto:wght@300;400;500&display=swap' rel='stylesheet'>");
            html.append("<style>");
            html.append("    :root {");
            html.append("        --primary-color: #e8b7d4;");
            html.append("        --secondary-color: #a5d8ff;");
            html.append("        --accent-color: #6a0572;");
            html.append("        --text-color: #333333;");
            html.append("        --light-color: #ffffff;");
            html.append("        --background-color: #f9f7f9;");
            html.append("    }");
            html.append("    * { margin: 0; padding: 0; box-sizing: border-box; }");
            html.append("    body {");
            html.append("        font-family: 'Roboto', sans-serif;");
            html.append("        color: var(--text-color);");
            html.append("        background-color: var(--background-color);");
            html.append("        line-height: 1.6;");
            html.append("        padding: 20px;");
            html.append("    }");
            html.append("    .container {");
            html.append("        max-width: 800px;");
            html.append("        margin: 0 auto;");
            html.append("        padding: 30px;");
            html.append("        background-color: var(--light-color);");
            html.append("        border-radius: 15px;");
            html.append("        box-shadow: 0 5px 15px rgba(0,0,0,0.05);");
            html.append("    }");
            html.append("    h1 {");
            html.append("        font-family: 'Playfair Display', serif;");
            html.append("        font-size: 2.5rem;");
            html.append("        margin-bottom: 30px;");
            html.append("        color: var(--accent-color);");
            html.append("        text-align: center;");
            html.append("    }");
            html.append("    .form-group {");
            html.append("        margin-bottom: 20px;");
            html.append("    }");
            html.append("    label {");
            html.append("        display: block;");
            html.append("        margin-bottom: 8px;");
            html.append("        font-weight: 500;");
            html.append("        color: var(--accent-color);");
            html.append("    }");
            html.append("    input, textarea {");
            html.append("        width: 100%;");
            html.append("        padding: 12px;");
            html.append("        border: 1px solid #ddd;");
            html.append("        border-radius: 8px;");
            html.append("        font-family: 'Roboto', sans-serif;");
            html.append("        font-size: 1rem;");
            html.append("    }");
            html.append("    textarea {");
            html.append("        min-height: 120px;");
            html.append("        resize: vertical;");
            html.append("    }");
            html.append("    .buttons {");
            html.append("        display: flex;");
            html.append("        justify-content: space-between;");
            html.append("        margin-top: 30px;");
            html.append("    }");
            html.append("    .btn {");
            html.append("        display: inline-block;");
            html.append("        padding: 12px 25px;");
            html.append("        border-radius: 50px;");
            html.append("        text-decoration: none;");
            html.append("        font-weight: 500;");
            html.append("        font-size: 1rem;");
            html.append("        transition: all 0.3s ease;");
            html.append("        cursor: pointer;");
            html.append("        border: none;");
            html.append("    }");
            html.append("    .btn-primary {");
            html.append("        background-color: var(--accent-color);");
            html.append("        color: var(--light-color);");
            html.append("        box-shadow: 0 4px 12px rgba(106, 5, 114, 0.3);");
            html.append("    }");
            html.append("    .btn-primary:hover {");
            html.append("        background-color: #7d0685;");
            html.append("        transform: translateY(-3px);");
            html.append("        box-shadow: 0 6px 15px rgba(106, 5, 114, 0.4);");
            html.append("    }");
            html.append("    .btn-secondary {");
            html.append("        background-color: var(--light-color);");
            html.append("        color: var(--accent-color);");
            html.append("        border: 2px solid var(--accent-color);");
            html.append("    }");
            html.append("    .btn-secondary:hover {");
            html.append("        background-color: var(--accent-color);");
            html.append("        color: var(--light-color);");
            html.append("        transform: translateY(-3px);");
            html.append("    }");
            html.append("    .result {");
            html.append("        margin-top: 20px;");
            html.append("        padding: 15px;");
            html.append("        border-radius: 8px;");
            html.append("    }");
            html.append("    .success {");
            html.append("        background-color: #dff0d8;");
            html.append("        color: #3c763d;");
            html.append("    }");
            html.append("    .error {");
            html.append("        background-color: #f2dede;");
            html.append("        color: #a94442;");
            html.append("    }");
            html.append("    footer {");
            html.append("        text-align: center;");
            html.append("        padding: 20px 0;");
            html.append("        color: #777;");
            html.append("        font-size: 0.9rem;");
            html.append("        margin-top: 40px;");
            html.append("    }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");
            html.append("<div class='container'>");
            html.append("<h1>Edit Event</h1>");
            
            html.append("<form id='editEventForm'>");
            html.append("<input type='hidden' id='eventId' value='").append(event.getId()).append("'>");
            
            html.append("<div class='form-group'>");
            html.append("<label for='name'>Event Name</label>");
            html.append("<input type='text' id='name' name='name' value='").append(event.getName()).append("' required>");
            html.append("</div>");
            
            html.append("<div class='form-group'>");
            html.append("<label for='dateTime'>Date and Time</label>");
            // Format the date for the datetime-local input
            String dateTimeValue = event.getDateTime().toString().replace("T", " ").substring(0, 16);
            html.append("<input type='datetime-local' id='dateTime' name='dateTime' value='").append(dateTimeValue).append("' required>");
            html.append("</div>");
            
            html.append("<div class='form-group'>");
            html.append("<label for='location'>Venue</label>");
            html.append("<input type='text' id='location' name='location' value='").append(event.getLocation()).append("' required>");
            html.append("</div>");
            
            html.append("<div class='form-group'>");
            html.append("<label for='description'>Description</label>");
            html.append("<textarea id='description' name='description' required>").append(event.getDescription()).append("</textarea>");
            html.append("</div>");
            
            html.append("<div class='buttons'>");
            html.append("<button type='submit' class='btn btn-primary'>Update Event</button>");
            html.append("<a href='/api/events'><button type='button' class='btn btn-secondary'>Cancel</button></a>");
            html.append("</div>");
            html.append("</form>");
            
            html.append("<div id='result' class='result'></div>");
            
            html.append("</div>");
            html.append("<footer>");
            html.append("<p>Wedding Planning Application &copy; " + java.time.Year.now().getValue() + " | A simple tool to make your special day perfect</p>");
            html.append("</footer>");
            
            html.append("<script>");
            html.append("document.getElementById('editEventForm').addEventListener('submit', function(e) {");
            html.append("  e.preventDefault();");
            html.append("  const eventId = document.getElementById('eventId').value;");
            html.append("  const formData = {");
            html.append("    name: document.getElementById('name').value,");
            html.append("    dateTime: document.getElementById('dateTime').value,");
            html.append("    location: document.getElementById('location').value,");
            html.append("    description: document.getElementById('description').value");
            html.append("  };");
            
            html.append("  fetch('/api/events/' + eventId, {");
            html.append("    method: 'PUT',");
            html.append("    headers: {");
            html.append("      'Content-Type': 'application/json'");
            html.append("    },");
            html.append("    body: JSON.stringify(formData)");
            html.append("  })");
            html.append("  .then(response => {");
            html.append("    if (response.ok) {");
            html.append("      return response.json();");
            html.append("    }");
            html.append("    throw new Error('Network response was not ok.');");
            html.append("  })");
            html.append("  .then(data => {");
            html.append("    document.getElementById('result').className = 'result success';");
            html.append("    document.getElementById('result').innerHTML = 'Event updated successfully!';");
            html.append("    setTimeout(() => { window.location.href = '/api/events'; }, 2000);");
            html.append("  })");
            html.append("  .catch(error => {");
            html.append("    document.getElementById('result').className = 'result error';");
            html.append("    document.getElementById('result').innerHTML = 'Error updating event: ' + error.message;");
            html.append("  });");
            html.append("});");
            html.append("</script>");
            
            html.append("</body>");
            html.append("</html>");
            
            return ResponseEntity.ok(html.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        }
    }
}