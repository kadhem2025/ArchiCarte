package com.carte.archicarte.dao;

import com.carte.archicarte.entity.ViewPoliceProassurEntity;
import com.carte.archicarte.entity.ViewSinisterProassurEntity;
import com.carte.archicarte.repository.ViewSinistreProassurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewSinistreProassurDao {
    @Autowired
    private ViewSinistreProassurRepository viewSinistreProassurRepository;


    public List<ViewSinisterProassurEntity> getSinistre(String sinistre){
        return viewSinistreProassurRepository.findByNSinister(sinistre);

    }

    public String getGestionnaire(String sinistre){
        List<ViewSinisterProassurEntity> listSinstre= viewSinistreProassurRepository.findByNSinister(sinistre);
        for(ViewSinisterProassurEntity cp: listSinstre){
            return cp.getGestionnaire();
        }
       return null;
    }

    public String getEtatDossierProassur(String sinistre){
        List<ViewSinisterProassurEntity> listSinstre= viewSinistreProassurRepository.findByNSinister(sinistre);
        for(ViewSinisterProassurEntity cp: listSinstre){
            return cp.getEtatDossier();
        }
        return null;
    }

    public String getTypeDossierProassur(String sinistre){
        List<ViewSinisterProassurEntity> listSinstre= viewSinistreProassurRepository.findByNSinister(sinistre);
        for(ViewSinisterProassurEntity cp: listSinstre){
            return cp.getTypeDossier();
        }
        return null;
    }
}
