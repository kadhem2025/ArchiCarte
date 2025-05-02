package com.carte.archicarte.dao;

import com.carte.archicarte.entity.SinistreEntity;
import com.carte.archicarte.repository.SinistreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SinistreDao {
    @Autowired
    private SinistreRepository sinistreRepository ;

    public void addSinistre(SinistreEntity sinistre){
        sinistreRepository.save(sinistre);
    }

    public  SinistreEntity findSinistre(String sinstre){
       return sinistreRepository.findByNSinistre(sinstre);
    }

    public List<SinistreEntity> getAllSinistre(){
       return sinistreRepository.findAll();
    }

    public List<SinistreEntity> getSinistreByDate(LocalDate date){
        return sinistreRepository.getSinistreByDate(date);
    }
}
