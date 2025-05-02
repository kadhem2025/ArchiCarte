package com.carte.archicarte.dao;

import com.carte.archicarte.entity.ArchivisteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ArchichivisteDao archichivisteDao;

    public void sendPasswordResetEmail(String to, String url) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Réinitialisation du mot de passe");
        message.setText("Cliquez sur le lien suivant pour réinitialiser votre mot de passe: " + url);
        javaMailSender.send(message);
    }



    public void sendRequestFolderEmail(String numDoosier,String namePr, LocalDate dateRes, LocalDate dateRet) {
        List<ArchivisteEntity> resp= archichivisteDao.getArchiviste();
        for(ArchivisteEntity cpt :resp){
           String  to= cpt.getEmail();
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Demande dossier");
            message.setText("M. "+namePr+" a soumis une demande pour le dossier numéro "+ numDoosier +" des archives, datée du "+ dateRes +" au "+ dateRet );
            javaMailSender.send(message);
        }

    }
}
