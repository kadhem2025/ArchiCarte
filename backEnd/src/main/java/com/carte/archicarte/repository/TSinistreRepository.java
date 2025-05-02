package com.carte.archicarte.repository;


import com.carte.archicarte.entity.TsinistreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TSinistreRepository extends JpaRepository<TsinistreEntity,Integer> {

    List<TsinistreEntity> findByNPolice(String sinistre);

    void deleteByNPolice(String nPolice);


}
