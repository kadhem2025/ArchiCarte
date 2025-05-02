package com.carte.archicarte.repository;


import com.carte.archicarte.entity.VPoliceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VPoliceRepository extends JpaRepository<VPoliceEntity,Integer> {

    List<VPoliceEntity> findByNPolice(String police);
    void deleteByNPolice(String nPolice);


}