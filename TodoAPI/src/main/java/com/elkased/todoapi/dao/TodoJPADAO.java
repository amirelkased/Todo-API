package com.elkased.todoapi.dao;


import com.elkased.todoapi.dto.TodoDTO;
import com.elkased.todoapi.exception.InvalidArgumentException;
import com.elkased.todoapi.exception.NoChangesFoundException;
import com.elkased.todoapi.exception.NotFoundException;
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

    @Override
    public TodoDTO updateTodo(TodoDTO todoDTO) {

        TodoDTO oldOne = todoRepository.findById(todoDTO.getId()).get();

        boolean changes = false;

        if (todoDTO.getTitle() != null && !oldOne.getTitle().equals(todoDTO.getTitle())) {
            oldOne.setTitle(todoDTO.getTitle());
            changes = true;
        }

        if (todoDTO.getDescription() != null && !oldOne.getDescription().equals(todoDTO.getDescription())) {
            oldOne.setDescription(todoDTO.getDescription());
            changes = true;
        }

        if (oldOne.isComplete() != todoDTO.isComplete()) {
            oldOne.setComplete(todoDTO.isComplete());
            changes = true;
        }

        if (!changes) {
            String message = "Their isn't any changes founded";
            throw new NoChangesFoundException(message);
        }

        return todoRepository.save(oldOne);
    }

    @Override
    public boolean isExistsTodo(long id) {
        return todoRepository.existsTodoDTOById(id);
    }

    @Override
    public void deleteTodo(long id) {
        try {
            if (!isExistsTodo(id)) {
                throw new NotFoundException("No todo with this id [%d]".formatted(id));
            }
            todoRepository.deleteById(id);
        } catch (IllegalArgumentException ex) {

            String message = "Id must not be null";
            throw new InvalidArgumentException(message);
        }
    }
}