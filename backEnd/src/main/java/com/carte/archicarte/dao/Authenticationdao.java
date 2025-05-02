package com.carte.archicarte.dao;

import com.carte.archicarte.entity.AdminEntity;
import com.carte.archicarte.entity.ArchivisteEntity;
import com.carte.archicarte.entity.PersonnelEntity;
import com.carte.archicarte.repository.ArchivisteRepository;
import com.carte.archicarte.repository.LogRepository;
import com.carte.archicarte.repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Authenticationdao {

    @Autowired
    private LogRepository logRepository;
    @Autowired
    private PersonnelRepository personnelRepository;
    @Autowired
    private ArchivisteRepository archivisteRepository;

    public boolean authenticate(String login, String password) {
        AdminEntity user = logRepository.findByUsername(login);

        if (user != null && user.getPassword().equals(password)) {
            // Authentication successful
            return true;
        }

        // Authentication failed
        return false;
    }

    public boolean authenticatePer(String login, String password) {
       PersonnelEntity personnel = personnelRepository.findByUsername(login);

        if (personnel != null && personnel.getPassword().equals(password)) {
            // Authentication successful
            return true;
        }

        // Authentication failed
        return false;
    }

    public boolean authenticateArch(String login, String password) {
        ArchivisteEntity archiviste = archivisteRepository.findByUsername(login);

        if (archiviste != null && archiviste.getPassword().equals(password)) {
            // Authentication successful
            return true;
        }

        // Authentication failed
        return false;
    }

    public String getNameOfUser(String userName,String profile){
        if(profile=="archiviste"){
            ArchivisteEntity archiviste = archivisteRepository.findByUsername(userName);
                if (archiviste!=null){
                    return archiviste.getFirstName()+" "+archiviste.getLastName();
                }
        }
        if(profile=="personnel"){
            PersonnelEntity personnel = personnelRepository.findByUsername(userName);
                if(personnel!=null){
                    return personnel.getFirstName()+" "+personnel.getLastName();

            }
        }
        if(profile=="administrateur"){
            AdminEntity user = logRepository.findByUsername(userName);
                if(user!=null){
                    return user.getFirstName()+" "+user.getLastName();
                }

        }
        return null;
    }
}
