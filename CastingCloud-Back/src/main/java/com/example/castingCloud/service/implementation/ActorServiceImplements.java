package com.example.castingCloud.service.implementation;

import org.springframework.stereotype.Service;

import com.example.castingCloud.common.ResponseMessage;
import com.example.castingCloud.dto.request.actor.ActorValidateEmailDto;
import com.example.castingCloud.dto.request.actor.ActorValidateNickNameDto;
import com.example.castingCloud.dto.response.ResponseDto;
import com.example.castingCloud.dto.response.actor.ActorValidateEmailResponseDto;
import com.example.castingCloud.dto.response.actor.ActorValidateNickNameResponseDto;
import com.example.castingCloud.repository.ActorRepository;
import com.example.castingCloud.service.ActorService;

@Service
public class ActorServiceImplements implements ActorService {
    
    private ActorRepository actorRepository;

    public ResponseDto<ActorValidateEmailResponseDto> actorValidateEmail(ActorValidateEmailDto dto) {
        ActorValidateEmailResponseDto data = null;

        String actorEmail = dto.getActorEmail();

        try {
            boolean hasActorEmail = actorRepository.existsByActorEmail(actorEmail);
            data = new ActorValidateEmailResponseDto(!hasActorEmail);
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<ActorValidateNickNameResponseDto> actorValidateNickName(ActorValidateNickNameDto dto) {
        ActorValidateNickNameResponseDto data = null;

        String actorNickName = dto.getActorNickName();

        try {
            boolean hasActorNickName = actorRepository.existsByActorEmail(actorNickName);
            data = new ActorValidateNickNameResponseDto(!hasActorNickName);
        } catch(Exception exception) {  
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
