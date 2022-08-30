package com.DietfriendsTodoDemoApp.domain.user.dto.requestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginRequestDTO {
    private String userName;
    private String password;
}
