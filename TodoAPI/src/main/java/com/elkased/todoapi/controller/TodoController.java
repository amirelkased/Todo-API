package com.elkased.todoapi.controller;

import com.elkased.todoapi.dto.TodoDTO;
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
    public ResponseEntity<List<TodoDTO>> getAllTodos() {

        List<TodoDTO> allTodoResult = todoService.getAllTodo();
        return new ResponseEntity<>(allTodoResult, HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<TodoDTO> createTodo(@Validated @RequestBody TodoDTO todoDTO) {
        TodoDTO result = todoService.createTodo(todoDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping(value = {"", "/"})
    public ResponseEntity<TodoDTO> updateTodo(@Validated @RequestBody TodoDTO todoDTO) {

        TodoDTO result = todoService.updateTodo(todoDTO);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{TodoID}")
    public void deleteTodo(@PathVariable(name = "TodoID") long id) {
        todoService.removeTodo(id);
    }
}
