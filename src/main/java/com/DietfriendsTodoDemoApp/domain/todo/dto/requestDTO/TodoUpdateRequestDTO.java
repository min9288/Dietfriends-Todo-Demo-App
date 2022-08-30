package com.DietfriendsTodoDemoApp.domain.todo.dto.requestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TodoUpdateRequestDTO {
    private String name;
    private Boolean completed;

}
