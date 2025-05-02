package com.carte.archicarte.dao;

import com.carte.archicarte.entity.ViewCxpProassurEntity;
import com.carte.archicarte.entity.ViewPoliceProassurEntity;
import com.carte.archicarte.repository.ViewCxpProassurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewCxpProassurDao {
    @Autowired
    private ViewCxpProassurRepository viewCxpProassurRepository;

    public List<ViewCxpProassurEntity> getcxp(String cxp){
        return viewCxpProassurRepository.findByNCxp(cxp);

    }
}
