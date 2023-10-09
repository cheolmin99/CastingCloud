package com.example.castingCloud.dto.request.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminSignInDto {
    @NotBlank
    @Email
    @Length(max = 40)
    private String adminEmail;

    @NotBlank
    @Length(min = 8, max = 20)
    private String adminPassword;
}
