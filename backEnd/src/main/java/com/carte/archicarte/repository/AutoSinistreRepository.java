package com.carte.archicarte.repository;


import com.carte.archicarte.entity.AutoSinistreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutoSinistreRepository extends JpaRepository<AutoSinistreEntity,Integer> {

    List<AutoSinistreEntity> findByNPolice(String sinistre);

    void deleteByNPolice(String nPolice);

}
