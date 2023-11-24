package com.example.castingCloud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int directorId;
    private String directorEmail;
    private String directorPassword;
    private String directorPhoneNumber;
    private String directorName;
    private String directorCompany;
    private String directorProfile;

    public DirectorEntity(DirectorSignUpDto dto) {
        this.directorEmail = dto.getDirectorEmail();
        this.directorPassword = dto.getDirectorPassword();
        this.directorName = dto.getDirectorName();
        this.directorPhoneNumber = dto.getDirectorPhoneNumber();
        this.directorCompany = dto.getDirectorCompany();
    }
}
