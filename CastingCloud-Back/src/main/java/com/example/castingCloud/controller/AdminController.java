package com.example.castingCloud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.castingCloud.common.ApiPattern;
import com.example.castingCloud.dto.request.admin.AdminSignInDto;
import com.example.castingCloud.dto.request.admin.AdminSignUpDto;
import com.example.castingCloud.dto.response.ResponseDto;
import com.example.castingCloud.dto.response.admin.AdminSignInResponseDto;
import com.example.castingCloud.dto.response.admin.AdminSignUpResponseDto;
import com.example.castingCloud.service.AdminAuthService;

@RestController
@RequestMapping(ApiPattern.ADMIN)
public class AdminController {
    @Autowired
    private AdminAuthService adminAuthService;

    private final String ADMIN_SIGN_UP = "/sign-up";
    private final String ADMIN_SIGN_IN = "/sign-in";

    @PostMapping(ADMIN_SIGN_UP)
    public ResponseDto<AdminSignUpResponseDto> signUp(@Valid @RequestBody AdminSignUpDto requestBody) {
        ResponseDto<AdminSignUpResponseDto> response = adminAuthService.adminSignUp(requestBody);
        return response;
    }

    @PostMapping(ADMIN_SIGN_IN)
    public ResponseDto<AdminSignInResponseDto> signIn(@RequestBody AdminSignInDto requestBody) {
        ResponseDto<AdminSignInResponseDto> response = adminAuthService.adminSignIn(requestBody);
        return response;
    }
}
