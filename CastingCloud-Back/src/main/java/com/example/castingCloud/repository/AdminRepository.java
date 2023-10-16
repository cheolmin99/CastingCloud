package com.example.castingCloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.castingCloud.entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, String>{
    public boolean exexistsByAdminEmail(String adminEmail);
    
    public boolean exexistsByAdminName(String adminName);

    public AdminEntity findByAdminEmail(String adminEmail);
}
