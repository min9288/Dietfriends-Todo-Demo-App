package com.DietfriendsTodoDemoApp.domain.todo.reository;

import com.DietfriendsTodoDemoApp.domain.todo.entity.Todo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodoCustomRepository {

    List<Todo> findAllMytodo(UUID userUUID);

    @Transactional
    Long deleteTodoByTodoUUID(UUID todoUUID);

}
