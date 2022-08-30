package com.DietfriendsTodoDemoApp.domain.todo.dto.responseDTO;

import com.DietfriendsTodoDemoApp.domain.todo.entity.Todo;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TodoListGetResponseDTO {

    private UUID todoUUID;
    private String name;
    private Boolean completed;
    private LocalDateTime completedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static TodoListGetResponseDTO createDTO(Todo todo) {
        return TodoListGetResponseDTO.builder()
                .todoUUID(todo.getTodoUUID())
                .name(todo.getName())
                .completed(todo.getCompleted())
                .completedAt(todo.getCompletedAt())
                .createdAt(todo.getCreatedAt())
                .updatedAt(todo.getUpdatedAt())
                .build();
    }

    @Builder
    public TodoListGetResponseDTO(UUID todoUUID, String name, Boolean completed, LocalDateTime completedAt, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.todoUUID = todoUUID;
        this.name = name;
        this.completed = completed;
        this.completedAt = completedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
