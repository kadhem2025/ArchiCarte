package com.carte.archicarte.dao;

import com.carte.archicarte.entity.AutoPoliceEntity;
import com.carte.archicarte.entity.CxpEntity;
import com.carte.archicarte.repository.CxpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CxpDao {
    @Autowired
    private CxpRepository cxpRepository;

    public void addDossier(CxpEntity code){
        cxpRepository.save(code);
    }

    public List<CxpEntity> getFolderCxp(String cxp){
        return cxpRepository.findByNCxp(cxp);
    }
    @Transactional
    public void deleteDossier(String cxp){
        cxpRepository.findByNCxp(cxp);
    }

    public CxpEntity findCxp(String cxp){
      List<CxpEntity> listCxp=  cxpRepository.findByNCxp(cxp);
      CxpEntity cxp1=new CxpEntity();
        for (CxpEntity cp:listCxp) {
            cxp1= cp;
        }
        return cxp1;
    }

    public  List<CxpEntity> getAllCxp(){
        return cxpRepository.findAll();
    }

    public List<CxpEntity> getCxpByDate(LocalDate date){
        return cxpRepository.getCxpByDate(date);
    }
}
