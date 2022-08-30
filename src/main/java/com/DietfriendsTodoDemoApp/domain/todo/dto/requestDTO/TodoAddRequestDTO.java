package com.DietfriendsTodoDemoApp.domain.todo.dto.requestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoAddRequestDTO {
    private String name;
    private Boolean completed;
}
