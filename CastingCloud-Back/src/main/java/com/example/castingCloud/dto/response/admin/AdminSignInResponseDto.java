package com.example.castingCloud.dto.response.admin;

import com.example.castingCloud.entity.AdminEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminSignInResponseDto {
    private String adminEmail;
    private String adminName;

    private String token;

    private int expriedTime;

    public AdminSignInResponseDto(AdminEntity AdminEntity, String token) {
        this.adminEmail = AdminEntity.getAdminEmail();
        this.adminName = AdminEntity.getAdminName();
        this.token = token;
        this.expriedTime = 3600;
    }
}
