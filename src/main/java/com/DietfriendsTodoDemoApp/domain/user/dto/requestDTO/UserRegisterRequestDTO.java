package com.DietfriendsTodoDemoApp.domain.user.dto.requestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterRequestDTO {
    private String userName;
    private String password;
    private Integer age;
}
