package com.carte.archicarte.repository;


import com.carte.archicarte.entity.RPoliceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RPoliceRepository extends JpaRepository<RPoliceEntity,Integer> {

    List<RPoliceEntity> findByNPolice(String police);
    void deleteByNPolice(String nPolice);


}