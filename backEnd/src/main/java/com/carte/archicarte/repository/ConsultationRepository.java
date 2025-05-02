package com.carte.archicarte.repository;

import com.carte.archicarte.entity.ConsultationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<ConsultationEntity,Integer> {

    List<ConsultationEntity> findByNaffaire(String police);


    boolean existsByNaffaire(String codeDossier);

    void deleteByNaffaire(String codeDossier);
    @Query(value ="SELECT * FROM [dbo].[consultation]  WHERE [type_affaire]= ?1 union all  SELECT  [date_consultation],[date_retour],[id],[date_insertion],[date_retour_reel],[archiviste_ajout],[archiviste_consultation],[consulter_par],[n_affaire],[type_affaire],[archiviste_retour] FROM [dbo].[consultation_historique] WHERE [type_affaire]= ?1", nativeQuery = true)
    List<ConsultationEntity> getAllFiltereParEntity(String typeDossier);
    @Query(value ="SELECT * FROM [dbo].[consultation]  WHERE [date_insertion]<?1 union all  SELECT  [date_consultation],[date_retour],[id],[date_insertion],[date_retour_reel],[archiviste_ajout],[archiviste_consultation],[consulter_par],[n_affaire],[type_affaire],[archiviste_retour] FROM [dbo].[consultation_historique] WHERE [date_insertion]<?1", nativeQuery = true)
    List<ConsultationEntity> getAllConsultationbyDate(LocalDate date);
    @Query(value ="SELECT * FROM [dbo].[consultation]  WHERE [date_insertion]<?2 AND [type_affaire]=?1 union all SELECT  [date_consultation],[date_retour],[id],[date_insertion],[date_retour_reel],[archiviste_ajout],[archiviste_consultation],[consulter_par],[n_affaire],[type_affaire],[archiviste_retour] FROM [dbo].[consultation_historique] WHERE [date_insertion]<?2 AND [type_affaire]=?1", nativeQuery = true)
    List<ConsultationEntity> getAllConsultationByTypeAndDate(String typeDossier, LocalDate date);


}
