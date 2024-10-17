package com.spring.controller;


import com.google.api.services.calendar.model.Event;
import com.spring.model.entity.Task;
import com.spring.model.mapper.TaskMapper;
import com.spring.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class CalenderController {

    private final CalendarService calendarService;
    private final TaskMapper taskMapper;

    @PostMapping("/events")
    public Event createEvent(@RequestBody Event event) throws  IOException {
        return calendarService.create(event);
    }

    @GetMapping("/events")
    public List<Task> getEvents() throws IOException {
        return taskMapper.mapEventToTask(calendarService.findAll());

    }

    @PutMapping("/events/{id}")
    public Event updateEvent(@PathVariable String id, @RequestBody Event event) throws IOException {
        return calendarService.update(id, (event));
    }

    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable String id) throws IOException {
        calendarService.delete(id);
    }


}
