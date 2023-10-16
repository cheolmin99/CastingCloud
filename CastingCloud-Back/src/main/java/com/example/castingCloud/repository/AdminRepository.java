package com.example.castingCloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.castingCloud.entity.AdminEntity;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, String>{
    public boolean existsByAdminEmail(String adminEmail);
    
    public boolean existsByAdminName(String adminName);

    public AdminEntity findByAdminEmail(String adminEmail);
}
