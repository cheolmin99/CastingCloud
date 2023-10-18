package com.example.castingCloud.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.castingCloud.common.ResponseMessage;
import com.example.castingCloud.dto.request.auth.ActorSignInDto;
import com.example.castingCloud.dto.request.auth.ActorSignUpDto;
import com.example.castingCloud.dto.request.auth.DirectorSignInDto;
import com.example.castingCloud.dto.request.auth.DirectorSignUpDto;
import com.example.castingCloud.dto.response.ResponseDto;
import com.example.castingCloud.dto.response.auth.ActorSignInResponseDto;
import com.example.castingCloud.dto.response.auth.ActorSignUpResponseDto;
import com.example.castingCloud.dto.response.auth.DirectorSignInResponseDto;
import com.example.castingCloud.dto.response.auth.DirectorSignUpResponseDto;
import com.example.castingCloud.entity.ActorEntity;
import com.example.castingCloud.entity.DirectorEntity;
import com.example.castingCloud.provider.TokenProvider;
import com.example.castingCloud.repository.ActorRepository;
import com.example.castingCloud.repository.DirectorRepository;
import com.example.castingCloud.service.AuthService;

@Service
public class AuthServiceImplements implements AuthService {
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private ActorRepository actorRepository;


    private static final String DEFAULT_NICKNAME = "user";

    private static final String E = null;
    
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseDto<ActorSignUpResponseDto> actorSignUp(ActorSignUpDto dto) {
        ActorSignUpResponseDto data = null;

        String actorEmail = dto.getActorEmail();
        String actorPassword = dto.getActorPassword();
        String actorNickName = dto.getActorNickName();

        try {
            boolean hasActorEmail = actorRepository.existsByActorEmail(actorEmail);
            if(hasActorEmail) return ResponseDto.setFailed(ResponseMessage.EXIST_EMAIL);

            String encodePassword = passwordEncoder.encode(actorPassword);
            dto.setActorPassword(encodePassword);

            if (actorNickName == null || actorNickName.isEmpty()) {
                actorNickName = DEFAULT_NICKNAME;
                }
            
            ActorEntity actorEntity = new ActorEntity(dto);
            actorEntity.setActorNickName(generateUniqueNickname(actorNickName));
            actorRepository.save(actorEntity);

            

        }catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    private String generateUniqueNickname(String baseNickname) {
        String newActorNickName = baseNickname;
        int suffix = 1;

        while (actorRepository.existsByActorNickName(newActorNickName)) {
            newActorNickName = baseNickname + suffix;
            suffix++;
        }

        return newActorNickName;
    }

    @Autowired
    private DirectorRepository directorRepository; 

    public ResponseDto<DirectorSignUpResponseDto> directorSignUp(DirectorSignUpDto dto) {
        DirectorSignUpResponseDto data = null;

        String directorEmail = dto.getDirectorEmail();
        String directorPassword = dto.getDirectorPassword();
        String directorName = dto.getDirectorName();
        String directorPhoneNumber = dto.getDirectorPhoneNumber();
        String directorCompany = dto.getDirectorCompany();
        
        try {
            boolean hasDirectorEmail = directorRepository.existsByDirectorEmail(directorEmail);
            if(hasDirectorEmail) return ResponseDto.setFailed(ResponseMessage.EXIST_EMAIL);

            String encodePassword  = passwordEncoder.encode(directorPassword);
            dto.setDirectorPassword(encodePassword);

            boolean hasDirectorName = directorRepository.existsByDirectorName(directorName);
            if(hasDirectorName) return ResponseDto.setFailed(ResponseMessage.EXIST_NAME);

            boolean hasDirectorPhoneNumber = directorRepository.existsByDirectorPhoneNumber(directorPhoneNumber);
            if(hasDirectorPhoneNumber)  return ResponseDto.setFailed(ResponseMessage.EXIST_PHONE_NUMBER);

            boolean hasDirectorCompany = directorRepository.existsByDirectorCompany(directorCompany);
            if(hasDirectorCompany) return ResponseDto.setFailed(ResponseMessage.EXIST_COMPANY);

            DirectorEntity directorEntity = new DirectorEntity(dto);
            directorRepository.save(directorEntity);

            data = new DirectorSignUpResponseDto(true);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<ActorSignInResponseDto> actorSignIn(ActorSignInDto dto) {
        ActorSignInResponseDto data = null;

        String actorEmail = dto.getActorEmail();
        String actorPassword = dto.getActorPassword();

        ActorEntity actorEntity = null;

        try {
            actorEntity = actorRepository.findByActorEmail(actorEmail);
            if (actorEmail == null)
            return ResponseDto.setFailed(ResponseMessage.FAIL_SIGN_IN);

            boolean isEqualPassword = passwordEncoder.matches(actorPassword, actorEntity.getActorPassword());
            if (!isEqualPassword)
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        try {
            String token = tokenProvider.create(actorEmail);
            data = new ActorSignInResponseDto(actorEntity, token);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<DirectorSignInResponseDto> directorSignIn(DirectorSignInDto dto) {
        DirectorSignInResponseDto data = null;

        String directorEmail = dto.getDirectorEmail();
        String directorPassword = dto.getDirectorPassword();

        DirectorEntity directorEntity = null;

        try {
            directorEntity = directorRepository.findByDirectorEmail(directorEmail);
            if (directorEmail == null)
            return ResponseDto.setFailed(ResponseMessage.FAIL_SIGN_IN);

            boolean isEqualPassword = passwordEncoder.matches(directorPassword, directorEntity.getDirectorPassword());
            if (!isEqualPassword)
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        try {
            String token = tokenProvider.create(directorEmail);
            data = new DirectorSignInResponseDto(directorEntity, token);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
}
