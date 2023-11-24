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
@Entity(name = "template")
@Table(name = "template")
@Data
public class TemplateEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int templateId;
    private int actorId;
    private int directorId;
    private int videoId;
}
