package com.example.castingCloud.dto.response.user;

import com.example.castingCloud.entity.ActorEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetActorResponseDto {
    private String actorEmail;
    private String actorNickName;
    private String actorProfile;

    public GetActorResponseDto(ActorEntity actorEntity) {
        this.actorEmail = actorEntity.getActorEmail();
        this.actorNickName = actorEntity.getActorNickName();
        this.actorProfile = actorEntity.getActorProfile();
    }
}
