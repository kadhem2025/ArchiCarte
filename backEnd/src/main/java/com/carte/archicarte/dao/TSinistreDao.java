package com.carte.archicarte.dao;

import com.carte.archicarte.entity.AutoPoliceEntity;
import com.carte.archicarte.entity.TsinistreEntity;
import com.carte.archicarte.repository.TSinistreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TSinistreDao {
    @Autowired
    private TSinistreRepository tSinistreRepository;
    public void addDossier(TsinistreEntity code){
        tSinistreRepository.save(code);
    }

    public List<TsinistreEntity> getFolderTsinistre(String sinistre){
        return tSinistreRepository.findByNPolice(sinistre);
    }
    @Transactional
    public void deleteDossier(String sinistre){
        tSinistreRepository.deleteByNPolice(sinistre);
    }
}
