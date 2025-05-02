package com.carte.archicarte.dao;

import com.carte.archicarte.entity.PoliceEntity;
import com.carte.archicarte.repository.PoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PoliceDao {
    @Autowired
    private PoliceRepository policeRepository;

    public void addContrat(PoliceEntity police){
        policeRepository.save(police);
    }

    public PoliceEntity findFolder(String police){
        return  policeRepository.findByNPolice(police);
    }
    public List<PoliceEntity> getallPolice(){
       return policeRepository.findAll();
    }

    public List<PoliceEntity> getPoliceByDate(LocalDate date){
        return policeRepository.getPoliceByDate(date);
    }
}
