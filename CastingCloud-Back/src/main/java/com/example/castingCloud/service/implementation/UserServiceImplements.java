package com.example.castingCloud.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.castingCloud.common.ResponseMessage;
import com.example.castingCloud.dto.request.user.ValidateEmailDto;
import com.example.castingCloud.dto.request.user.ValidateNickNameDto;
import com.example.castingCloud.dto.response.ResponseDto;
import com.example.castingCloud.dto.response.user.ValidateEmailResponseDto;
import com.example.castingCloud.dto.response.user.ValidateNickNameResponseDto;
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

            data = new ValidateEmailResponseDto(hasActorEmail, hasDirectorEmail);

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
}
