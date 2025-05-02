package com.carte.archicarte.repository;

import com.carte.archicarte.entity.AdminEntity;
import com.carte.archicarte.entity.PersonnelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnelRepository  extends JpaRepository<PersonnelEntity,Integer> {
    PersonnelEntity findByUsername(String login) ;

    void deleteByUsername(String login);

    boolean existsByUsername(String login) ;
}
