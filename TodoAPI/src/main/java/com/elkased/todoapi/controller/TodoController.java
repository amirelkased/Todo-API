package com.elkased.todoapi.controller;

import com.elkased.todoapi.model.TodoDto;
import com.elkased.todoapi.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/todos")
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<TodoDto>> getAllTodos() {

        List<TodoDto> allTodoResult = todoService.getAllTodo();
        return new ResponseEntity<>(allTodoResult, HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<TodoDto> createTodo(@Validated @RequestBody TodoDto todoDto) {
        TodoDto result = todoService.createTodo(todoDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping(value = {"", "/"})
    public ResponseEntity<TodoDto> updateTodo(@Validated @RequestBody TodoDto todoDto) {

        TodoDto result = todoService.updateTodo(todoDto);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{TodoID}")
    public void deleteTodo(@PathVariable(name = "TodoID") long id) {
        todoService.removeTodo(id);
    }
}
