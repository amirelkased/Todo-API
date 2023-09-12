package com.elkased.todoapi.service;

import com.elkased.todoapi.dao.TodoDAO;
import com.elkased.todoapi.dto.TodoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    @Qualifier("TodoJpaDao")
    TodoDAO todoDAO;

    public List<TodoDTO> getAllTodo() {
        return todoDAO.findAllTodo();
    }

    public TodoDTO createTodo(TodoDTO todoDTO) {
        return todoDAO.saveTodo(todoDTO);
    }
}
