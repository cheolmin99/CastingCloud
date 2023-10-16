package com.example.castingCloud.dto.request.admin;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminSignInDto {
    @NotBlank
    @Email
    @Length(max = 254)
    private String adminEmail;

    @NotBlank
    @Length(min = 8, max = 20)
    private String adminPassword;
}
