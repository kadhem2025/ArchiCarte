package com.carte.archicarte.repository;

import com.carte.archicarte.entity.DPoliceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DPoliceRepository extends JpaRepository<DPoliceEntity,Integer> {

    List<DPoliceEntity> findByNPolice(String police);
    void deleteByNPolice(String nPolice);

}
