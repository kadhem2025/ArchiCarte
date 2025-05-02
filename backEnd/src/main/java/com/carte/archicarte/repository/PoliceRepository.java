package com.carte.archicarte.repository;

import com.carte.archicarte.entity.CxpEntity;
import com.carte.archicarte.entity.PoliceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PoliceRepository extends JpaRepository<PoliceEntity, Integer> {
    //@Query("select NUMERO_POLICE from ArchiCarteCopie.dbo.view_police_proassur  where NUMERO_POLICE = :N_Police")
    //List<PoliceEntity> recherchePolice(@Param(value="N_Police") String N_Police);

    PoliceEntity findByNPolice(String police);

    @Query(value ="SELECT * FROM [dbo].[police_archive]  WHERE [date_ajout]<?", nativeQuery = true)
    List<PoliceEntity> getPoliceByDate(LocalDate date);

}
