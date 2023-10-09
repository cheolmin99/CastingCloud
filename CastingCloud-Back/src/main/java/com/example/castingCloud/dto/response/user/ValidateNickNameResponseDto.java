package com.example.castingCloud.dto.response.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ValidateNickNameResponseDto {
    private boolean actorNickNameVliad;
    private boolean directorNameValid;

    public ValidateNickNameResponseDto(boolean actorNickNameVliad, boolean directorNameValid) {
        this.actorNickNameVliad = actorNickNameVliad;
        this.directorNameValid = directorNameValid;
    }
}
