package com.elkased.todoapi.repository;

import com.elkased.todoapi.dto.TodoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TodoRepository extends JpaRepository<TodoDTO, UUID> {

}
