package com.example.castingCloud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.castingCloud.common.ApiPattern;
import com.example.castingCloud.dto.request.user.ValidateEmailDto;
import com.example.castingCloud.dto.request.user.ValidateNickNameDto;
import com.example.castingCloud.dto.response.ResponseDto;
import com.example.castingCloud.dto.response.user.GetActorResponseDto;
import com.example.castingCloud.dto.response.user.GetDirectorResponseDto;
import com.example.castingCloud.dto.response.user.ValidateEmailResponseDto;
import com.example.castingCloud.dto.response.user.ValidateNickNameResponseDto;
import com.example.castingCloud.service.UserService;

@RestController
@RequestMapping(ApiPattern.USER)
public class UserController {
    @Autowired
    private UserService userService;

    private final String VALIDATE_EMAIL = "/validate/email";
    private final String VALIDATE_NAME = "/validate/name";
    private final String GET_ACTOR = "/actor";
    private final String GET_DIRECTOR = "/director";

    @GetMapping(GET_ACTOR)
    public ResponseDto<GetActorResponseDto> getActor(@AuthenticationPrincipal String actorEmail) {
        ResponseDto<GetActorResponseDto> response = userService.getActor(actorEmail);
        return response;
    }

    @GetMapping(GET_DIRECTOR)
    public ResponseDto<GetDirectorResponseDto> getDirector(@AuthenticationPrincipal String directorEmail) {
        ResponseDto<GetDirectorResponseDto> response = userService.getDirector(directorEmail);
        return response;
    }

    @PostMapping(VALIDATE_EMAIL)
    public ResponseDto<ValidateEmailResponseDto> validateEmail(@Valid @RequestBody ValidateEmailDto requestBody) {
        ResponseDto<ValidateEmailResponseDto> response = userService.validateEmail(requestBody);
        return(response);
    }

    @PostMapping(VALIDATE_NAME)
    public ResponseDto<ValidateNickNameResponseDto> validateNickName(@Valid @RequestBody ValidateNickNameDto requestBody) {
        ResponseDto<ValidateNickNameResponseDto> response = userService.validateNickName(requestBody);
        return(response);
    }
}
