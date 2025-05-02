package com.carte.archicarte.dao;

import com.carte.archicarte.entity.ReservationEntity;
import com.carte.archicarte.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationDao {
    @Autowired
    private ReservationRepository reservationRepository;

    public  void addReservation(ReservationEntity reservation){
        reservationRepository.save(reservation);
    }

    public List<ReservationEntity> getREservations(){
        return reservationRepository.findAll();
    }

    public boolean checkCode(String code ){
        return reservationRepository.existsByCodeDossier(code);
    }

    public List<ReservationEntity> findConsultation(String code){
        return reservationRepository.findByCodeDossier(code);
    }

    @Transactional
    public void  deleteReservation(String numDossier){
        reservationRepository.deleteByCodeDossier(numDossier); ;
    }

}
