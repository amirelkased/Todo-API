package com.elkased.todoapi.dao;

import com.elkased.todoapi.dto.TodoDTO;
import com.elkased.todoapi.exception.InvalidArgumentException;
import com.elkased.todoapi.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "TodoJpaDao")
public class TodoJPADAO implements TodoDAO {

    @Autowired
    TodoRepository todoRepository;

    @Override
    public List<TodoDTO> findAllTodo() {
        return todoRepository.findAll();
    }

    @Override
    public TodoDTO saveTodo(TodoDTO todoDTO) {
        try {

            return todoRepository.save(todoDTO);
        } catch (Exception e) {
            String message = "Make Sure all fields is provided";
            throw new InvalidArgumentException(message);
        }
    }
}
