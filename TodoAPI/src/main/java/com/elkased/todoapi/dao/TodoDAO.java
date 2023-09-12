package com.elkased.todoapi.dao;

import com.elkased.todoapi.dto.TodoDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoDAO {
    List<TodoDTO> findAllTodo();

    TodoDTO saveTodo(TodoDTO todoDTO);

    TodoDTO updateTodo(TodoDTO todoDTO);

    boolean isExistsTodo(long id);

    void deleteTodo(long id);
}
