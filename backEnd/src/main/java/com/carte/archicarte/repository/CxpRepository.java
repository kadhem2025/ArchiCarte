package com.carte.archicarte.repository;

import com.carte.archicarte.entity.CxpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CxpRepository extends JpaRepository<CxpEntity,Integer> {
    List<CxpEntity> findByNCxp(String cxp);
    void deleteByNCxp(String nCxp);
    @Query(value ="SELECT * FROM [dbo].[cxp]  WHERE [date_ajout]<?", nativeQuery = true)
    List<CxpEntity> getCxpByDate(LocalDate date);
}
