package com.example.castingCloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.castingCloud.entity.VideoEntity;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Integer> {
    public VideoEntity findById(int videoId);    
}
