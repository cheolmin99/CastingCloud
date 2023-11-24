package com.example.castingCloud.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.castingCloud.dto.request.upload.PostUploadDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "video")
@Table(name = "video")
@Data
public class VideoEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int videoId;
    private int actorId;
    private String videoUrl;
    private String videoCategoryGender;
    private String videoCategoryAge;
    private String videoCategoryGenre;
    private String videoCategoryPosition;
    private String videoDate;
    private String uploadUserNickName;
    private String uploadUserProfileUrl;

    public VideoEntity(ActorEntity actorEntity, PostUploadDto postUploadDto) {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

        this.videoCategoryAge = postUploadDto.getVideoCategoryAge();
        this.videoCategoryGender = postUploadDto.getVideoCategoryGender();
        this.videoCategoryGenre = postUploadDto.getVideoCategoryGenre();
        this.videoCategoryPosition = postUploadDto.getVideoCategoryPosition();
        this.videoUrl = postUploadDto.getVideoUrl();
        this.videoDate = simpleDateFormat.format(now);
        this.uploadUserNickName = actorEntity.getActorNickName();
        this.uploadUserProfileUrl = actorEntity.getActorProfile();
        this.actorId = actorEntity.getActorId();
    }
}
