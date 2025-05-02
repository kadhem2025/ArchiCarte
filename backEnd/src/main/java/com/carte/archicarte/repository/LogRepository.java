package com.carte.archicarte.repository;

import com.carte.archicarte.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<AdminEntity,Integer> {

    AdminEntity findByUsername(String login);
}
