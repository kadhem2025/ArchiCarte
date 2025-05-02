package com.carte.archicarte.dao;

import com.carte.archicarte.entity.AutoPoliceEntity;
import com.carte.archicarte.entity.AutoSinistreEntity;
import com.carte.archicarte.repository.AutoSinistreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutoSinistreDao {
    @Autowired
    private AutoSinistreRepository autoSinistreRepository;

    public void addDossier(AutoSinistreEntity code){
        autoSinistreRepository.save(code);
    }

    public List<AutoSinistreEntity> getFolderAutoSinistre(String sinistre){
       return autoSinistreRepository.findByNPolice(sinistre);
    }
    @Transactional
    public void deleteDossier(String sinistre){
        autoSinistreRepository.deleteByNPolice(sinistre);
    }
}
