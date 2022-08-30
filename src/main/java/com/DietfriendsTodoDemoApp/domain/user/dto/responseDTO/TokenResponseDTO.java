package com.DietfriendsTodoDemoApp.domain.user.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponseDTO {
    String accessToken;
    String refreshToken;
}
