package org.example.guestmmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        // This will look for /WEB-INF/jsp/index.html
        return "index"; // Make sure index.html exists!
    }
}
