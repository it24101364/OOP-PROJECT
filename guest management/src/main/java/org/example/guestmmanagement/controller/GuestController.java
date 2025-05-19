package org.example.guestmmanagement.controller;

import org.example.guestmmanagement.model.Guest;
import org.example.guestmmanagement.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/guests")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @GetMapping
    public String listGuests(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String group,
            @RequestParam(required = false) String status,
            Model model) {

        List<Guest> filteredGuests = guestService.getAllGuests().stream()
                .filter(g -> search == null || search.isEmpty()
                        || (g.getName() != null && g.getName().toLowerCase().contains(search.toLowerCase()))
                        || (g.getEmail() != null && g.getEmail().toLowerCase().contains(search.toLowerCase()))
                        || (g.getGroup() != null && g.getGroup().toLowerCase().contains(search.toLowerCase())))
                .filter(g -> group == null || group.isEmpty() || (g.getGroup() != null && g.getGroup().equalsIgnoreCase(group)))
                .filter(g -> status == null || status.isEmpty() || (g.getStatus() != null && g.getStatus().equalsIgnoreCase(status)))
                .collect(Collectors.toList());

        model.addAttribute("guests", filteredGuests);
        model.addAttribute("groups", guestService.getAllGroups());
        model.addAttribute("statuses", guestService.getAllStatuses());
        model.addAttribute("newGuest", new Guest());
        return "guests"; // Use guests.html for Thymeleaf
    }

    @PostMapping
    public String addGuest(@ModelAttribute("newGuest") Guest guest) {
        guestService.addGuest(guest);
        return "redirect:/guests";
    }

    @PostMapping("/update")
    public String updateGuest(@ModelAttribute Guest guest) {
        guestService.updateGuest(guest);
        return "redirect:/guests";
    }

    @GetMapping("/delete/{id}")
    public String deleteGuest(@PathVariable String id) {
        guestService.deleteGuest(id);
        return "redirect:/guests";
    }
}
