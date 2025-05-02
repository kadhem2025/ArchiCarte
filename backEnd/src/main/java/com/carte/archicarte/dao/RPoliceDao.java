package com.carte.archicarte.dao;

;
import com.carte.archicarte.entity.AutoPoliceEntity;
import com.carte.archicarte.entity.RPoliceEntity;
import com.carte.archicarte.repository.RPoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RPoliceDao {
    @Autowired
    private RPoliceRepository rPoliceRepository;

    public void addDossier(RPoliceEntity code){
        rPoliceRepository.save(code);
    }

    public List<RPoliceEntity> getFolderRpolice(String police){
       return rPoliceRepository.findByNPolice(police);
    }
    @Transactional
    public void deleteDossier(String police){
        rPoliceRepository.deleteByNPolice(police);
    }
}