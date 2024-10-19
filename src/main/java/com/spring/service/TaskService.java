package com.spring.service;

import com.spring.exception.exceptions.RecordNotFoundException;
import com.spring.model.entity.Task;
import com.spring.model.mapper.TaskMapper;
import com.spring.repo.TaskRepo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepo taskRepo;
    private final CalendarService calendarService;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepo taskRepo, CalendarService calendarService, TaskMapper taskMapper) {
        this.taskRepo = taskRepo;
        this.calendarService = calendarService;
        this.taskMapper = taskMapper;
    }


    @Cacheable(key = "#root.methodName", value = "findAllTasks")
    public List<Task> findAll() {
        return taskRepo.findAll();
    }


    public Task findById(String id) {
        return taskRepo.findById(id).orElseThrow(() -> new RecordNotFoundException("Task not found"));
    }

    @CacheEvict(key = "#root.methodName", value = "findAllTasks", allEntries = true)
    public Task create(Task task) throws IOException {
        if (task == null)
            throw new RecordNotFoundException("Task is null");
        try {
            task.setOffline(false);
            calendarService.create(taskMapper.mapTaskToEvent(task));
        } catch (UnknownHostException ex) {
            task.setOffline(true);
        }
        return taskRepo.save(task);
    }


    @CacheEvict(key = "#root.methodName", value = "findAllTasks", allEntries = true)
    public Task update(Task task, String id) throws IOException {
        if (task == null)
            throw new RecordNotFoundException("Task is null");
        task.setId(id);
        try {
            calendarService.update(id, taskMapper.mapTaskToEvent(task));
        } catch (UnknownHostException ex) {
            task.setOffline(true);
        }
        return taskRepo.save(task);
    }

    @CacheEvict(key = "#root.methodName", value = "findAllTasks", allEntries = true)
    public String deleteById(String id) throws IOException {
        if (taskRepo.findById(id).isPresent()) {
            taskRepo.deleteById(id);
            calendarService.delete(id);
            return "Task deleted";
        } else throw new RecordNotFoundException("Task not found");
    }

    public List<Task> findByIsOffline(boolean offline) {
        return taskRepo.findByIsOffline(offline);
    }

    @CacheEvict(key = "#root.methodName", value = "findAllTasks", allEntries = true)
    public String syncOffline() throws IOException {
        for (Task task : findByIsOffline(true)) {
            try {
                calendarService.create(taskMapper.mapTaskToEvent(task));
            } catch (UnknownHostException ex) {
                return "sync failed";
            }
            task.setOffline(false);
            create(task);
        }
        return "sync successful";
    }


}
