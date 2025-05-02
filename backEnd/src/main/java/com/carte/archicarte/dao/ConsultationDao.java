package com.carte.archicarte.dao;

import com.carte.archicarte.entity.ConsultationEntity;
import com.carte.archicarte.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ConsultationDao {
    @Autowired
    private ConsultationRepository consultationRepository;
    public List<ConsultationEntity> getCosultation(String police){
        return consultationRepository.findByNaffaire(police);
    }

    public void addConsltation(ConsultationEntity consultation){
        consultationRepository.save(consultation);
    }

    public List<ConsultationEntity> getConsultationsByCode(String code){
        return consultationRepository.findByNaffaire(code);
    }

    @Transactional
    public void  deleteConsultation(String numDossier){
        consultationRepository.deleteByNaffaire(numDossier); ;
    }

    public List<ConsultationEntity> getAllConsultationFiltreByTypeDossier(String typedossier){
        return consultationRepository.getAllFiltereParEntity(typedossier);
    }

    public List<ConsultationEntity> getAllConsultationFiltreByDate(LocalDate date){
        return consultationRepository.getAllConsultationbyDate(date);
    }

    public List<ConsultationEntity> getAllConsultationBydateAndType(String typeDossier, LocalDate date){
        return consultationRepository.getAllConsultationByTypeAndDate(typeDossier,date);
    }

    public List<ConsultationEntity> getAllConsultation(){
        return consultationRepository.findAll();
    }
}
