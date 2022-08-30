package com.DietfriendsTodoDemoApp.domain.todo.service;

import com.DietfriendsTodoDemoApp.domain.todo.dto.requestDTO.TodoAddRequestDTO;
import com.DietfriendsTodoDemoApp.domain.todo.dto.requestDTO.TodoUpdateRequestDTO;
import com.DietfriendsTodoDemoApp.domain.todo.dto.responseDTO.*;
import com.DietfriendsTodoDemoApp.domain.todo.entity.Todo;
import com.DietfriendsTodoDemoApp.domain.todo.reository.TodoCustomRepositoryImpl;
import com.DietfriendsTodoDemoApp.domain.todo.reository.TodoRepository;
import com.DietfriendsTodoDemoApp.domain.user.entity.User;
import com.DietfriendsTodoDemoApp.domain.user.repository.UserRepository;
import com.DietfriendsTodoDemoApp.exception.*;
import com.DietfriendsTodoDemoApp.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoCustomRepositoryImpl todoCustomRepository;
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    // todo 추가
    @Override
    @Transactional
    public TodoAddResponseDTO addTodo(TodoAddRequestDTO requestDTO) throws ProcessFailureException {
        Todo todo = todoRepository.save(
                Todo.builder()
                        .name(requestDTO.getName())
                        .completed(requestDTO.getCompleted())
                        .build());
        todo.insertUser(findUser());
        return TodoAddResponseDTO.builder()
                .todoUUID(todo.getTodoUUID())
                .name(todo.getName())
                .completed(todo.getCompleted())
                .createdAt(todo.getCreatedAt())
                .build();
    }

    // todo 수정
    @Override
    @Transactional
    public TodoUpdateResponseDTO updateTodo(UUID todoUUID, TodoUpdateRequestDTO requestDTO) {
        User user = findUser();
        Todo todo = findTodoByTodoUUID(todoUUID);

        // 수정하려는 TODO 작성자와 로그인한 회원이 동일한지 확인
        if(!todo.getUser().getUserUUID().equals(user.getUserUUID()))
            throw new UserNotWriterException();

        todo.setName(requestDTO.getName());
        todo.setCompleted(requestDTO.getCompleted());
        if(requestDTO.getCompleted() == true){
            todo.setCompletedAt(LocalDateTime.now());
        }
        todoRepository.save(todo);

        return TodoUpdateResponseDTO.builder()
                .todoUUID(todo.getTodoUUID())
                .name(todo.getName())
                .completed(todo.getCompleted())
                .completedAt(todo.getCompletedAt())
                .createAt(todo.getCreatedAt())
                .updatedAt(todo.getUpdatedAt())
                .build();
    }

    // 내가 작성한 todo 전체 조회
    @Override
    public List<TodoListGetResponseDTO> findMyTodoList() {
        User user = findUser();

        List<Todo> todos = todoCustomRepository.findAllMytodo(user.getUserUUID());
        return todos.stream()
                .map(todo -> TodoListGetResponseDTO.createDTO(todo))
                .collect(Collectors.toList());
    }

    // 내가 작성한 todo 선택 조회
    @Override
    public TodoGetResponseDTO findMyTodo(UUID todoUUID) {
        Todo todo = findTodoByTodoUUID(todoUUID);
        User user = findUser();
        if(!todo.getUser().getUserUUID().equals(user.getUserUUID()))
            throw new UserNotWriterException();

        return TodoGetResponseDTO.builder()
                .todoUUID(todo.getTodoUUID())
                .name(todo.getName())
                .completed(todo.getCompleted())
                .completedAt(todo.getCompletedAt())
                .createdAt(todo.getCreatedAt())
                .updatedAt(todo.getUpdatedAt())
                .build();
    }

    // todo 삭제
    @Override
    @Transactional
    public TodoDeleteResponseDTO deleteMyTodo(UUID todoUUID) {
        Todo todo = findTodoByTodoUUID(todoUUID);
        User user = findUser();

        if(!todo.getUser().getUserUUID().equals(user.getUserUUID()))
            throw new UserNotWriterException();

        if(todoCustomRepository.deleteTodoByTodoUUID(todoUUID) != 1)
            throw new TodoDeleteFailureException();

        return TodoDeleteResponseDTO.builder()
                .successMSG("삭제 성공")
                .build();
    }

    // 현재 로그인한 유저 정보 조회
    public User findUser() {
        return userRepository.findUserByUserName(SecurityUtil.getLoginUsername()).orElseThrow(UserNotFoundException::new);
    }


    // todoUUID로 todo정보 조회
    public Todo findTodoByTodoUUID(UUID todoUUID) {
        return todoRepository.findByTodoUUID(todoUUID).orElseThrow(TodoNotfoundException::new);
    }


}
