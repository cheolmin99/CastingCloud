package com.example.castingCloud.service;

import com.example.castingCloud.dto.request.auth.ActorSignInDto;
import com.example.castingCloud.dto.request.auth.ActorSignUpDto;
import com.example.castingCloud.dto.request.auth.DirectorSignInDto;
import com.example.castingCloud.dto.request.auth.DirectorSignUpDto;
import com.example.castingCloud.dto.response.ResponseDto;
import com.example.castingCloud.dto.response.auth.ActorSignInResponseDto;
import com.example.castingCloud.dto.response.auth.ActorSignUpResponseDto;
import com.example.castingCloud.dto.response.auth.DirectorSignInResponseDto;
import com.example.castingCloud.dto.response.auth.DirectorSignUpResponseDto;

public interface AuthService {
    public ResponseDto<ActorSignUpResponseDto> actorSignUp(ActorSignUpDto dto);
    
    public ResponseDto<DirectorSignUpResponseDto> directorSignUp(DirectorSignUpDto dto);

    public ResponseDto<ActorSignInResponseDto> actorSignIn(ActorSignInDto dto);

    public ResponseDto<DirectorSignInResponseDto> directorSignIn(DirectorSignInDto dto);
}
