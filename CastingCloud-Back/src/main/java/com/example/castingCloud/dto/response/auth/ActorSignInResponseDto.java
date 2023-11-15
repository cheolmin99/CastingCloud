package com.example.castingCloud.dto.response.auth;

import com.example.castingCloud.entity.ActorEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorSignInResponseDto {
    private String actorEmail;
    private String actorNickName;
    private String actorProfile;

    private String token;

    private int expiredTime;

    public ActorSignInResponseDto(ActorEntity actorEntity, String token) {
        this.actorEmail = actorEntity.getActorEmail();
        this.actorNickName = actorEntity.getActorNickName();
        this.actorProfile = actorEntity.getActorProfile();
        this.token = token;
        this.expiredTime = 60 * 60 * 1000;
    }
}
