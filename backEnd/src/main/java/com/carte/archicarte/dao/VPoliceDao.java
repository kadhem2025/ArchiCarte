package com.carte.archicarte.dao;



import com.carte.archicarte.entity.AutoPoliceEntity;
import com.carte.archicarte.entity.VPoliceEntity;
import com.carte.archicarte.repository.VPoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VPoliceDao {
    @Autowired
    private VPoliceRepository vPoliceRepository;

    public void addDossier(VPoliceEntity code){
        vPoliceRepository.save(code);
    }


    public List<VPoliceEntity> getFolderVpolice(String police){
        return vPoliceRepository.findByNPolice(police);
    }
    @Transactional
    public void deleteDossier(String police){
        vPoliceRepository.deleteByNPolice(police);
    }
}
