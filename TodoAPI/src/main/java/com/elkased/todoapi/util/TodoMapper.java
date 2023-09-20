package com.elkased.todoapi.util;

import com.elkased.todoapi.model.TodoDto;
import com.elkased.todoapi.model.entity.Todo;

import java.time.LocalDateTime;

public class TodoMapper {

    private TodoMapper() {
    }

    public static Todo mapToNewEntity(TodoDto todoDto) {
        return Todo.builder()
                .title(todoDto.getTitle())
                .description(todoDto.getDescription())
                .complete(todoDto.isComplete())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static Todo mapToUpdatedEntity(TodoDto todoDto) {
        return Todo.builder()
                .id(todoDto.getId())
                .title(todoDto.getTitle())
                .description(todoDto.getDescription())
                .complete(todoDto.isComplete())
                .timestamp(todoDto.getTimestamp())
                .build();
    }

    public static TodoDto mapToDto(Todo todo) {
        return TodoDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .complete(todo.isComplete())
                .timestamp(todo.getTimestamp())
                .build();
    }
}
