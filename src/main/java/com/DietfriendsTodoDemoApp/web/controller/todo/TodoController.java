package com.DietfriendsTodoDemoApp.web.controller.todo;

import com.DietfriendsTodoDemoApp.domain.response.result.MultipleResult;
import com.DietfriendsTodoDemoApp.domain.response.result.SingleResult;
import com.DietfriendsTodoDemoApp.domain.response.service.ResponseService;
import com.DietfriendsTodoDemoApp.domain.todo.dto.requestDTO.TodoAddRequestDTO;
import com.DietfriendsTodoDemoApp.domain.todo.dto.requestDTO.TodoUpdateRequestDTO;
import com.DietfriendsTodoDemoApp.domain.todo.dto.responseDTO.*;
import com.DietfriendsTodoDemoApp.domain.todo.service.TodoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {

    private final TodoServiceImpl todoService;
    private final ResponseService responseService;

    // todo 등록
    @PostMapping("")
    public SingleResult<TodoAddResponseDTO> addTodo(@Valid @RequestBody TodoAddRequestDTO requestDto) {
        TodoAddResponseDTO responseDto = todoService.addTodo(requestDto);
        return responseService.getSingleResult(responseDto);
    }

    // todo 수정
    @PutMapping("/{todoUUID}")
    public SingleResult<TodoUpdateResponseDTO> updateTodo(@PathVariable("todoUUID") UUID todoUUID,
                                                        @Valid @RequestBody TodoUpdateRequestDTO requestDto) {
        return responseService.getSingleResult(todoService.updateTodo(todoUUID, requestDto));
    }

    // 내 todo 전체조회
    @GetMapping("")
    public MultipleResult<TodoListGetResponseDTO> findMyTodoList() {
        List<TodoListGetResponseDTO> responseDto = todoService.findMyTodoList();
        return responseService.getMultipleResult(responseDto);
    }

    // 내 todo 선택조회
    @GetMapping("/{todoUUID}")
    public SingleResult<TodoGetResponseDTO> findMyTodo(@PathVariable("todoUUID") UUID todoUUID) {
        TodoGetResponseDTO responseDto = todoService.findMyTodo(todoUUID);
        return responseService.getSingleResult(responseDto);
    }

    // todo 삭제
    @DeleteMapping("{todoUUID}")
    public SingleResult<TodoDeleteResponseDTO> deleteMyTodo(@PathVariable("todoUUID") UUID todoUUID) {
        TodoDeleteResponseDTO responseDto = todoService.deleteMyTodo(todoUUID);
        return responseService.getSingleResult(responseDto);
    }
}
