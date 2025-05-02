package com.carte.archicarte.dao;

import com.carte.archicarte.entity.AutoPoliceEntity;
import com.carte.archicarte.repository.AutoPoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutoPoliceDao {
    @Autowired
    private AutoPoliceRepository autoPoliceRepository;

    public void addDossier(AutoPoliceEntity code){
        autoPoliceRepository.save(code);
    }
    public List<AutoPoliceEntity> getAllPolice(String police){
      return   autoPoliceRepository.findByNPolice(police);
    }
    @Transactional
    public void deleteDossier(String police){
        autoPoliceRepository.deleteByNPolice(police);
    }
}