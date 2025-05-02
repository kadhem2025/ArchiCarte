package com.carte.archicarte.dao;

import com.carte.archicarte.entity.AutoPoliceEntity;
import com.carte.archicarte.entity.RsinistreEntity;
import com.carte.archicarte.repository.RSinistreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RSinistreDao {
    @Autowired
    private RSinistreRepository rSinistreRepository;

    public void   addDossier(RsinistreEntity code){
        rSinistreRepository.save(code);
    }

    public List<RsinistreEntity> getFolderRsinistre(String sinistre){
        return rSinistreRepository.findByNPolice(sinistre);
    }
    @Transactional
    public void deleteDossier(String sinistre){
        rSinistreRepository.deleteByNPolice(sinistre);
    }
}
