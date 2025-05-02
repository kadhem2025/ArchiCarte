package com.carte.archicarte.controller;

import com.carte.archicarte.dao.*;
import com.carte.archicarte.entity.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class RechercheController {
    @Autowired
    private PoliceDao policeDao;
    @Autowired
    private SinistreDao sinistreDao;
    @Autowired
    private CxpDao cxpDao;
    @Autowired
    private ConsultationDao consultationDao;
    @Autowired
    private HistoryConsultationDao historyConsultationDao;

    @PostMapping(path = "Dossiers/recherche/{typeDossier}/{nameArchiviste}")
    public List<List<List<?>>> addConsultations(@PathVariable String typeDossier,@PathVariable String nameArchiviste,@RequestBody List<Map<String,String>> listDossiers) {

        List<List<?>> listDoss=new ArrayList<>();
        List<Object> listAux=new ArrayList<>();
        List<List<List<?>>> listResult=new ArrayList<>();
        List<Map<String,String>> listErr = new ArrayList<>();
        List<Map<String,String>> listScc = new ArrayList<>();
        List<Map<String,String>> secoundList=new ArrayList<>();
        List<Map<String,String>> firstList=new ArrayList<>();
        List<ConsultationEntity> consultation=new ArrayList<>();
        ConsultationEntity addConsultation=new ConsultationEntity();
        for (Map<String, String> cp : listDossiers) {
            if(cp.size()==1) {
                secoundList.add(cp);
            } else if (cp.size()>1) {
                firstList.add(cp);
            }
        }
        for(Map<String,String> cp:secoundList){
            List<?> listreturn = getFolder_V2(typeDossier, cp.get("dossier"));
            consultation=consultationDao.getConsultationsByCode(cp.get("dossier"));
            if(listreturn.size()==0){
                cp.put("erreur","Dossier existant dans la base archive");
                listErr.add(cp);
            } else if (consultation.size()!=0) {
                for (ConsultationEntity cons:consultation) {
                    cp.put("erreur", "Le dossier est déjà consulté par " + cons.getConsulterPar());
                }
                listErr.add(cp);

            }else if(listreturn.size()!=0 && consultation.size()==0){
                LocalDateTime currentDate=LocalDateTime.now();
                for(Map<String,String> firstCp:firstList){
                    for (Object cpDossier:listreturn){


                        if(typeDossier.equals("Police")){
                            PoliceEntity item = (PoliceEntity) cpDossier;
                            addConsultation.setArchivisteAjout(item.getUtilisateur());
                            addConsultation.setNaffaire(item.getNPolice());
                        }
                        if(typeDossier.equals("Sinistre")){
                            SinistreEntity item = (SinistreEntity) cpDossier;
                            addConsultation.setArchivisteAjout(item.getUtilisateur());
                            addConsultation.setNaffaire(item.getNSinistre());

                        }
                        if(typeDossier.equals("Cxp")){
                            CxpEntity item = (CxpEntity) cpDossier;
                            addConsultation.setArchivisteAjout(item.getUtilisateur());
                            addConsultation.setNaffaire(item.getNCxp());

                        }

                    }
                    addConsultation.setConsulterPar(firstCp.get("consuterPar"));
                    addConsultation.setArchivisteCons(nameArchiviste);
                    addConsultation.setTypeAffaire(typeDossier);
                    if(firstCp.get("dateConsultation").equals("")) {
                        addConsultation.setDateConsultation(null);
                    }
                    else {
                        addConsultation.setDateConsultation(LocalDate.parse(firstCp.get("dateConsultation")));
                    }
                    if(firstCp.get("dateRetour").equals("")) {
                        addConsultation.setDateRetour(null);
                    }
                    else {
                        addConsultation.setDateRetour(LocalDate.parse(firstCp.get("dateRetour")));
                    }
                    addConsultation.setDateInsertion(currentDate);

                   // addConsultation.set
                }

                cp.put("message","Dossier est ajout avec succes");
                listErr.add(cp);
                for(Object cpDossier:listreturn){
                    if (typeDossier.equals("Police")){
                        PoliceEntity polices=(PoliceEntity) cpDossier;
                        listAux.add(polices);
                    }else if(typeDossier.equals("Sinistre")){
                        SinistreEntity sinistres=(SinistreEntity) cpDossier;
                        listAux.add(sinistres);
                    }else if(typeDossier.equals("Cxp")){
                        CxpEntity cxps=(CxpEntity) cpDossier;
                        listAux.add(cxps);
                    }
                }


            }

            if(addConsultation.getNaffaire()!=null) {
                consultationDao.addConsltation(addConsultation);
                addConsultation = new ConsultationEntity();
            }
        }

        List<?> innerList = listAux;

// Wrap innerList in a List<List<?>> (second layer)
        List<List<?>> middleList = new ArrayList<>();
        middleList.add(innerList);

        listDoss.add(listErr);
        listResult.add(listDoss);
        listResult.add(middleList);
        return listResult;
    }

    public List<?> getFolder_V2(@PathVariable String typedossier, @PathVariable String dossier){
        if(typedossier.equals("Police")){
            List<PoliceEntity> listPolice=new ArrayList<>();
            PoliceEntity folder = policeDao.findFolder(dossier);
            if (folder != null) {
                listPolice.add(folder);
                return listPolice; // Return the found folder
            } else {
                return listPolice;
            }
        } else if (typedossier.equals("Sinistre")) {
            List<SinistreEntity> listSinistre=new ArrayList<>();
            SinistreEntity folder = sinistreDao.findSinistre(dossier);
            if (folder != null) {
                listSinistre.add(folder);
                return listSinistre; // Return the found folder
            } else {
                return listSinistre;
            }
        } else if (typedossier.equals("Cxp")) {
            List<CxpEntity> listCxp=new ArrayList<>();
            CxpEntity folder = cxpDao.findCxp(dossier);
            if (folder.getNCxp() != null) {
                listCxp.add(folder);
                return listCxp; // Return the found folder
            } else {
                return null;
            }

        }
        return null;
    }

    @DeleteMapping(path = "Dossiers/recuperationDossiers/{nameArchiviste}/{dateRetour}")
    public List<List<Map<String,String>>> deleteConsultation(@RequestBody List<Map<String,String>> listDossiers,@PathVariable String nameArchiviste,@PathVariable String dateRetour) {
        LocalDateTime currentDate = LocalDateTime.now();
        //DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy à HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateRetour, formatter);
        ConsultationHistoriqueEntity historiqueConsultation=new ConsultationHistoriqueEntity();
        List<ConsultationEntity> consultations=new ArrayList<>();
       List< Map<String,String>> resultError=new ArrayList<>();
        List<Map<String,String>> resultmessage=new ArrayList<>();
        List<List<Map<String,String>>> finalResult=new ArrayList<>();

        for(Map<String,String> numDossier:listDossiers) {
            consultations = consultationDao.getCosultation(numDossier.get("dossier"));
            if(consultations.isEmpty()) {
                String lastArchivisteRetour= historyConsultationDao.getLastArchivisteRetour(numDossier.get("dossier"));
                if(lastArchivisteRetour!=null){
                    numDossier.put("erreur", "Le dossier est déja récupéré par "+lastArchivisteRetour);
                    resultError.add(numDossier);
                }else {
                    numDossier.put("erreur", "Le dossier n'est pas consulté");
                    resultError.add(numDossier);

                }
            }

            String archivisteAjout = "";
            for (ConsultationEntity consultation : consultations) {
                if (consultation.getTypeAffaire().equals("Police")) {
                    PoliceEntity p = policeDao.findFolder(consultation.getNaffaire());
                    archivisteAjout = p.getUtilisateur();
                }
                if (consultation.getTypeAffaire().equals("Sinistre")) {
                    SinistreEntity p = sinistreDao.findSinistre(consultation.getNaffaire());
                    archivisteAjout = p.getUtilisateur();
                }
                if (consultation.getTypeAffaire().equals("Cxp")) {
                    List<CxpEntity> p = cxpDao.getFolderCxp(consultation.getNaffaire());
                    for (CxpEntity cp : p) {
                        archivisteAjout = cp.getUtilisateur();
                    }
                }
            }
            if(!consultations.isEmpty()) {
                for (ConsultationEntity cp : consultations) {
                    historiqueConsultation.setConsulterPar(cp.getConsulterPar());
                    historiqueConsultation.setDateConsultation(cp.getDateConsultation());
                    historiqueConsultation.setArchivisteCons(cp.getArchivisteCons());
                    historiqueConsultation.setArchivisteRet(nameArchiviste);
                    historiqueConsultation.setArchivisteAjout(archivisteAjout);
                    historiqueConsultation.setDateRetour(cp.getDateRetour());
                    historiqueConsultation.setNaffaire(cp.getNaffaire());
                    historiqueConsultation.setDateInsertion(cp.getDateInsertion());
                    historiqueConsultation.setTypeAffaire(cp.getTypeAffaire());
                    historiqueConsultation.setDateRetourReel(localDateTime);
                    historyConsultationDao.addHistory(historiqueConsultation);
                    historiqueConsultation = new ConsultationHistoriqueEntity();
                }
                consultationDao.deleteConsultation(numDossier.get("dossier"));
                numDossier.put("message", "La consultation est supprimé avec succes");
                resultmessage.add(numDossier);

            }
        }
        finalResult.add(resultError);
        finalResult.add(resultmessage);
        return finalResult;
    }
}
