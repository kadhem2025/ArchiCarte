package com.carte.archicarte.repository;



import com.carte.archicarte.entity.RsinistreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RSinistreRepository extends JpaRepository<RsinistreEntity,Integer> {

    List<RsinistreEntity> findByNPolice(String sinistre);
    void deleteByNPolice(String nPolice);


}

