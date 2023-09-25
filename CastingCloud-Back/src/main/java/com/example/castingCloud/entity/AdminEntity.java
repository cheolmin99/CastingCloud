package com.example.castingCloud.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "admin")
@Table(name = "admin")
@Data
public class AdminEntity {
    @Id
    private int adminId;
    private String adminName;
    private String adminEmail;
    private String adminPassword;
}
