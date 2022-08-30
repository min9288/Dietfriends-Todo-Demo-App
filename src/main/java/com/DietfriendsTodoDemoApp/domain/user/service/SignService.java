package com.DietfriendsTodoDemoApp.domain.user.service;

import com.DietfriendsTodoDemoApp.domain.user.dto.requestDTO.TokenRequestDTO;
import com.DietfriendsTodoDemoApp.domain.user.dto.requestDTO.UserLoginRequestDTO;
import com.DietfriendsTodoDemoApp.domain.user.dto.requestDTO.UserRegisterRequestDTO;
import com.DietfriendsTodoDemoApp.domain.user.dto.responseDTO.TokenResponseDTO;
import com.DietfriendsTodoDemoApp.domain.user.dto.responseDTO.UserLoginResponseDTO;
import com.DietfriendsTodoDemoApp.domain.user.dto.responseDTO.UserRegisterResponseDTO;
import com.DietfriendsTodoDemoApp.domain.user.entity.User;
import com.DietfriendsTodoDemoApp.domain.user.repository.UserRepository;
import com.DietfriendsTodoDemoApp.exception.InvalidRefreshTokenException;
import com.DietfriendsTodoDemoApp.exception.LoginFailureException;
import com.DietfriendsTodoDemoApp.exception.UserNameAlreadyExistsException;
import com.DietfriendsTodoDemoApp.exception.UserNotFoundException;
import com.DietfriendsTodoDemoApp.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SignService {

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    // 회원가입 구현
    @Transactional
    public UserRegisterResponseDTO registerUser(UserRegisterRequestDTO requestDto) {
        validateDuplicated(requestDto.getUserName());
        User user = userRepository.save(
                User.builder()
                        .userName(requestDto.getUserName())
                        .password(passwordEncoder.encode(requestDto.getPassword()))
                        .age(requestDto.getAge())
                        .build());
        return UserRegisterResponseDTO.builder()
                .userName(user.getUserName())
                .age(user.getAge())
                .build();
    }

    public void validateDuplicated(String userName) {
        if (userRepository.findByUserName(userName).isPresent()) throw new UserNameAlreadyExistsException();
    }

    // 로그인 구현
    @Transactional
    public UserLoginResponseDTO loginUser(UserLoginRequestDTO requestDto) {
        User user = userRepository.findByUserName(requestDto.getUserName()).orElseThrow(UserNotFoundException::new);
        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword()))
            throw new LoginFailureException();
        user.updateRefreshToken(jwtTokenProvider.createRefreshToken());
        return new UserLoginResponseDTO(user.getUserUUID(), jwtTokenProvider.createToken(requestDto.getUserName()), user.getRefreshToken());
    }

    // 토큰 재발행
    @Transactional
    public TokenResponseDTO reIssue(TokenRequestDTO requestDto) {
        if(!jwtTokenProvider.validateTokenExpiration(requestDto.getRefreshToken()))
            throw new InvalidRefreshTokenException();
        User user = findUserByToken(requestDto);

        if(!user.getRefreshToken().equals(requestDto.getRefreshToken()))
            throw new InvalidRefreshTokenException();
        String accessToken = jwtTokenProvider.createToken(user.getUserName());
        String refreshToken = jwtTokenProvider.createRefreshToken();
        user.updateRefreshToken(refreshToken);
        return new TokenResponseDTO(accessToken, refreshToken);
    }

    public User findUserByToken(TokenRequestDTO requestDto) {
        Authentication auth = jwtTokenProvider.getAuthentication(requestDto.getAccessToken());
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String userName = userDetails.getUsername();
        return userRepository.findByUserName(userName).orElseThrow(UserNotFoundException::new);
    }


}
