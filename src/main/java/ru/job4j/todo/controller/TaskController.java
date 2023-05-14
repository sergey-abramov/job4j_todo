package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

@Controller
@AllArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping({"/", "tasks/list"})
    public String getAll(Model model) {
        var list = service.findAll();
        if (list.isEmpty()) {
            model.addAttribute("message", "Задач нет");
            return "tasks/list";
        }
        model.addAttribute("tasks", service.findAll());
        return "tasks/list";
    }

    @GetMapping("tasks/list_new")
    public String getNew(Model model) {
        var list = service.findByDone(false);
        if (list.isEmpty()) {
            model.addAttribute("message", "Новых задач нет");
            return "tasks/list";
        }
        model.addAttribute("tasks", list);
        return "tasks/list";
    }

    @GetMapping("tasks/list_done")
    public String getDone(Model model) {
        var list = service.findByDone(true);
        if (list.isEmpty()) {
            model.addAttribute("message", "Выполненных задач нет");
            return "tasks/list";
        }
        model.addAttribute("tasks", list);
        return "tasks/list";
    }

    @GetMapping("tasks/one/{id}")
    public String getOne(@PathVariable int id, Model model) {
        var task = service.findById(id);
        if (task.isEmpty()) {
            model.addAttribute("message", "Что-то пошло не так");
            return "errors/404";
        }
        model.addAttribute("task", task.get());
        return "tasks/one";
    }

    @GetMapping("tasks/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        var delete = service.delete(id);
        if (!delete) {
            model.addAttribute("message", "Что-то пошло не так");
            return "errors/404";
        }
        return "redirect:/";
    }

    @GetMapping("tasks/getUpdate/{id}")
    public String getUpdate(@PathVariable int id, Model model) {
        var task = service.findById(id);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            return "tasks/update";
        }
        model.addAttribute("message", "Что-то пошло не так");
        return "errors/404";
    }

    @PostMapping("tasks/update")
    public String update(@ModelAttribute Task task, Model model) {
        if (service.update(task)) {
            return "redirect:/";
        }
        model.addAttribute("message", "Что-то пошло не так");
        return "errors/404";
    }

    @GetMapping("tasks/updateDone/{id}")
    public String updateDone(@PathVariable int id, Model model) {
        if (service.updateDone(id)) {
            return "redirect:/";
        }
        model.addAttribute("message", "Что-то пошло не так");
        return "errors/404";
    }
}
