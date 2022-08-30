package com.DietfriendsTodoDemoApp.domain.todo.service;

import com.DietfriendsTodoDemoApp.domain.todo.dto.requestDTO.TodoAddRequestDTO;
import com.DietfriendsTodoDemoApp.domain.todo.dto.requestDTO.TodoUpdateRequestDTO;
import com.DietfriendsTodoDemoApp.domain.todo.dto.responseDTO.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface TodoService {

    // Todo 등록
    TodoAddResponseDTO addTodo(TodoAddRequestDTO requestDTO);

    // Todo 수정
    TodoUpdateResponseDTO updateTodo(UUID todoUUID, TodoUpdateRequestDTO requestDTO);

    // Todo 전체조회
    List<TodoListGetResponseDTO> findMyTodoList();

    // Todo 선택조회
    TodoGetResponseDTO findMyTodo(UUID todoUUID);

    // Todo 삭제
    TodoDeleteResponseDTO deleteMyTodo(UUID todoUUID);
}
