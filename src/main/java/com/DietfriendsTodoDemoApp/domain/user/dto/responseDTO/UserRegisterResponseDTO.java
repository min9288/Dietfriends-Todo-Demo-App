package com.DietfriendsTodoDemoApp.domain.user.dto.responseDTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterResponseDTO {
    private String userName;
    private Integer age;

    @Builder
    public UserRegisterResponseDTO(String userName, String password, Integer age){
        this.userName = userName;
        this.age = age;
    }
}
