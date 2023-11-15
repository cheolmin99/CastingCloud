package com.example.castingCloud.service;

import com.example.castingCloud.dto.request.user.ValidateEmailDto;
import com.example.castingCloud.dto.request.user.ValidateNickNameDto;
import com.example.castingCloud.dto.response.ResponseDto;
import com.example.castingCloud.dto.response.user.GetActorResponseDto;
import com.example.castingCloud.dto.response.user.GetDirectorResponseDto;
import com.example.castingCloud.dto.response.user.ValidateEmailResponseDto;
import com.example.castingCloud.dto.response.user.ValidateNickNameResponseDto;

public interface UserService {
    public ResponseDto<GetActorResponseDto> getActor(String actorEmail);

    public ResponseDto<GetDirectorResponseDto> getDirector(String directorEmail);

    public ResponseDto<ValidateEmailResponseDto> validateEmail(ValidateEmailDto dto);

    public ResponseDto<ValidateNickNameResponseDto> validateNickName(ValidateNickNameDto dto);

}
