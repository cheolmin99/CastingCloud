package com.example.castingCloud.dto.request.actor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorValidateEmailDto {
    @NotBlank
    @Email
    @Length(max = 40)
    private String actorEmail;
}
