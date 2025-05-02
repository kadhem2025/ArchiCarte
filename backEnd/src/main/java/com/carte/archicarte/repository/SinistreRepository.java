package com.carte.archicarte.repository;


import com.carte.archicarte.entity.PoliceEntity;
import com.carte.archicarte.entity.SinistreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SinistreRepository extends JpaRepository<SinistreEntity, String> {
    SinistreEntity findByNSinistre(String Sinistre);

    @Query(value ="SELECT * FROM [dbo].[sinistre_archive]  WHERE [date_ajout]<?", nativeQuery = true)
    List<SinistreEntity> getSinistreByDate(LocalDate date);
}
