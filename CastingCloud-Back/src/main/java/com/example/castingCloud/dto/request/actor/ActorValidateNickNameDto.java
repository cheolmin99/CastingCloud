package com.example.castingCloud.dto.request.actor;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ActorValidateNickNameDto {
    @NotBlank
    @Length(min = 3, max = 10)
    private String actorNickName;
}
