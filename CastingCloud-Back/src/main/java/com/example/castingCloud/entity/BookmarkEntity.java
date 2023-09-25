package com.example.castingCloud.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bookmark")
@Table(name = "bookmark")
@Data
public class BookmarkEntity {
    @Id
    private int bookmarkId;
    private int templateId;
    private int videoId;
    private int actorId;
    private int directorId;
}
