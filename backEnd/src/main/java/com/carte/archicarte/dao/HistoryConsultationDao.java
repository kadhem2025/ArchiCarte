package com.carte.archicarte.dao;

import com.carte.archicarte.entity.ConsultationHistoriqueEntity;
import com.carte.archicarte.repository.HistoryConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryConsultationDao {
    @Autowired
    private HistoryConsultationRepository historyConsultationRepository;

    public void addHistory(ConsultationHistoriqueEntity history){
        historyConsultationRepository.save(history);
    }

    public List<ConsultationHistoriqueEntity> gethistorys(String numDossier){
        return historyConsultationRepository.findByNaffaire(numDossier);
    }

    public String getLastArchivisteRetour(String numAffaire){
        return historyConsultationRepository.getNameArchiviste(numAffaire);
    }
}
