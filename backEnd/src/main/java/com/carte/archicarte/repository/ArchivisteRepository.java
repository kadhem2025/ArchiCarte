package com.carte.archicarte.repository;

import com.carte.archicarte.entity.ArchivisteEntity;
import com.carte.archicarte.entity.PersonnelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivisteRepository extends JpaRepository<ArchivisteEntity,Integer> {

    ArchivisteEntity findByUsername(String username) ;

    void deleteByUsername(String login);

}
