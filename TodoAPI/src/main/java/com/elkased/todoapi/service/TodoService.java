package com.elkased.todoapi.service;

import com.elkased.todoapi.dao.TodoDAO;
import com.elkased.todoapi.dto.TodoDTO;
import com.elkased.todoapi.exception.NotFoundException;
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

    public TodoDTO updateTodo(TodoDTO todoDTO) {
        if (!todoDAO.isExistsTodo(todoDTO.getId())) {
            String message = "Todo with id [%d] not exists!".formatted(todoDTO.getId());
            throw new NotFoundException(message);
        }
        return todoDAO.updateTodo(todoDTO);
    }

    public void removeTodo(long id) {
        todoDAO.deleteTodo(id);
    }
}
