package com.example.castingCloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.castingCloud.entity.DirectorEntity;

@Repository
public interface DirectorRepository extends JpaRepository<DirectorEntity, Integer>{
    public DirectorEntity findByDirectorEmail(String directorEmail);

    public boolean existsByDirectorEmail(String directorEmail);

    public boolean existsByDirectorName(String directorName);

    public boolean existsByDirectorPhoneNumber(String directorPhoneNumber);

    public boolean existsByDirectorCompany(String directorCompany);
}
