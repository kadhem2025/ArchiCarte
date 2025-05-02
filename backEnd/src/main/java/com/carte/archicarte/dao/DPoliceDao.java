package com.carte.archicarte.dao;

import com.carte.archicarte.entity.AutoPoliceEntity;
import com.carte.archicarte.entity.DPoliceEntity;
import com.carte.archicarte.repository.DPoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DPoliceDao {
    @Autowired
    private DPoliceRepository dPoliceRepository;

    public void addDossier(DPoliceEntity code){
       dPoliceRepository.save(code);
    }
    public List<DPoliceEntity> getFolder(String police){
        return dPoliceRepository.findByNPolice(police);
    }
    @Transactional
    public void deleteDossier(String police){
        dPoliceRepository.deleteByNPolice(police);
    }
}
