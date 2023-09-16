package com.elkased.todoapi.repository;

import com.elkased.todoapi.dto.TodoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoDTO, Long> {

    boolean existsTodoDTOById(long id);

    List<TodoDTO> findAllByUsername(String username);
}
