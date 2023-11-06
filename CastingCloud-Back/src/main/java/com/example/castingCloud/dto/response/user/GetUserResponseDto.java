package com.example.castingCloud.dto.response.user;

import com.example.castingCloud.entity.ActorEntity;
import com.example.castingCloud.entity.DirectorEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetUserResponseDto {
    private String actorEmail;
    private String actorNickName;
    private String actorProfile;
    private boolean isActor;

    private String directorEmail;
    private String directorName;
    private String directorPhoneNumber;
    private String directorCompany;
    private String directorProfile;
    private boolean isDirector;
    

    public GetUserResponseDto(ActorEntity actorEntity) {
        this.actorEmail = actorEntity.getActorEmail();
        this.actorNickName = actorEntity.getActorNickName();
        this.actorProfile = actorEntity.getActorProfile();
        this.isActor = true;
    }

    public GetUserResponseDto(DirectorEntity directorEntity) {
        this.directorEmail = directorEntity.getDirectorEmail();
        this.directorName = directorEntity.getDirectorName();
        this.directorPhoneNumber = directorEntity.getDirectorPhoneNumber();
        this.directorCompany = directorEntity.getDirectorCompany();
        this.directorProfile = directorEntity.getDirectorProfile();
        this.isDirector = true;
    }

}
