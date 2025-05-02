package com.carte.archicarte.dao;

import com.carte.archicarte.entity.AdminEntity;
import com.carte.archicarte.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminDao {
    @Autowired
    private LogRepository logRepository;

    public List<AdminEntity> getAdmins(){
        return logRepository.findAll();
    }
}
