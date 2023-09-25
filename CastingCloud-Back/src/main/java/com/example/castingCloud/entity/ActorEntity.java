package com.example.castingCloud.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.castingCloud.dto.request.auth.ActorSignUpDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "actor")
@Table(name = "actor")
@Data
public class ActorEntity {
    @Id
    private int actorId;
    private String actorEmail;
    private String actorPassword;
    private String actorNickName;
    private String actorProfile;

    public ActorEntity(ActorSignUpDto dto) {
        this.actorEmail = dto.getActorEmail();
        this.actorPassword = dto.getActorPassword();
        this.actorNickName = dto.getActorNickName();
        this.actorProfile = dto.getActorEmail();
    }
}
