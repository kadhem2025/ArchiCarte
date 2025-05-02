package com.carte.archicarte.dao;

import com.carte.archicarte.entity.ViewPoliceProassurEntity;
import com.carte.archicarte.repository.ViewPoliceProassurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewPoliceProassurDao {
    @Autowired
    private ViewPoliceProassurRepository viewPoliceProassurRepository;

    public List<ViewPoliceProassurEntity> getPolice(String police){
       return viewPoliceProassurRepository.findByNPolice(police);
    }


}
