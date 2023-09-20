package com.elkased.todoapi.dao;

import com.elkased.todoapi.model.entity.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoDAO {
    List<Todo> findAllTodo(String username);

    Todo saveTodo(Todo todo);

    Todo updateTodo(Todo todo);

    boolean isExistsTodo(long id);

    void deleteTodo(long id);
}
