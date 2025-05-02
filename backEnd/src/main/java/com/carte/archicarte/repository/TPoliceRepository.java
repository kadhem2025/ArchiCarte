package com.carte.archicarte.repository;


import com.carte.archicarte.entity.TPoliceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TPoliceRepository extends JpaRepository<TPoliceEntity,Integer> {

    List<TPoliceEntity> findByNPolice(String police);
    void deleteByNPolice(String nPolice);


}