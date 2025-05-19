package com.example.weddingPlanning.service;

import com.example.weddingPlanning.model.Event;
import com.example.weddingPlanning.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event eventDetails) {
        Optional<Event> optionalEvent = getEventById(id);
        if (optionalEvent.isPresent()) {
            Event existingEvent = optionalEvent.get();
            existingEvent.setName(eventDetails.getName());
            existingEvent.setDateTime(eventDetails.getDateTime());
            existingEvent.setLocation(eventDetails.getLocation());
            existingEvent.setDescription(eventDetails.getDescription());
            return eventRepository.save(existingEvent);
        } else {
            return null; // Or throw an exception
        }
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}