package com.spring.controller;

import com.spring.model.entity.Task;
import com.spring.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/task")
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) throws GeneralSecurityException, IOException {
        return taskService.create(task);
    }

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/task")
    @ResponseStatus(HttpStatus.OK)
    public Task findById(@RequestParam String id) {
        return taskService.findById(id);
    }

    @GetMapping("/task/{offline}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> findByIsOffline(@PathVariable boolean offline) {
        return taskService.findByIsOffline(offline);
    }

    @PutMapping("/task/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task update(@RequestBody Task task, @PathVariable String id) throws IOException {
        return taskService.update(task, id);
    }

    @DeleteMapping("/task/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable String id) throws IOException {
        return taskService.deleteById(id);
    }

    @GetMapping("/task/sync")
    @ResponseStatus(HttpStatus.OK)
    public String sync() throws IOException {
        return taskService.syncOffline();
    }
}