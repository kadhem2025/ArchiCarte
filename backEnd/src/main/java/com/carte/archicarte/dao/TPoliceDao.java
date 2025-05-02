package com.carte.archicarte.dao;



import com.carte.archicarte.entity.AutoPoliceEntity;
import com.carte.archicarte.entity.TPoliceEntity;
import com.carte.archicarte.repository.TPoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TPoliceDao {
    @Autowired
    private TPoliceRepository tPoliceRepository;

    public void addDossier(TPoliceEntity code){
        tPoliceRepository.save(code);
    }

    public List<TPoliceEntity> getFolderTpolice(String police){
        return tPoliceRepository.findByNPolice(police);
    }

    @Transactional
    public void deleteDossier(String police){
        tPoliceRepository.deleteByNPolice(police);
    }
}