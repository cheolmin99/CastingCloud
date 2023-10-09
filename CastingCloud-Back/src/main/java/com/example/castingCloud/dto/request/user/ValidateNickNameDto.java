package com.example.castingCloud.dto.request.user;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ValidateNickNameDto {
    @NotBlank
    @Length(min = 3, max = 20)
    private String actorNickName;

    @NotBlank
    @Length(min = 3, max = 20)
    private String directorName;
}
