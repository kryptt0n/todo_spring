package com.vitaly.firstwebapp.todo;

import org.springframework.cglib.core.Local;
import org.springframework.cglib.core.Predicate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {

    private static List<ToDo> todos = new ArrayList<>();
    private static int taskCounter = 0;

    static {
        todos.add(new ToDo(++taskCounter, "vitaly", "spring", LocalDate.now().plusMonths(1), false));
        todos.add(new ToDo(++taskCounter, "vitaly", "databases", LocalDate.now().plusMonths(2), false));
        todos.add(new ToDo(++taskCounter, "vitaly", "operation systems", LocalDate.now().plusMonths(3), false));

    }

    public List<ToDo> findByUsername(String username) {
        return todos.stream().filter(toDo -> toDo.getUsername().equalsIgnoreCase(username)).toList();
    }

    public void addTask(ToDo toDo) {
        addTask(toDo.getUsername(), toDo.getDescription(), toDo.getTargetDate(), toDo.isDone());
    }

    public void addTask(String username, String description, LocalDate targetDate, boolean done) {
        ToDo toDo = new ToDo(++taskCounter, username, description, targetDate, done);
        todos.add(toDo);
    }

    public void deleteTask(int id){
        todos.removeIf(todo -> todo.getId() == id);
    }

    public ToDo findById(int id) {
        return todos.stream().filter(toDo -> toDo.getId()==id).findFirst().get();
    }

    public void update(ToDo toDo) {
        deleteTask(toDo.getId());
        todos.add(toDo);
    }
}
