package com.example.castingCloud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "comment")
@Table(name = "comment")
@Data
public class CommentEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int commentId;
    private int videoId;
    private int directorId;
    private String commentContent;
}
