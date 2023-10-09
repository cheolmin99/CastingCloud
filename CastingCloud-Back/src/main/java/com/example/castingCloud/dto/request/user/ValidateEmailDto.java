package com.example.castingCloud.dto.request.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateEmailDto {
    @NotBlank
    @Email
    @Length(max = 254)
    private String actorEmail;

    @NotBlank
    @Email
    @Length(max = 254)
    private String directorEmail;
}
