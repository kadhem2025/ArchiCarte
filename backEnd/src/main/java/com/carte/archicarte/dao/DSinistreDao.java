package com.carte.archicarte.dao;

import com.carte.archicarte.entity.AutoPoliceEntity;
import com.carte.archicarte.entity.DsinistreEntity;
import com.carte.archicarte.repository.DSinistreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DSinistreDao {
    @Autowired
    private DSinistreRepository dSinistreRepository;

    public  void addDossier(DsinistreEntity code){
        dSinistreRepository.save(code);
    }

    public List<DsinistreEntity> getFoldetDsinistre(String sinistre){
        return dSinistreRepository.findByNPolice(sinistre);
    }
    @Transactional
    public void deleteDossier(String sinistre){
        dSinistreRepository.deleteByNPolice(sinistre);
    }
}
