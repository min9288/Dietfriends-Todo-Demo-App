package com.DietfriendsTodoDemoApp.web.controller.user;

import com.DietfriendsTodoDemoApp.domain.response.result.SingleResult;
import com.DietfriendsTodoDemoApp.domain.response.service.ResponseService;
import com.DietfriendsTodoDemoApp.domain.user.dto.requestDTO.TokenRequestDTO;
import com.DietfriendsTodoDemoApp.domain.user.dto.requestDTO.UserLoginRequestDTO;
import com.DietfriendsTodoDemoApp.domain.user.dto.requestDTO.UserRegisterRequestDTO;
import com.DietfriendsTodoDemoApp.domain.user.dto.responseDTO.TokenResponseDTO;
import com.DietfriendsTodoDemoApp.domain.user.dto.responseDTO.UserLoginResponseDTO;
import com.DietfriendsTodoDemoApp.domain.user.dto.responseDTO.UserRegisterResponseDTO;
import com.DietfriendsTodoDemoApp.domain.user.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signs")
public class UserController {

    private final SignService signService;
    private final ResponseService responseService;

    // 회원가입
    @PostMapping("/register")
    public SingleResult<UserRegisterResponseDTO> register(@RequestBody UserRegisterRequestDTO requestDto) {
        UserRegisterResponseDTO responseDto = signService.registerUser(requestDto);
        return responseService.getSingleResult(responseDto);
    }

    // 로그인
    @PostMapping("/login")
    public SingleResult<UserLoginResponseDTO> login(@Valid @RequestBody UserLoginRequestDTO requestDto) {
        UserLoginResponseDTO responseDto = signService.loginUser(requestDto);
        return responseService.getSingleResult(responseDto);
    }

    // 토큰 재발행
    @PostMapping("/reissue")
    public SingleResult<TokenResponseDTO> reissue(@Valid @RequestBody TokenRequestDTO tokenRequestDto) {
        TokenResponseDTO responseDto = signService.reIssue(tokenRequestDto);
        return responseService.getSingleResult(responseDto);
    }

}
