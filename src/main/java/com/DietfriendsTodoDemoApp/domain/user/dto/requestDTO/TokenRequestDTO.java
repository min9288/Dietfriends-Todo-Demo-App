package com.DietfriendsTodoDemoApp.domain.user.dto.requestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenRequestDTO {

    String accessToken;
    String refreshToken;

}
