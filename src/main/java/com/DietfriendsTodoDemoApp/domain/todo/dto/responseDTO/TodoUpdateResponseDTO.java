package com.DietfriendsTodoDemoApp.domain.todo.dto.responseDTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TodoUpdateResponseDTO {
    private UUID todoUUID;
    private String name;
    private Boolean completed;
    private LocalDateTime completedAt;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;

    @Builder
    public TodoUpdateResponseDTO(UUID todoUUID, String name, Boolean completed, LocalDateTime completedAt,
                              LocalDateTime createAt, LocalDateTime updatedAt){
        this.todoUUID = todoUUID;
        this.name = name;
        this.completed = completed;
        this.completedAt = completedAt;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
    }
}
