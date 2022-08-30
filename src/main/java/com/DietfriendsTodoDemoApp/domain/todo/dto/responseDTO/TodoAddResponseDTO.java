package com.DietfriendsTodoDemoApp.domain.todo.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TodoAddResponseDTO {
    private UUID todoUUID;
    private String name;
    private Boolean completed;
    private LocalDateTime createdAt;

    @Builder
    public TodoAddResponseDTO(UUID todoUUID, String name, Boolean completed, LocalDateTime createdAt) {
        this.todoUUID = todoUUID;
        this.name = name;
        this.completed = completed;
        this.createdAt = createdAt;
    }
}
