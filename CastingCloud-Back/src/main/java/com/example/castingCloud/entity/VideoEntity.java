package com.example.castingCloud.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private int videoId;
    private int actorId;
    private String videoUrl;
    private String videoCategoryGender;
    private String videoCategoryAge;
    private String videoCategoryGenre;
    private String videoCategoryPosition;
    private String videoDate;
}
