package com.example.castingCloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.castingCloud.entity.ActorEntity;

@Repository
public interface ActorRepository extends JpaRepository<ActorEntity, Integer> {
    public ActorEntity findByActorEmail(String actorEmail);

    public boolean existsByActorEmail(String actorEmail);

    public boolean existsByActorNickName(String actorNickName);
}
