package com.example.castingCloud.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.castingCloud.common.ResponseMessage;
import com.example.castingCloud.dto.request.user.ValidateEmailDto;
import com.example.castingCloud.dto.request.user.ValidateNickNameDto;
import com.example.castingCloud.dto.response.ResponseDto;
import com.example.castingCloud.dto.response.user.GetUserResponseDto;
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

    public ResponseDto<GetUserResponseDto> getUser(String actorEmail, String directorEmail) {
        GetUserResponseDto data = null;

        try {
            ActorEntity actorEntity = actorRepository.findByActorEmail(actorEmail);
            DirectorEntity directorEntity = directorRepository.findByDirectorEmail(directorEmail);

        if(actorEntity == null && directorEntity == null)
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            //directorEntity 에서 값을 못 받아오는중 왜인지 모르겠음
        
        data = new GetUserResponseDto();
        
        if(actorEntity != null) {
            data = new GetUserResponseDto(actorEntity);
            data.setActor(true);
        }
        
        if(directorEntity != null) {
            data = new GetUserResponseDto(directorEntity);
            data.setDirector(true);
        }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

}
