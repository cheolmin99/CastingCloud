package com.example.castingCloud.service;

import com.example.castingCloud.dto.request.admin.AdminSignUpDto;
import com.example.castingCloud.dto.request.admin.AdminSignInDto;
import com.example.castingCloud.dto.response.ResponseDto;
import com.example.castingCloud.dto.response.admin.AdminSignUpResponseDto;
import com.example.castingCloud.dto.response.admin.AdminSignInResponseDto;

public interface AdminAuthService {
    public ResponseDto<AdminSignUpResponseDto> adminSignUp(AdminSignUpDto dto);
    
    public ResponseDto<AdminSignInResponseDto> adminSignIn(AdminSignInDto dto);
}
