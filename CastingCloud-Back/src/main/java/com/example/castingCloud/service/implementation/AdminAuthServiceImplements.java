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
            boolean hasAdminEmail = adminRepository.exexistsByAdminEmail(adminEmail);
            if(hasAdminEmail) return ResponseDto.setFailed(ResponseMessage.EXIST_EMAIL);

            String encodePassword = passwordEncoder.encode(adminPassword);
            dto.setAdminPassword(encodePassword);

            boolean hasAdminName = adminRepository.exexistsByAdminName(adminName);
            if(hasAdminName) return ResponseDto.setFailed(ResponseMessage.EXIST_NAME);

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
