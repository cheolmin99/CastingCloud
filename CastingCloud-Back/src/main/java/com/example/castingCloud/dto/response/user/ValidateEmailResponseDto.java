package com.example.castingCloud.dto.response.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidateEmailResponseDto {
    private boolean actorEmailValid;
    private boolean directorEmailValid;

    public ValidateEmailResponseDto(boolean actorEmailValid, boolean directorEmailValid) {
        this.actorEmailValid = actorEmailValid;
        this.directorEmailValid = directorEmailValid;
    }
}
