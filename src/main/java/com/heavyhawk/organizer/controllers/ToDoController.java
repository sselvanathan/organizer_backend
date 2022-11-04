package com.heavyhawk.organizer.controllers;

import com.heavyhawk.organizer.models.Todo;
import com.heavyhawk.organizer.repositories.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {
    final TodoRepository todoRepository;

    public ToDoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @PostMapping("/create")
    public void createTodo(@RequestBody Todo todo) {
        todoRepository.save(todo);
    }

    @GetMapping("/{todoId}")
    public Todo readTodo(@PathVariable Long todoId) {
        Optional<Todo> todo = todoRepository.findById(todoId);
        if (todo.isPresent()) {
            return todo.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo with this id was not found");
    }

    @PutMapping("/{todoId}")
    public void updateTodo(@PathVariable Long todoId, @RequestBody Todo todoUpdate) {
        Optional<Todo> todo = todoRepository.findById(todoId);
        if (todo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo with this id was not found");
        }
        Todo todoInstance = todo.get();
        todoInstance.setName(todoUpdate.getName());
        todoInstance.setUserId(todoUpdate.getUserId());
        todoRepository.save(todoInstance);
    }

    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable Long todoId) {
        Optional<Todo> todo = todoRepository.findById(todoId);
        if (todo.isPresent()) {
            todoRepository.deleteById(todoId);
            return;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo with this id was not found");
    }
}
