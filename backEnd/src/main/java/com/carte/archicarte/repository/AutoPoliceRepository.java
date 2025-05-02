package com.carte.archicarte.repository;

import com.carte.archicarte.entity.AutoPoliceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutoPoliceRepository extends JpaRepository<AutoPoliceEntity,Integer> {

    List<AutoPoliceEntity> findByNPolice(String police);

     void deleteByNPolice(String nPolice);

}