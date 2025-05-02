package com.carte.archicarte.repository;



import com.carte.archicarte.entity.DsinistreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DSinistreRepository extends JpaRepository<DsinistreEntity,Integer> {

    List<DsinistreEntity> findByNPolice(String sinistre);
    void deleteByNPolice(String nSinistre);

}