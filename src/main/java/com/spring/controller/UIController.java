package com.spring.controller;

import com.spring.model.entity.Creator;
import com.spring.model.entity.Organizer;
import com.spring.model.entity.Task;
import com.spring.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UIController {

    private final TaskService taskService;

    @GetMapping()
    public String index(Model model) {
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @GetMapping("/addtask")
    public String create(Model model) {
        model.addAttribute("task", new Task());
        return "create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("task") Task task) throws IOException {
        task.setCreator(new Creator(null, "any@gmail.com", true));
        task.setOrganizer(new Organizer(null, "any@gmail.com", true));
        task.setEventType("default");
        taskService.create(task);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("task") Task task) throws IOException {
        task.setCreator(new Creator(null, "any@gmail.com", true));
        task.setOrganizer(new Organizer(null, "any@gmail.com", true));
        task.setEventType("default");
        taskService.update(task, task.getId());
        return "redirect:/";
    }

    @GetMapping("/getTask")
    public String update(@RequestParam("taskId") String id, Model model) {
        model.addAttribute("task", taskService.findById(id));
        return "update";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("taskId") String id) throws IOException {
        taskService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("sync")
    public String sync() throws IOException {
        taskService.syncOffline();
        return "redirect:/";

    }


}
