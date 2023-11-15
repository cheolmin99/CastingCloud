package com.example.castingCloud.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.castingCloud.common.ResponseMessage;
import com.example.castingCloud.dto.request.user.ValidateEmailDto;
import com.example.castingCloud.dto.request.user.ValidateNickNameDto;
import com.example.castingCloud.dto.response.ResponseDto;
import com.example.castingCloud.dto.response.user.GetActorResponseDto;
import com.example.castingCloud.dto.response.user.GetDirectorResponseDto;
import com.example.castingCloud.dto.response.user.ValidateEmailResponseDto;
import com.example.castingCloud.dto.response.user.ValidateNickNameResponseDto;
import com.example.castingCloud.entity.ActorEntity;
import com.example.castingCloud.entity.DirectorEntity;
import com.example.castingCloud.repository.ActorRepository;
import com.example.castingCloud.repository.DirectorRepository;
import com.example.castingCloud.service.UserService;

@Service
public class UserServiceImplements implements UserService {
    
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private DirectorRepository directorRepository;

    public ResponseDto<ValidateEmailResponseDto> validateEmail(ValidateEmailDto dto) {
        ValidateEmailResponseDto data = null;

        String actorEmail = dto.getActorEmail();
        String directorEmail = dto.getDirectorEmail();

        try {
            boolean hasActorEmail = actorRepository.existsByActorEmail(actorEmail);
            boolean hasDirectorEmail = directorRepository.existsByDirectorEmail(directorEmail);

            boolean isEmailValid = !(hasActorEmail || hasDirectorEmail);

            if (isEmailValid) {
                data = new ValidateEmailResponseDto(true, true);
            } else {
                data = new ValidateEmailResponseDto(false, false);
            }
            // data = new ValidateEmailResponseDto(isEmailValid, isEmailValid);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<ValidateNickNameResponseDto> validateNickName(ValidateNickNameDto dto) {
        ValidateNickNameResponseDto data = null;

        String actorNickName = dto.getActorNickName();
        String directorName = dto.getDirectorName();

        try {
            boolean hasActorNickName = actorRepository.existsByActorNickName(actorNickName);
            boolean hasDirectorName = directorRepository.existsByDirectorName(directorName);

            data = new ValidateNickNameResponseDto(!hasActorNickName, !hasDirectorName);
        } catch(Exception exception) {  
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<GetActorResponseDto> getActor(String actorEmail) {
        GetActorResponseDto data = null;

        try {
            ActorEntity actorEntity = actorRepository.findByActorEmail(actorEmail);
            if(actorEntity == null)
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            data = new GetActorResponseDto(actorEntity);
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<GetDirectorResponseDto> getDirector(String directorEmail) {
        GetDirectorResponseDto data = null;

        try {
            DirectorEntity directorEntity = directorRepository.findByDirectorEmail(directorEmail);
            if(directorEntity == null)
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            data = new GetDirectorResponseDto(directorEntity);
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

}
