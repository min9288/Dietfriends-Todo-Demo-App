package com.DietfriendsTodoDemoApp.domain.user.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponseDTO {
    private UUID userUUID;
    private String token;
    private String refreshToken;
}
