package com.example.castingCloud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.castingCloud.common.ApiPattern;
import com.example.castingCloud.dto.request.auth.ActorSignInDto;
import com.example.castingCloud.dto.request.auth.ActorSignUpDto;
import com.example.castingCloud.dto.request.auth.DirectorSignInDto;
import com.example.castingCloud.dto.request.auth.DirectorSignUpDto;
import com.example.castingCloud.dto.response.ResponseDto;
import com.example.castingCloud.dto.response.auth.ActorSignInResponseDto;
import com.example.castingCloud.dto.response.auth.ActorSignUpResponseDto;
import com.example.castingCloud.dto.response.auth.DirectorSignInResponseDto;
import com.example.castingCloud.dto.response.auth.DirectorSignUpResponseDto;
import com.example.castingCloud.service.AuthService;

@RestController
@RequestMapping(ApiPattern.AUTH)
public class AuthController {
    @Autowired
    private AuthService authService;

    private final String ACTOR_SIGN_UP = "/actor-sign-up";
    private final String ACTOR_SIGN_IN = "/actor-sign-in";
    
    @PostMapping(ACTOR_SIGN_UP)
    public ResponseDto<ActorSignUpResponseDto> actorSignUp(@Valid @RequestBody ActorSignUpDto requestBody) {
        ResponseDto<ActorSignUpResponseDto> response = authService.actorSignUp(requestBody);
        return response;
    }

    @PostMapping(ACTOR_SIGN_IN)
    public ResponseDto<ActorSignInResponseDto> actorSignIn(@RequestBody ActorSignInDto requestBody) {
        ResponseDto<ActorSignInResponseDto> response = authService.actorSignIn(requestBody);
        return response;
    }

    private final String DIRECTOR_SIGN_UP = "/director-sign-up";
    private final String DIRECTOR_SIGN_IN = "/director-sign-in";

    @PostMapping(DIRECTOR_SIGN_UP)
    public ResponseDto<DirectorSignUpResponseDto> directorSignUp(@Valid @RequestBody DirectorSignUpDto requestBody) {
        ResponseDto<DirectorSignUpResponseDto> response = authService.directorSignUp(requestBody);
        return response;
    }

     @PostMapping(DIRECTOR_SIGN_IN)
    public ResponseDto<DirectorSignInResponseDto> directorSignIn(@RequestBody DirectorSignInDto requestBody) {
        ResponseDto<DirectorSignInResponseDto> response = authService.directorSignIn(requestBody);
        return response;
    }
}
