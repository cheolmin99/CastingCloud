package com.example.castingCloud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.castingCloud.dto.request.admin.AdminSignUpDto;

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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int adminId;
    private String adminName;
    private String adminEmail;
    private String adminPassword;

    public AdminEntity(AdminSignUpDto dto) {
        this.adminEmail = dto.getAdminEmail();
        this.adminName = dto.getAdminName();
        this.adminPassword = dto.getAdminPassword();
    }
}
