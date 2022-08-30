package com.DietfriendsTodoDemoApp.domain.todo.reository;

import com.DietfriendsTodoDemoApp.domain.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TodoRepository extends JpaRepository<Todo, UUID>,  TodoCustomRepository{

    Optional<Todo> findByTodoUUID(UUID TodoUUID);

}
