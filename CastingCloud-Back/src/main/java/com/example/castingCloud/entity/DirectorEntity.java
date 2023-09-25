package com.example.castingCloud.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.castingCloud.dto.request.auth.DirectorSignUpDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "director")
@Table(name = "director")
@Data
public class DirectorEntity {
    @Id
    private int directorId;
    private String directorEmail;
    private String directorPassword;
    private String directorPhoneNumber;
    private String directorName;
    private String directorCompany;
    private String directorProfile;

    public DirectorEntity(DirectorSignUpDto dto) {
        this.directorEmail = getDirectorEmail();
        this.directorPassword = getDirectorPassword();
        this.directorPhoneNumber = getDirectorPhoneNumber();
        this.directorName = getDirectorName();
        this.directorCompany = getDirectorCompany();
    }
}
