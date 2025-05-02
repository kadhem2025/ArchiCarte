package com.carte.archicarte.repository;

import com.carte.archicarte.entity.ViewPoliceProassurEntity;
import org.springframework.data.jpa.repository.Query;


import java.util.List;



public interface ViewPoliceProassurRepository extends ReadOnlyRepository<ViewPoliceProassurEntity,Integer> {
  //  @Query(value = "SELECT * FROM [dbo].[view_police_proassur] WHERE [NUMERO_POLICE] = ?", nativeQuery = true)
    List<ViewPoliceProassurEntity> findByNPolice(String police);



}

