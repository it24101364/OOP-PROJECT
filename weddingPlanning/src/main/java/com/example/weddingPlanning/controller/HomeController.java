package com.example.weddingPlanning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "<!DOCTYPE html>" +
               "<html lang='en'>" +
               "<head>" +
               "    <meta charset='UTF-8'>" +
               "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
               "    <title>Wedding Planning Application</title>" +
               "    <link href='https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Roboto:wght@300;400;500&display=swap' rel='stylesheet'>" +
               "    <style>" +
               "        :root {" +
               "            --primary-color: #e8b7d4;" +
               "            --secondary-color: #a5d8ff;" +
               "            --accent-color: #6a0572;" +
               "            --text-color: #333333;" +
               "            --light-color: #ffffff;" +
               "            --background-color: #f9f7f9;" +
               "        }" +
               "        * { margin: 0; padding: 0; box-sizing: border-box; }" +
               "        body {" +
               "            font-family: 'Roboto', sans-serif;" +
               "            color: var(--text-color);" +
               "            background-color: var(--background-color);" +
               "            line-height: 1.6;" +
               "        }" +
               "        .container {" +
               "            max-width: 1200px;" +
               "            margin: 0 auto;" +
               "            padding: 0 20px;" +
               "        }" +
               "        header {" +
               "            background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));" +
               "            color: var(--light-color);" +
               "            text-align: center;" +
               "            padding: 80px 20px;" +
               "            margin-bottom: 40px;" +
               "            border-radius: 0 0 30px 30px;" +
               "            box-shadow: 0 4px 12px rgba(0,0,0,0.1);" +
               "        }" +
               "        h1 {" +
               "            font-family: 'Playfair Display', serif;" +
               "            font-size: 3rem;" +
               "            margin-bottom: 20px;" +
               "            color: var(--accent-color);" +
               "        }" +
               "        .tagline {" +
               "            font-size: 1.2rem;" +
               "            margin-bottom: 30px;" +
               "            color: var(--accent-color);" +
               "            font-weight: 300;" +
               "        }" +
               "        .features {" +
               "            display: flex;" +
               "            flex-wrap: wrap;" +
               "            justify-content: center;" +
               "            gap: 30px;" +
               "            margin-bottom: 50px;" +
               "        }" +
               "        .feature-card {" +
               "            background-color: var(--light-color);" +
               "            border-radius: 15px;" +
               "            padding: 30px;" +
               "            width: 300px;" +
               "            text-align: center;" +
               "            box-shadow: 0 5px 15px rgba(0,0,0,0.05);" +
               "            transition: transform 0.3s ease, box-shadow 0.3s ease;" +
               "        }" +
               "        .feature-card:hover {" +
               "            transform: translateY(-10px);" +
               "            box-shadow: 0 15px 30px rgba(0,0,0,0.1);" +
               "        }" +
               "        .feature-icon {" +
               "            font-size: 2.5rem;" +
               "            margin-bottom: 20px;" +
               "            color: var(--primary-color);" +
               "        }" +
               "        .feature-title {" +
               "            font-size: 1.5rem;" +
               "            margin-bottom: 15px;" +
               "            color: var(--accent-color);" +
               "        }" +
               "        .cta-buttons {" +
               "            display: flex;" +
               "            justify-content: center;" +
               "            gap: 20px;" +
               "            margin: 40px 0;" +
               "        }" +
               "        .btn {" +
               "            display: inline-block;" +
               "            padding: 15px 30px;" +
               "            border-radius: 50px;" +
               "            text-decoration: none;" +
               "            font-weight: 500;" +
               "            font-size: 1.1rem;" +
               "            transition: all 0.3s ease;" +
               "            cursor: pointer;" +
               "            border: none;" +
               "        }" +
               "        .btn-primary {" +
               "            background-color: var(--accent-color);" +
               "            color: var(--light-color);" +
               "            box-shadow: 0 4px 12px rgba(106, 5, 114, 0.3);" +
               "        }" +
               "        .btn-primary:hover {" +
               "            background-color: #7d0685;" +
               "            transform: translateY(-3px);" +
               "            box-shadow: 0 6px 15px rgba(106, 5, 114, 0.4);" +
               "        }" +
               "        .btn-secondary {" +
               "            background-color: var(--light-color);" +
               "            color: var(--accent-color);" +
               "            border: 2px solid var(--accent-color);" +
               "        }" +
               "        .btn-secondary:hover {" +
               "            background-color: var(--accent-color);" +
               "            color: var(--light-color);" +
               "            transform: translateY(-3px);" +
               "        }" +
               "        footer {" +
               "            text-align: center;" +
               "            padding: 30px 0;" +
               "            color: #777;" +
               "            font-size: 0.9rem;" +
               "            margin-top: 50px;" +
               "            border-top: 1px solid #eee;" +
               "        }" +
               "        @media (max-width: 768px) {" +
               "            h1 { font-size: 2.5rem; }" +
               "            .features { flex-direction: column; align-items: center; }" +
               "            .cta-buttons { flex-direction: column; align-items: center; }" +
               "            .btn { width: 80%; text-align: center; }" +
               "        }" +
               "    </style>" +
               "</head>" +
               "<body>" +
               "    <header>" +
               "        <div class='container'>" +
               "            <h1>Wedding Planning</h1>" +
               "            <p class='tagline'>Organize your perfect day with ease and elegance</p>" +
               "        </div>" +
               "    </header>" +
               "    <main class='container'>" +
               "        <div class='features'>" +
               "            <div class='feature-card'>" +
               "                <div class='feature-icon'>📅</div>" +
               "                <h2 class='feature-title'>Event Management</h2>" +
               "                <p>Create and manage all your wedding events in one place. From ceremony to reception, keep everything organized.</p>" +
               "            </div>" +
               "            <div class='feature-card'>" +
               "                <div class='feature-icon'>📍</div>" +
               "                <h2 class='feature-title'>Venue Tracking</h2>" +
               "                <p>Keep track of venue details, locations, and important information for each of your wedding events.</p>" +
               "            </div>" +
               "            <div class='feature-card'>" +
               "                <div class='feature-icon'>📝</div>" +
               "                <h2 class='feature-title'>Detailed Planning</h2>" +
               "                <p>Add comprehensive descriptions and notes to ensure no detail is overlooked in your wedding planning.</p>" +
               "            </div>" +
               "        </div>" +
               "        <div class='cta-buttons'>" +
               "            <a href='/api/events' class='btn btn-primary'>View Your Events</a>" +
               "            <a href='/create-event' class='btn btn-secondary'>Create New Event</a>" +
               "        </div>" +
               "    </main>" +
               "    <footer class='container'>" +
               "        <p>Wedding Planning Application &copy; " + java.time.Year.now().getValue() + " | A simple tool to make your special day perfect</p>" +
               "    </footer>" +
               "</body>" +
               "</html>";
    }

    @GetMapping("/health")
    @ResponseBody
    public String health() {
        return "Application is running!";
    }
    
    @GetMapping("/create-event")
    @ResponseBody
    public String createEventForm() {
        return "<!DOCTYPE html>" +
               "<html lang='en'>" +
               "<head>" +
               "    <meta charset='UTF-8'>" +
               "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
               "    <title>Create New Event - Wedding Planning</title>" +
               "    <link href='https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700&family=Roboto:wght@300;400;500&display=swap' rel='stylesheet'>" +
               "    <style>" +
               "        :root {" +
               "            --primary-color: #e8b7d4;" +
               "            --secondary-color: #a5d8ff;" +
               "            --accent-color: #6a0572;" +
               "            --text-color: #333333;" +
               "            --light-color: #ffffff;" +
               "            --background-color: #f9f7f9;" +
               "        }" +
               "        * { margin: 0; padding: 0; box-sizing: border-box; }" +
               "        body {" +
               "            font-family: 'Roboto', sans-serif;" +
               "            color: var(--text-color);" +
               "            background-color: var(--background-color);" +
               "            line-height: 1.6;" +
               "            padding: 20px;" +
               "        }" +
               "        .container {" +
               "            max-width: 800px;" +
               "            margin: 0 auto;" +
               "            padding: 30px;" +
               "            background-color: var(--light-color);" +
               "            border-radius: 15px;" +
               "            box-shadow: 0 5px 15px rgba(0,0,0,0.05);" +
               "        }" +
               "        h1 {" +
               "            font-family: 'Playfair Display', serif;" +
               "            font-size: 2.5rem;" +
               "            margin-bottom: 30px;" +
               "            color: var(--accent-color);" +
               "            text-align: center;" +
               "        }" +
               "        .form-group {" +
               "            margin-bottom: 20px;" +
               "        }" +
               "        label {" +
               "            display: block;" +
               "            margin-bottom: 8px;" +
               "            font-weight: 500;" +
               "            color: var(--accent-color);" +
               "        }" +
               "        input, textarea {" +
               "            width: 100%;" +
               "            padding: 12px;" +
               "            border: 1px solid #ddd;" +
               "            border-radius: 8px;" +
               "            font-family: 'Roboto', sans-serif;" +
               "            font-size: 1rem;" +
               "        }" +
               "        textarea {" +
               "            min-height: 120px;" +
               "            resize: vertical;" +
               "        }" +
               "        .buttons {" +
               "            display: flex;" +
               "            justify-content: space-between;" +
               "            margin-top: 30px;" +
               "        }" +
               "        .btn {" +
               "            display: inline-block;" +
               "            padding: 12px 25px;" +
               "            border-radius: 50px;" +
               "            text-decoration: none;" +
               "            font-weight: 500;" +
               "            font-size: 1rem;" +
               "            transition: all 0.3s ease;" +
               "            cursor: pointer;" +
               "            border: none;" +
               "        }" +
               "        .btn-primary {" +
               "            background-color: var(--accent-color);" +
               "            color: var(--light-color);" +
               "            box-shadow: 0 4px 12px rgba(106, 5, 114, 0.3);" +
               "        }" +
               "        .btn-primary:hover {" +
               "            background-color: #7d0685;" +
               "            transform: translateY(-3px);" +
               "            box-shadow: 0 6px 15px rgba(106, 5, 114, 0.4);" +
               "        }" +
               "        .btn-secondary {" +
               "            background-color: var(--light-color);" +
               "            color: var(--accent-color);" +
               "            border: 2px solid var(--accent-color);" +
               "        }" +
               "        .btn-secondary:hover {" +
               "            background-color: var(--accent-color);" +
               "            color: var(--light-color);" +
               "            transform: translateY(-3px);" +
               "        }" +
               "        .result {" +
               "            margin-top: 20px;" +
               "            padding: 15px;" +
               "            border-radius: 8px;" +
               "        }" +
               "        .success {" +
               "            background-color: #dff0d8;" +
               "            color: #3c763d;" +
               "        }" +
               "        .error {" +
               "            background-color: #f2dede;" +
               "            color: #a94442;" +
               "        }" +
               "        footer {" +
               "            text-align: center;" +
               "            padding: 20px 0;" +
               "            color: #777;" +
               "            font-size: 0.9rem;" +
               "            margin-top: 40px;" +
               "        }" +
               "    </style>" +
               "</head>" +
               "<body>" +
               "    <div class='container'>" +
               "        <h1>Create New Event</h1>" +
               "        <form id='eventForm'>" +
               "            <div class='form-group'>" +
               "                <label for='name'>Event Name</label>" +
               "                <input type='text' id='name' name='name' required>" +
               "            </div>" +
               "            <div class='form-group'>" +
               "                <label for='dateTime'>Date and Time</label>" +
               "                <input type='datetime-local' id='dateTime' name='dateTime' required>" +
               "            </div>" +
               "            <div class='form-group'>" +
               "                <label for='location'>Venue</label>" +
               "                <input type='text' id='location' name='location' required>" +
               "            </div>" +
               "            <div class='form-group'>" +
               "                <label for='description'>Description</label>" +
               "                <textarea id='description' name='description' required></textarea>" +
               "            </div>" +
               "            <div class='buttons'>" +
               "                <button type='submit' class='btn btn-primary'>Create Event</button>" +
               "                <a href='/'><button type='button' class='btn btn-secondary'>Cancel</button></a>" +
               "            </div>" +
               "        </form>" +
               "        <div id='result' class='result'></div>" +
               "    </div>" +
               "    <footer>" +
               "        <p>Wedding Planning Application &copy; " + java.time.Year.now().getValue() + " | A simple tool to make your special day perfect</p>" +
               "    </footer>" +
               "    <script>" +
               "        document.getElementById('eventForm').addEventListener('submit', function(e) {" +
               "            e.preventDefault();" +
               "            const formData = {" +
               "                name: document.getElementById('name').value," +
               "                dateTime: document.getElementById('dateTime').value," +
               "                location: document.getElementById('location').value," +
               "                description: document.getElementById('description').value" +
               "            };" +
               "            fetch('/api/events', {" +
               "                method: 'POST'," +
               "                headers: {" +
               "                    'Content-Type': 'application/json'" +
               "                }," +
               "                body: JSON.stringify(formData)" +
               "            })" +
               "            .then(response => {" +
               "                if (response.ok) {" +
               "                    return response.json();" +
               "                }" +
               "                throw new Error('Network response was not ok.');" +
               "            })" +
               "            .then(data => {" +
               "                document.getElementById('result').className = 'result success';" +
               "                document.getElementById('result').innerHTML = 'Event created successfully!';" +
               "                document.getElementById('eventForm').reset();" +
               "                setTimeout(() => { window.location.href = '/api/events'; }, 2000);" +
               "            })" +
               "            .catch(error => {" +
               "                document.getElementById('result').className = 'result error';" +
               "                document.getElementById('result').innerHTML = 'Error creating event: ' + error.message;" +
               "            });" +
               "        });" +
               "    </script>" +
               "</body>" +
               "</html>";
    }

    @GetMapping("/edit-event")
    @ResponseBody
    public String editEventForm(@RequestParam Long id) {
        // Redirect to the EventController's edit endpoint
        return "<script>window.location.href = '/api/events/edit/" + id + "';</script>";
    }
}