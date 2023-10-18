package com.example.castingCloud.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.castingCloud.common.ResponseMessage;
import com.example.castingCloud.dto.request.admin.AdminSignInDto;
import com.example.castingCloud.dto.request.admin.AdminSignUpDto;
import com.example.castingCloud.dto.response.ResponseDto;
import com.example.castingCloud.dto.response.admin.AdminSignInResponseDto;
import com.example.castingCloud.dto.response.admin.AdminSignUpResponseDto;
import com.example.castingCloud.entity.AdminEntity;
import com.example.castingCloud.provider.TokenProvider;
import com.example.castingCloud.repository.AdminRepository;
import com.example.castingCloud.service.AdminAuthService;

@Service
public class AdminAuthServiceImplements implements AdminAuthService {
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AdminRepository adminRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseDto<AdminSignUpResponseDto> adminSignUp(AdminSignUpDto dto) {
        AdminSignUpResponseDto data = null;

        String adminEmail = dto.getAdminEmail();
        String adminPassword = dto.getAdminPassword();
        String adminName = dto.getAdminName();

        try {
            boolean hasAdminEmail = adminRepository.existsByAdminEmail(adminEmail);
            if(hasAdminEmail) return ResponseDto.setFailed(ResponseMessage.EXIST_EMAIL);

            boolean hasAdminName = adminRepository.existsByAdminName(adminName);
            if(hasAdminName) return ResponseDto.setFailed(ResponseMessage.EXIST_NAME);
            
            String encodePassword = passwordEncoder.encode(adminPassword);
            dto.setAdminPassword(encodePassword);

            AdminEntity adminEntity = new AdminEntity(dto);
            adminRepository.save(adminEntity);

            data = new AdminSignUpResponseDto(true);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<AdminSignInResponseDto> adminSignIn(AdminSignInDto dto) {
        AdminSignInResponseDto data = null;

        String adminEmail = dto.getAdminEmail();
        String adminPassword = dto.getAdminPassword();

        AdminEntity adminEntity = null;
        
        try {
            adminEntity = adminRepository.findByAdminEmail(adminEmail);
            if(adminEmail == null)
            return ResponseDto.setFailed(ResponseMessage.FAIL_SIGN_IN);

            boolean isEqualPassword = passwordEncoder.matches(adminPassword, adminEntity.getAdminPassword());
            if(!isEqualPassword)
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        
        try {
            String token = tokenProvider.create(adminEmail);
            data = new AdminSignInResponseDto(adminEntity, token);
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
