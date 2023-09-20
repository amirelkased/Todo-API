package com.elkased.todoapi.service;

import com.elkased.todoapi.dao.TodoDAO;
import com.elkased.todoapi.exception.NotFoundException;
import com.elkased.todoapi.model.TodoDto;
import com.elkased.todoapi.model.entity.Todo;
import com.elkased.todoapi.util.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    @Qualifier("TodoJpaDao")
    TodoDAO todoDAO;

    public List<TodoDto> getAllTodo() {

        List<Todo> todoList = todoDAO.findAllTodo(getActiveUsername());
        return todoList.stream().map(TodoMapper::mapToDto).collect(Collectors.toList());
    }

    public TodoDto createTodo(TodoDto todoDto) {

        Todo todo = TodoMapper.mapToNewEntity(todoDto);
        todo.setUsername(getActiveUsername());

        Todo result = todoDAO.saveTodo(todo);
        return TodoMapper.mapToDto(result);
    }

    public TodoDto updateTodo(TodoDto todoDto) {

        if (!todoDAO.isExistsTodo(todoDto.getId())) {
            String message = "Todo with id [%d] not exists!".formatted(todoDto.getId());
            throw new NotFoundException(message);
        }

        Todo todo = TodoMapper.mapToUpdatedEntity(todoDto);
        Todo result = todoDAO.updateTodo(todo);

        return TodoMapper.mapToDto(result);
    }

    public void removeTodo(long id) {
        todoDAO.deleteTodo(id);
    }

    private String getActiveUsername() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal == null ? null : principal.getUsername();
    }
}
