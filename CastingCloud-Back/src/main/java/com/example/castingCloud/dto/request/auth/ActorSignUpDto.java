package com.example.castingCloud.dto.request.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorSignUpDto {
    @NotBlank
    @Email
    @Length(max = 254)
    private String actorEmail;

    @NotBlank
    @Length(min = 8, max = 20)
    private String actorPassword;

    @NotBlank
    @Length(min = 3, max = 20)
    private String actorNickName;
}
