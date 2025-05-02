package com.carte.archicarte.dao;

import com.carte.archicarte.entity.ArchivisteEntity;
import com.carte.archicarte.entity.PersonnelEntity;
import com.carte.archicarte.repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonnelDao {
    @Autowired
    private PersonnelRepository personnelRepository;
    public void addProfil(PersonnelEntity profil){
        personnelRepository.save(profil);
    }
    public List<PersonnelEntity> getPersonnel(){
       return personnelRepository.findAll();
    }

    public PersonnelEntity getPersonnelByLogin(String login){
      return   personnelRepository.findByUsername(login);
    }

    @Transactional
    public void  deletePersonnel(String login){
        personnelRepository.deleteByUsername(login) ;
    }
    @Transactional
    public PersonnelEntity updateItem(PersonnelEntity newPersonnel, String aux) {
        PersonnelEntity UserPersonnel = personnelRepository.findByUsername(aux);
        if (UserPersonnel!=null) {
            PersonnelEntity personnel = UserPersonnel;
            // Update the details
            personnel.setPassword(newPersonnel.getPassword());
            personnel.setFirstName(newPersonnel.getFirstName());
            personnel.setLastName(newPersonnel.getLastName());
            personnel.setUsername(newPersonnel.getFirstName()+"."+newPersonnel.getLastName());
            personnel.setProfil(newPersonnel.getProfil());
            // Save the updated user
            return personnelRepository.save(personnel);
        } else {
            // Handle the case where the username does not exist
            throw new RuntimeException("User not found with username: " + newPersonnel.getUsername());
        }
    }

    @Transactional
    public void PasswordChanged(PersonnelEntity personnel, String password){
        personnel.setPassword(password);
    }
}
