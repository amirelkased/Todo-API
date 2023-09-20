package com.elkased.todoapi.repository;

import com.elkased.todoapi.model.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    boolean existsTodoDTOById(long id);

    List<Todo> findAllByUsername(String username);
}
