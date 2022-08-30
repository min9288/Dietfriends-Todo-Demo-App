package com.DietfriendsTodoDemoApp.domain.todo.dto.responseDTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoDeleteResponseDTO {

    private String successMSG;

    @Builder
    public TodoDeleteResponseDTO(String successMSG) {
        this.successMSG = successMSG;
    }
}
