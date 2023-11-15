package com.example.castingCloud.dto.response.auth;

import com.example.castingCloud.entity.DirectorEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectorSignInResponseDto {
    private String directorEmail;
    private String directorPhoneNumber;
    private String directorName;
    private String directorCompany;
    private String directorProfile;

    private String token;

    private int expiredTime;

    public DirectorSignInResponseDto(DirectorEntity directorEntity, String token) {
        this.directorEmail = directorEntity.getDirectorEmail();
        this.directorPhoneNumber = directorEntity.getDirectorPhoneNumber();
        this.directorName = directorEntity.getDirectorName();
        this.directorCompany = directorEntity.getDirectorCompany();
        this.directorProfile = directorEntity.getDirectorProfile();
        this.token = token;
        this.expiredTime = 60 * 60 * 1000;
    }
}
