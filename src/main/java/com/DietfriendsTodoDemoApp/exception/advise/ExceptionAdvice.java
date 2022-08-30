package com.DietfriendsTodoDemoApp.exception.advise;

import com.DietfriendsTodoDemoApp.domain.response.result.Result;
import com.DietfriendsTodoDemoApp.domain.response.service.ResponseService;
import com.DietfriendsTodoDemoApp.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(AuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result authenticationEntryPointException() {
        return responseService.getFailureResult(-401, "인증 실패에 따른 예외가 발생했습니다.");
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result invalidRefreshTokenException() {
        return responseService.getFailureResult(-401, "Refresh Token이 유효하지 않습니다.");
    }

    @ExceptionHandler(LoginFailureException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result loginFailureException() {
        return responseService.getFailureResult(-400, "아이디 혹은 비밀번호가 틀립니다.");
    }

    @ExceptionHandler(UserNameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result userNameAlreadyExistsException() {
        return responseService.getFailureResult(-400, "이미 존재하는 회원명 입니다.");
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result userNotFoundException() {
        return responseService.getFailureResult(-404, "회원정보를 찾을 수 없습니다.");
    }

    @ExceptionHandler(ProcessFailureException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result processFailureException() {
        return responseService.getFailureResult(-400, "처리 과정에 오류가 발생했습니다.");
    }

    @ExceptionHandler(TodoNotfoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result todoNotfoundException() {
        return responseService.getFailureResult(-404, "TODO를 찾을 수 없습니다.");
    }

    @ExceptionHandler(UserNotWriterException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result userNotWriterException() {
        return responseService.getFailureResult(-401, "작성자가 아닙니다. 본인이 작성한 TODO만 이용할 수 있습니다.");
    }

    @ExceptionHandler(TodoDeleteFailureException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result todoDeleteFailureException() {
        return responseService.getFailureResult(-400, "삭제에 실패 했습니다.");
    }




}

