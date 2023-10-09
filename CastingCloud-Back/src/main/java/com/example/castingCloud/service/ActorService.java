package com.example.castingCloud.service;

import com.example.castingCloud.dto.request.actor.ActorValidateEmailDto;
import com.example.castingCloud.dto.request.actor.ActorValidateNickNameDto;
import com.example.castingCloud.dto.response.ResponseDto;
import com.example.castingCloud.dto.response.actor.ActorValidateEmailResponseDto;
import com.example.castingCloud.dto.response.actor.ActorValidateNickNameResponseDto;

public interface ActorService {
    public ResponseDto<ActorValidateEmailResponseDto> actorValidateEmail(ActorValidateEmailDto dto);

    public ResponseDto<ActorValidateNickNameResponseDto> actorValidateNickName(ActorValidateNickNameDto dto);
    
}
