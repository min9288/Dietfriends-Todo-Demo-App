package com.DietfriendsTodoDemoApp.domain.todo.reository;

import com.DietfriendsTodoDemoApp.domain.todo.entity.Todo;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

import static com.DietfriendsTodoDemoApp.domain.todo.entity.QTodo.todo;
import static com.DietfriendsTodoDemoApp.domain.user.entity.QUser.user;

public class TodoCustomRepositoryImpl implements TodoCustomRepository{

    JPAQueryFactory jpaQueryFactory;

    public TodoCustomRepositoryImpl(EntityManager em) {
        jpaQueryFactory = new JPAQueryFactory(em);
    }
    @Override
    public List<Todo> findAllMytodo(UUID userUUID) {
        List<Todo> todoList = jpaQueryFactory.selectFrom(todo)
                .where(todo.user.userUUID.eq(userUUID))
                .leftJoin(todo.user, user)
                .fetch();
        return todoList;
    }

    @Override
    public Long deleteTodoByTodoUUID(UUID todoUUID) {
        Long delete = jpaQueryFactory.delete(todo)
                .where(todo.todoUUID.eq(todoUUID))
                .execute();
        return delete;
    }
}
