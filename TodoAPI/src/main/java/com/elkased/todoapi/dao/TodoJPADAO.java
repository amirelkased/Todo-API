package com.elkased.todoapi.dao;


import com.elkased.todoapi.exception.InvalidArgumentException;
import com.elkased.todoapi.exception.NoChangesFoundException;
import com.elkased.todoapi.exception.NotFoundException;
import com.elkased.todoapi.model.entity.Todo;
import com.elkased.todoapi.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "TodoJpaDao")
public class TodoJPADAO implements TodoDAO {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> findAllTodo(String username) {
        return todoRepository.findAllByUsername(username);
    }

    @Override
    public Todo saveTodo(Todo todo) {
        try {

            return todoRepository.save(todo);
        } catch (Exception e) {
            String message = "Make Sure all fields is provided";
            throw new InvalidArgumentException(message);
        }
    }

    @Override
    public Todo updateTodo(Todo todo) {

        Todo oldOne = todoRepository.findById(todo.getId())
                .orElseThrow(() -> new NotFoundException("This is Todo id [%d] not exists".formatted(todo.getId())));

        boolean changes = false;

        if (todo.getTitle() != null && !oldOne.getTitle().equals(todo.getTitle())) {
            oldOne.setTitle(todo.getTitle());
            changes = true;
        }

        if (todo.getDescription() != null && !oldOne.getDescription().equals(todo.getDescription())) {
            oldOne.setDescription(todo.getDescription());
            changes = true;
        }

        if (oldOne.isComplete() != todo.isComplete()) {
            oldOne.setComplete(todo.isComplete());
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