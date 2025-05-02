package com.carte.archicarte.dao;

import com.carte.archicarte.entity.ArchivisteEntity;
import com.carte.archicarte.entity.PersonnelEntity;
import com.carte.archicarte.repository.ArchivisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class ArchichivisteDao {
    @Autowired
    private ArchivisteRepository archivisteRepository;

    public List<ArchivisteEntity> getArchiviste(){
        return archivisteRepository.findAll();
    }
    public void addProfil(ArchivisteEntity archiviste){
        archivisteRepository.save(archiviste);
    }
    public ArchivisteEntity getArchivisteByLogin(String login){
      return   archivisteRepository.findByUsername(login);
    }

    @Transactional
    public void  deleteArchiviste(String login){
        archivisteRepository.deleteByUsername(login) ;
    }

    @Transactional
    public ArchivisteEntity updateUser(PersonnelEntity newPersonnel, String aux) {
        ArchivisteEntity UserArchiviste = archivisteRepository.findByUsername(aux);
        if (UserArchiviste!=null) {
            ArchivisteEntity archiviste = UserArchiviste;
            // Update the details
            archiviste.setPassword(newPersonnel.getPassword());
            archiviste.setFirstName(newPersonnel.getFirstName());
            archiviste.setLastName(newPersonnel.getLastName());
            archiviste.setUsername(newPersonnel.getFirstName()+"."+newPersonnel.getLastName());

            // Save the updated user
            return archivisteRepository.save(archiviste);
        } else {
            // Handle the case where the username does not exist
            throw new RuntimeException("User not found with username: " + newPersonnel.getUsername());
        }
    }
    @Transactional
    public void PasswordChanged(ArchivisteEntity archiviste, String password){
        archiviste.setPassword(password);
    }

}
