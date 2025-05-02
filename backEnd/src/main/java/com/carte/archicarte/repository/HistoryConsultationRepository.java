package com.carte.archicarte.repository;

import com.carte.archicarte.entity.ConsultationHistoriqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoryConsultationRepository extends JpaRepository<ConsultationHistoriqueEntity,Integer> {
    List<ConsultationHistoriqueEntity> findByNaffaire(String nDossier);
    //SELECT TOP 1 (archiviste_retour)
    //FROM consultation_historique
    //WHERE n_affaire = '19170289'
    //ORDER BY date_retour_reel DESC;
    @Query(value ="SELECT TOP 1 (archiviste_retour) FROM [dbo].[consultation_historique]  WHERE n_affaire =? ORDER BY date_retour_reel DESC", nativeQuery = true)
    String getNameArchiviste(String numAffaire);
}
