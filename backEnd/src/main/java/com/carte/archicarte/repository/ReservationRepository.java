package com.carte.archicarte.repository;

import com.carte.archicarte.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity,Integer> {

    boolean existsByCodeDossier(String code);
    List<ReservationEntity> findByCodeDossier(String code);

    void deleteByCodeDossier(String codeDossier);

}
