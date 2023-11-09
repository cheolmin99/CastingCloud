package com.example.castingCloud.dto.response.user;

import com.example.castingCloud.entity.DirectorEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetDirectorResponseDto {
    private String directorEmail;
    private String directorName;
    private String directorPhoneNumber;
    private String directorCompany;
    private String directorProfile;

    public GetDirectorResponseDto(DirectorEntity directorEntity) {
        this.directorEmail = directorEntity.getDirectorEmail();
        this.directorName = directorEntity.getDirectorName();
        this.directorPhoneNumber = directorEntity.getDirectorPhoneNumber();
        this.directorCompany = directorEntity.getDirectorCompany();
        this.directorProfile = directorEntity.getDirectorProfile();
    }
}
