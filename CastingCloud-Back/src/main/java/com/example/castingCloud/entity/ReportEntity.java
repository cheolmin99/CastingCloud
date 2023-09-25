package com.example.castingCloud.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "report")
@Table(name = "report")
@Data
public class ReportEntity {
    @Id
    private int reportId;
    private int directorId;
    private int actorId;
    private int videoId;
    private String reportReason;
}
