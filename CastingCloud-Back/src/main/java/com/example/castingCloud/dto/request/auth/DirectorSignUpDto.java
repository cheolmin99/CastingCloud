package com.example.castingCloud.dto.request.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DirectorSignUpDto {
    @NotBlank
    @Email
    @Length(max = 254)
    private String directorEmail;
    
    @NotBlank
    @Length(min = 8, max = 20)
    private String directorPassword;
    
    @NotBlank
    @Length(min = 10, max = 11)
    private int directorPhoneNumber;
    
    @NotBlank
    @Length(min = 3, max = 20)
    private String directorName;
    
    @NotBlank
    @Length(min = 3, max = 100)
    private String directorCompany;
}
