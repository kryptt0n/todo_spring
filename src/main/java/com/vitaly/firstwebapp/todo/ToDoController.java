package com.vitaly.firstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//@Controller
@SessionAttributes("name")
public class ToDoController {
    private ToDoService service;

    public ToDoController(ToDoService service) {
        this.service = service;
    }

    @RequestMapping("/todo-list")
    public String listAllToDos(ModelMap model) {
        List<ToDo> toDos = service.findByUsername(getUserName());
        model.put("toDos", toDos);
        return "toDoList";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showAddToDoPage(ModelMap model) {
        ToDo todo = new ToDo();
        model.put("todo", todo);
        return "toDo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addToDo(ModelMap model, @Valid @ModelAttribute("todo") ToDo toDo, BindingResult result) {

        if (result.hasErrors()) {
            return "toDo";
        }

        String username = getUserName();
        service.addTask(username, toDo.getDescription(), LocalDate.now().plusMonths(6), false);
        return "redirect:todo-list";
    }

    @RequestMapping(value = "/delete-todo")
    public String removeToDo(@RequestParam int id) {
        service.deleteTask(id);
        return "redirect:todo-list";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateToDoPage(@RequestParam int id, ModelMap model) {
        ToDo todo = service.findById(id);
        model.put("todo", todo);
        return "toDo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateToDo(ModelMap model, @Valid @ModelAttribute("todo") ToDo toDo, BindingResult result) {

        if (result.hasErrors()) {
            return "toDo";
        }

        String username = String.valueOf(getUserName());
        toDo.setUsername(username);
        service.update(toDo);
        return "redirect:todo-list";
    }

    private String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
