package com.carte.archicarte.controller;

import com.carte.archicarte.dao.*;
import com.carte.archicarte.entity.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

//import javax.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private AdminDao adminDao;

    @Autowired
    private Authenticationdao authenticationdao;
    @Autowired
    private ArchichivisteDao archichivisteDao;
    @Autowired
    private ReservationDao reservationDao;
    @Autowired
    private PersonnelDao personnelDao;
    @Autowired
    private DPoliceDao dPoliceDao;
    @Autowired
    private RPoliceDao rPoliceDao;
    @Autowired
    private TPoliceDao tPoliceDao;
    @Autowired
    private VPoliceDao vPoliceDao;
    @Autowired
    private AutoPoliceDao autoPoliceDao;
    @Autowired
    private AutoSinistreDao autoSinistreDao;
    @Autowired
    private DSinistreDao dSinistreDao;
    @Autowired
    private RSinistreDao rSinistreDao;
    @Autowired
    private TSinistreDao tSinistreDao;
    @Autowired
    private CxpDao cxpDao;
    @Autowired
    private ConsultationDao consultationDao;
    @Autowired
    private ProassurService proassurService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ViewPoliceProassurDao viewPoliceProassurDao;
    @Autowired
    private  ViewSinistreProassurDao viewSinistreProassurDao;
    @Autowired
    @Lazy
    private ViewCxpProassurDao viewCxpProassurDao;
    @Autowired
    private PoliceDao policeDao;
    @Autowired
    private SinistreDao sinistreDao;
    @Autowired
    private HistoryConsultationDao historyConsultationDao;
    @Autowired
    private ExcelExportPoliceService excelExportService;




    @GetMapping(path = "/getAdmin")
    public List<AdminEntity> getAdmin() {
        return adminDao.getAdmins();
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody UserEntity user) {

        String username = user.getUsername();
        String password = user.getPassword();
        String profile = user.getProfil();
        if (profile.equals("administrateur")) {

            if (authenticationdao.authenticate(username, password)) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
            }
        } else if (profile.equals("personnel")) {

            if (authenticationdao.authenticatePer(username, password)) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
            }
        } else if (profile.equals("archiviste")) {

            if (authenticationdao.authenticateArch(username, password)) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
            }
        }
        return null;

    }

    @GetMapping(path = "login/demandePR")
    public List<String> getArchiviste() {
        if (true) {
            List<ArchivisteEntity> Archiviste = new ArrayList<>();
            List<String> listOfName = new ArrayList<>();
            Archiviste = archichivisteDao.getArchiviste();
            for (ArchivisteEntity arch : Archiviste) {

                listOfName.add(arch.getFirstName() + " " + arch.getLastName());
            }
            return listOfName;
        }
        return null;
    }

    @PostMapping(path = "login/demandePR")
    public void addRes(@RequestBody ReservationEntity reservation) {

        reservationDao.addReservation(reservation);
       emailService.sendRequestFolderEmail(reservation.getCodeDossier(),reservation.getNamePer(), reservation.getDateRes(),reservation.getDateRet());
    }

    @PostMapping(path = "login/Admin-menu/AjoutPR")
    public void addProfil(@RequestBody PersonnelEntity profil) {
        profil.setUsername(profil.getFirstName() + "." + profil.getLastName());
        if (profil.getProfil().equals("Personnel")) {
            personnelDao.addProfil(profil);
        } else if (profil.getProfil().equals("Archiviste")) {
            ArchivisteEntity archiviste = new ArchivisteEntity();
            archiviste.setFirstName(profil.getFirstName());
            archiviste.setLastName(profil.getLastName());
            archiviste.setEmail(profil.getEmail());
            archiviste.setUsername(profil.getUsername());
            archiviste.setPassword(profil.getPassword());
            archichivisteDao.addProfil(archiviste);

        }
    }

    @PostMapping(path = "login/Admin-menu/GererPR")
    public List<String> getSuggestionsNom(@RequestBody String profil) {
        if (profil.equals("Archiviste")) {
            List<ArchivisteEntity> Archiviste = new ArrayList<>();
            List<String> listOfName = new ArrayList<>();
            Archiviste = archichivisteDao.getArchiviste();
            for (ArchivisteEntity arch : Archiviste) {

                listOfName.add(arch.getFirstName() + " " + arch.getLastName());
            }
            return listOfName;
        } else if (profil.equals("Personnel")) {
            List<PersonnelEntity> listPersonnel = new ArrayList<>();
            List<String> listOfName = new ArrayList<>();
            listPersonnel = personnelDao.getPersonnel();
            for (PersonnelEntity Per : listPersonnel) {

                listOfName.add(Per.getFirstName() + " " + Per.getLastName());
            }
            return listOfName;
        }
        return null;
    }

    @PostMapping(path = "login/Admin-menu/GererPR/consulterPr")
    public List<String> getUser(@RequestBody Map<String, String> data) {
        String name = data.get("name");
        String profil = data.get("profil");
        String[] words = name.split(" ");
        String login = words[0] + "." + words[words.length - 1];
        List<String> listUser = new ArrayList<>();
        if (profil.equals("Personnel")) {
            PersonnelEntity personnel = new PersonnelEntity();
            personnel = personnelDao.getPersonnelByLogin(login);
            if (personnel != null) {
                listUser.add(personnel.getFirstName());
                listUser.add(personnel.getLastName());
                listUser.add(personnel.getEmail());
                listUser.add(personnel.getUsername());
                listUser.add(personnel.getPassword());
                listUser.add(profil);
                return listUser;
            }
        } else if (profil.equals("Archiviste")) {
            ArchivisteEntity archiviste = new ArchivisteEntity();
            archiviste = archichivisteDao.getArchivisteByLogin(login);
            if (archiviste != null) {
                listUser.add(archiviste.getFirstName());
                listUser.add(archiviste.getLastName());
                listUser.add(archiviste.getEmail());
                listUser.add(archiviste.getUsername());
                listUser.add(archiviste.getPassword());
                listUser.add(profil);
                return listUser;
            }
        }

        return null;
    }

    @DeleteMapping(path = "login/Admin-menu/GererPR/consulterPr/{login}/{profil}")
    public void deleteUser(@PathVariable String login, @PathVariable String profil) {
        if (profil.equals("Personnel")) {
            personnelDao.deletePersonnel(login);
        } else if (profil.equals("Archiviste")) {

            archichivisteDao.deleteArchiviste(login);
        }
    }

    @PutMapping(path = "login/Admin-menu/GererPR/consulterPr/{aux}")
    public void updateItem(@RequestBody PersonnelEntity newUserUpdate, @PathVariable String aux) {
        if (newUserUpdate.getProfil().equals("Personnel")) {
            PersonnelEntity updatedItem = personnelDao.updateItem(newUserUpdate, aux);
        } else if (newUserUpdate.getProfil().equals("Archiviste")) {
            ArchivisteEntity updatedItem = archichivisteDao.updateUser(newUserUpdate, aux);

        }


    }

    @PostMapping(path = "login/ArchiMenu/AjoutD")
    public List<ReservationDummyEntity> addfolder(@RequestBody Map<String, String> data) {
        //police
        String[] autoP = {"20", "21", "22", "23", "24", "25", "26", "27", "28","29","76","74","79","86","93","94"};
        String[] dprod = {"50", "51", "52", "53", "54", "55", "70", "71", "72", "73", "76", "77", "80", "81", "82", "83", "84", "85", "86", "87", "92", "93"};
        String[] rprod = {"60", "79", "86", "93"};
        String[] tprod = {"10", "11", "12", "15", "16", "17", "18"};
        String[] vprod = {"39", "61"};

        //sinistre
        String[] autoS = {"20", "21", "22", "23", "24", "25", "26", "28", "29","76","74","79","86","93","94"};
        String[] dSinistre = {"50", "51", "52", "53", "54", "55", "70", "71", "72", "73", "80", "81", "82", "83", "84", "85", "86", "87"};
        String[] rSinistre = {"60"};
        String[] tSinistre = {"10", "11", "12", "15", "16", "17", "18","19"};
        String typeDossier = data.get("TypeDossier");
        String categorie = data.get("categorie");
        String n_Dossier = data.get("n_Dossier");
        String annee = data.get("annee");
        String codeDossier="";
        if(typeDossier.equals("Police")) {
             codeDossier = categorie + annee + n_Dossier;
        } else if (typeDossier.equals("Sinistre")) {
            codeDossier = annee + categorie + n_Dossier;
        } else if (typeDossier.equals("Cxp")) {
            codeDossier = annee + n_Dossier;
        }
        List<ReservationDummyEntity> checkExiste= getFolder(typeDossier,codeDossier);
        if(checkExiste.size()==0) {
            if (typeDossier.equals("Police")) {
                for (String str : autoP) {
                    if (categorie.equals(str)) {
                        System.out.println("The categorie matches with belongs to the auto branche: ");
                        AutoPoliceEntity autoPolice = new AutoPoliceEntity();
                        autoPolice.setNPolice(codeDossier);
                        autoPoliceDao.addDossier(autoPolice);

                    }
                }
                for (String str : dprod) {
                    if (categorie.equals(str)) {
                        System.out.println("The categorie matches with belongs to the d_police branche: ");
                        DPoliceEntity dPolice = new DPoliceEntity();
                        dPolice.setNPolice(codeDossier);
                        dPoliceDao.addDossier(dPolice);


                    }
                }
                for (String str : rprod) {
                    if (categorie.equals(str)) {
                        System.out.println("The categorie matches with belongs to the r_police branche: ");
                        RPoliceEntity rPolice = new RPoliceEntity();
                        rPolice.setNPolice(codeDossier);
                        rPoliceDao.addDossier(rPolice);

                    }
                }
                for (String str : tprod) {
                    if (categorie.equals(str)) {
                        System.out.println("The categorie matches with belongs to the t_police branche: ");
                        TPoliceEntity tPolice = new TPoliceEntity();
                        tPolice.setNPolice(codeDossier);
                        tPoliceDao.addDossier(tPolice);

                    }
                }
                for (String str : vprod) {
                    if (categorie.equals(str)) {
                        System.out.println("The categorie matches with belongs to the v_police branche: ");
                        VPoliceEntity vPolice = new VPoliceEntity();
                        vPolice.setNPolice(codeDossier);
                        vPoliceDao.addDossier(vPolice);

                    }
                }
            } else if (typeDossier.equals("Sinistre")) {
                for (String str : autoS) {
                    if (categorie.equals(str)) {
                        System.out.println("The categorie matches with belongs to the auto-sinistre branche: ");
                        AutoSinistreEntity autoSinistre = new AutoSinistreEntity();
                        autoSinistre.setNPolice(codeDossier);
                        autoSinistreDao.addDossier(autoSinistre);

                    }
                }

                for (String str : dSinistre) {
                    if (categorie.equals(str)) {
                        System.out.println("The categorie matches with belongs to the auto-sinistre branche: ");
                        DsinistreEntity dSinistr = new DsinistreEntity();
                        dSinistr.setNPolice(codeDossier);
                        dSinistreDao.addDossier(dSinistr);

                    }
                }

                for (String str : rSinistre) {
                    if (categorie.equals(str)) {
                        System.out.println("The categorie matches with belongs to the auto-sinistre branche: ");
                        RsinistreEntity rsinistr = new RsinistreEntity();
                        rsinistr.setNPolice(codeDossier);
                        rSinistreDao.addDossier(rsinistr);

                    }
                }

                for (String str : tSinistre) {
                    if (categorie.equals(str)) {
                        System.out.println("The categorie matches with belongs to the auto-sinistre branche: ");
                        TsinistreEntity tsinistr = new TsinistreEntity();
                        tsinistr.setNPolice(codeDossier);
                        tSinistreDao.addDossier(tsinistr);

                    }
                }
            } else if (typeDossier.equals("Cxp")) {
                System.out.println("The categorie matches with belongs to the cxp branche: ");
                CxpEntity cxp = new CxpEntity();
                cxp.setNCxp(codeDossier);
                cxpDao.addDossier(cxp);

            }
            return checkExiste;
        }
        else {
            return checkExiste;
        }
    }


    @GetMapping(path = "login/ArchiMenu/RechercheD/{TypeDossier}/{n_Dossier}")
    public List<ReservationDummyEntity> getFolder(@PathVariable String TypeDossier, @PathVariable String n_Dossier) {
        //police
        String[] autoP = {"20", "21", "22", "23", "24", "25", "26", "27", "28", "29","76","74","79","86","93","94"};
        String[] dprod = {"32","50", "51", "52", "53", "54", "55", "70", "71", "72", "73", "76", "77", "80", "81", "82", "83", "84", "85", "86", "87", "92", "93"};
        String[] rprod = {"60", "79", "86", "93"};
        String[] tprod = {"10", "11", "12", "15", "16", "17", "18"};
        String[] vprod = {"39", "61"};

        //sinistre
        String[] autoS = {"20", "21", "22", "23", "24", "25", "26", "28", "29","76","74","79","86","93","94"};
        String[] dSinistre = {"33","34","50", "51", "52", "53", "54", "55", "70", "71", "72", "73", "80", "81", "82", "83", "84", "85", "86", "87","91"};
        String[] rSinistre = {"60"};
        String[] tSinistre = {"10", "11", "12", "15", "16", "17", "18","19"};

        if (TypeDossier.equals("Police")) {
            String categorie = n_Dossier.substring(0, 2);
            System.out.println(categorie);
            List<ReservationDummyEntity> resDammy = new ArrayList<>();
            for (String str : autoP) {
                if (categorie.equals(str)) {
                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<AutoPoliceEntity> autoPolice = new ArrayList<>();
                    autoPolice = autoPoliceDao.getAllPolice(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    int i = 0;
                    for (AutoPoliceEntity cp : autoPolice) {
                        if (i < consultation.size()) {
                            res.setConsulterPar(consultation.get(i).getConsulterPar());
                            res.setDateConsultation(consultation.get(i).getDateConsultation());
                            res.setDateRetour(consultation.get(i).getDateRetour());
                            cp.setEtat(1);
                        }
                        res.setNPolice(cp.getNPolice());
                        res.setEtat(cp.getEtat());
                        resDammy.add(res);
                        res = new ReservationDummyEntity();
                        i++;
                    }

                    return resDammy;
                }


            }

            for (String str : dprod) {
                if (categorie.equals(str)) {
                    //return dPoliceDao.getFolder(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<DPoliceEntity> dPolice = new ArrayList<>();
                    dPolice = dPoliceDao.getFolder(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    int i = 0;
                    for (DPoliceEntity cp : dPolice) {
                        if (i < consultation.size()) {
                            res.setConsulterPar(consultation.get(i).getConsulterPar());
                            res.setDateConsultation(consultation.get(i).getDateConsultation());
                            res.setDateRetour(consultation.get(i).getDateRetour());
                            cp.setEtat(1);
                        }
                        res.setNPolice(cp.getNPolice());
                        res.setEtat(cp.getEtat());
                        resDammy.add(res);
                        res = new ReservationDummyEntity();
                        i++;
                    }

                    return resDammy;
                }


            }

            for (String str : rprod) {
                if (categorie.equals(str)) {
                    //  return rPoliceDao.getFolderRpolice(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<RPoliceEntity> rPolice = new ArrayList<>();
                    rPolice = rPoliceDao.getFolderRpolice(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    int i = 0;
                    for (RPoliceEntity cp : rPolice) {
                        if (i < consultation.size()) {
                            res.setConsulterPar(consultation.get(i).getConsulterPar());
                            res.setDateConsultation(consultation.get(i).getDateConsultation());
                            res.setDateRetour(consultation.get(i).getDateRetour());
                            cp.setEtat(1);
                        }
                        res.setNPolice(cp.getNPolice());
                        res.setEtat(cp.getEtat());
                        resDammy.add(res);
                        res = new ReservationDummyEntity();
                        i++;
                    }

                    return resDammy;
                }


            }

            for (String str : tprod) {
                if (categorie.equals(str)) {
                    //  return tPoliceDao.getFolderTpolice(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<TPoliceEntity> tPolice = new ArrayList<>();
                    tPolice = tPoliceDao.getFolderTpolice(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    int i = 0;
                    for (TPoliceEntity cp : tPolice) {
                        if (i < consultation.size()) {
                            res.setConsulterPar(consultation.get(i).getConsulterPar());
                            res.setDateConsultation(consultation.get(i).getDateConsultation());
                            res.setDateRetour(consultation.get(i).getDateRetour());
                            cp.setEtat(1);
                        }
                        res.setNPolice(cp.getNPolice());
                        res.setEtat(cp.getEtat());
                        resDammy.add(res);
                        res = new ReservationDummyEntity();
                        i++;
                    }

                    return resDammy;
                }


            }

            for (String str : vprod) {
                if (categorie.equals(str)) {
                    // return vPoliceDao.getFolderVpolice(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<VPoliceEntity> vPolice = new ArrayList<>();
                    vPolice = vPoliceDao.getFolderVpolice(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    int i = 0;
                    for (VPoliceEntity cp : vPolice) {
                        if (i < consultation.size()) {
                            res.setConsulterPar(consultation.get(i).getConsulterPar());
                            res.setDateConsultation(consultation.get(i).getDateConsultation());
                            res.setDateRetour(consultation.get(i).getDateRetour());
                            cp.setEtat(1);
                        }
                        res.setNPolice(cp.getNPolice());
                        res.setEtat(cp.getEtat());
                        resDammy.add(res);
                        res = new ReservationDummyEntity();
                        i++;
                    }

                    return resDammy;
                }


            }

        }

        if (TypeDossier.equals("Sinistre")) {
            String categorie = n_Dossier.substring(4, 6);
            System.out.println(categorie);
            List<ReservationDummyEntity> resDammy = new ArrayList<>();
            for (String str : autoS) {
                if (categorie.equals(str)) {
                    // return autoSinistreDao.getFolderAutoSinistre(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<AutoSinistreEntity> autoSinistre = new ArrayList<>();
                    autoSinistre = autoSinistreDao.getFolderAutoSinistre(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    int i = 0;
                    for (AutoSinistreEntity cp : autoSinistre) {
                        if (i < consultation.size()) {
                            res.setConsulterPar(consultation.get(i).getConsulterPar());
                            res.setDateConsultation(consultation.get(i).getDateConsultation());
                            res.setDateRetour(consultation.get(i).getDateRetour());
                            cp.setEtat(1);
                        }
                        res.setNPolice(cp.getNPolice());
                        res.setEtat(cp.getEtat());
                        resDammy.add(res);
                        res = new ReservationDummyEntity();
                        i++;
                    }

                    return resDammy;
                }


            }

            for (String str : dSinistre) {
                if (categorie.equals(str)) {
                    //return dSinistreDao.getFoldetDsinistre(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<DsinistreEntity> dSinistr = new ArrayList<>();
                    dSinistr = dSinistreDao.getFoldetDsinistre(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    int i = 0;
                    for (DsinistreEntity cp : dSinistr) {
                        if (i < consultation.size()) {
                            res.setConsulterPar(consultation.get(i).getConsulterPar());
                            res.setDateConsultation(consultation.get(i).getDateConsultation());
                            res.setDateRetour(consultation.get(i).getDateRetour());
                            cp.setEtat(1);
                        }
                        res.setNPolice(cp.getNPolice());
                        res.setEtat(cp.getEtat());
                        resDammy.add(res);
                        res = new ReservationDummyEntity();
                        i++;
                    }

                    return resDammy;
                }


            }

            for (String str : rSinistre) {
                if (categorie.equals(str)) {
                    //  return rSinistreDao.getFolderRsinistre(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<RsinistreEntity> rSinistr = new ArrayList<>();
                    rSinistr = rSinistreDao.getFolderRsinistre(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    int i = 0;
                    for (RsinistreEntity cp : rSinistr) {
                        if (i < consultation.size()) {
                            res.setConsulterPar(consultation.get(i).getConsulterPar());
                            res.setDateConsultation(consultation.get(i).getDateConsultation());
                            res.setDateRetour(consultation.get(i).getDateRetour());
                            cp.setEtat(1);
                        }
                        res.setNPolice(cp.getNPolice());
                        res.setEtat(cp.getEtat());
                        resDammy.add(res);
                        res = new ReservationDummyEntity();
                        i++;
                    }

                    return resDammy;
                }


            }

            for (String str : tSinistre) {
                if (categorie.equals(str)) {
                    //return tSinistreDao.getFolderTsinistre(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<TsinistreEntity> tSinistr = new ArrayList<>();
                    tSinistr = tSinistreDao.getFolderTsinistre(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    int i = 0;
                    for (TsinistreEntity cp : tSinistr) {
                        if (i < consultation.size()) {
                            res.setConsulterPar(consultation.get(i).getConsulterPar());
                            res.setDateConsultation(consultation.get(i).getDateConsultation());
                            res.setDateRetour(consultation.get(i).getDateRetour());
                            cp.setEtat(1);
                        }
                        res.setNPolice(cp.getNPolice());
                        res.setEtat(cp.getEtat());
                        resDammy.add(res);
                        res = new ReservationDummyEntity();
                        i++;
                    }

                    return resDammy;
                }


            }


        }
        if (TypeDossier.equals("Cxp")) {
            //  return cxpDao.getFolderCxp(n_Dossier);
            List<ReservationDummyEntity> resDammy = new ArrayList<>();
            List<ConsultationEntity> consultation = new ArrayList<>();
            ReservationDummyEntity res = new ReservationDummyEntity();
            List<CxpEntity> cxp = new ArrayList<>();
            cxp = cxpDao.getFolderCxp(n_Dossier);
            consultation = consultationDao.getCosultation(n_Dossier);
            int i = 0;
            for (CxpEntity cp : cxp) {
                if (i < consultation.size()) {
                    res.setConsulterPar(consultation.get(i).getConsulterPar());
                    res.setDateConsultation(consultation.get(i).getDateConsultation());
                    res.setDateRetour(consultation.get(i).getDateRetour());

                }
                res.setNPolice(cp.getNCxp());
                resDammy.add(res);
                res = new ReservationDummyEntity();
                i++;
            }

            return resDammy;
        }


        return null;

    }

    @GetMapping(path = "login/ArchiMenu/RechercheD/gridD/reservation")
    public List<String> getNamePersonnel() {

        List<PersonnelEntity> listPersonnel = new ArrayList<>();
        List<String> listOfName = new ArrayList<>();
        listPersonnel = personnelDao.getPersonnel();
        for (PersonnelEntity Per : listPersonnel) {

            listOfName.add(Per.getFirstName() + " " + Per.getLastName());
        }
        return listOfName;
    }

    @PostMapping(path = "login/ArchiMenu/RechercheD/gridD/reservation")
    public void addConsultation(@RequestBody ConsultationEntity consultation) {
        LocalDateTime currentDate = LocalDateTime.now();
        //Date currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        //List<?> outFolder = new ArrayList<>();
        String archivisteAjout="";
        if(consultation.getTypeAffaire().equals("Police")){
            PoliceEntity p= policeDao.findFolder(consultation.getNaffaire());
            archivisteAjout=p.getUtilisateur();
        }
        if(consultation.getTypeAffaire().equals("Sinistre")){
            SinistreEntity p= sinistreDao.findSinistre(consultation.getNaffaire());
            archivisteAjout=p.getUtilisateur();
        }
        if(consultation.getTypeAffaire().equals("Cxp")){
            List<CxpEntity> p= cxpDao.getFolderCxp(consultation.getNaffaire());
            for(CxpEntity cp:p) {
                archivisteAjout = cp.getUtilisateur();
            }
        }

        List<ReservationEntity> listRes=new ArrayList<>();
        ConsultationEntity cons=new ConsultationEntity();
        cons.setNaffaire(consultation.getNaffaire());
        cons.setConsulterPar(consultation.getConsulterPar());
            if(consultation.getDateConsultation()==null){
                cons.setDateConsultation(currentDate.toLocalDate());
            }else{
                cons.setDateConsultation(consultation.getDateConsultation());
            }

        cons.setDateRetour(consultation.getDateRetour());
        cons.setDateRetourReel(cons.getDateRetourReel());
        cons.setDateInsertion(currentDate);
        cons.setArchivisteCons(consultation.getArchivisteCons());
        cons.setArchivisteAjout(archivisteAjout);
        cons.setTypeAffaire(consultation.getTypeAffaire());
        consultationDao.addConsltation(cons);

    }


    @PostMapping(path = "login/forgotPassword")
    public void sendPasswordResetEmail(@RequestBody String email) {
        // Generate a unique URL for password reset
        String resetUrl = "http://172.16.115.201:4200/login/changePassord/lkjsdhsdquydqsdqomiqd54868qdsbqsdjjgqsgvqs5454sdqskjbqsgazuhsjhgsd11156efjdn";

        // Send the email
        emailService.sendPasswordResetEmail(email, resetUrl);
    }

    @PostMapping(path = "login/changePassord/lkjsdhsdquydqsdqomiqd54868qdsbqsdjjgqsgvqs5454sdqskjbqsgazuhsjhgsd11156efjdn")
    public String changePassword(@RequestBody Map<String, String> userinfo) {
        ArchivisteEntity archiviste = archichivisteDao.getArchivisteByLogin(userinfo.get("username"));
        PersonnelEntity personnel = personnelDao.getPersonnelByLogin(userinfo.get("username"));
        if (archiviste != null) {
            archichivisteDao.PasswordChanged(archiviste, userinfo.get("password"));
            return "1";
        } else if (personnel != null) {
            personnelDao.PasswordChanged(personnel, userinfo.get("password"));
            return "1";
        } else {
            return "0";
        }


    }

    @GetMapping(path = "login/listReservation")
    public List<ReservationEntity> getListReservation() {
        return reservationDao.getREservations();
    }

    @GetMapping(path = "login/demandePR/{code}")
    public List<?> checkCode(@PathVariable String code) {
        List<ReservationEntity> checkReservation = reservationDao.findConsultation(code);
        List<ConsultationEntity> checkConsultation = consultationDao.getConsultationsByCode(code);
        if (checkReservation.size()==0 && checkConsultation.size()==0) {
            return null;
        } else if (checkReservation.size()!=0 && checkConsultation.size()==0) {
            return checkReservation;
        } else if (checkReservation.size()==0 && checkConsultation.size() != 0) {
            return checkConsultation;
        }
        else if (checkReservation.size()!=0 && checkConsultation.size()!=0) {
            return checkReservation;
        }
        else {
            return null;
        }
    }

    @DeleteMapping(path = "login/ArchiMenu/listReservation/{numDossier}/{nameArchiviste}/{dateRetour}")
    public void deleteConsultation(@PathVariable String numDossier,@PathVariable String nameArchiviste,@PathVariable String dateRetour) {
        LocalDateTime currentDate = LocalDateTime.now();
        //DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy à HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateRetour, formatter);
        ConsultationHistoriqueEntity historiqueConsultation=new ConsultationHistoriqueEntity();
        List<ConsultationEntity> consultations=new ArrayList<>();
        consultations=consultationDao.getCosultation(numDossier);

        String archivisteAjout="";
        for(ConsultationEntity consultation:consultations ) {
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
        for(ConsultationEntity cp:consultations){
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
        }
        consultationDao.deleteConsultation(numDossier);
    }

    @GetMapping(path = "login/checkDossier/{typeDossier}/{numFolder}/{username}")
    public boolean checkFolder(@PathVariable String typeDossier,@PathVariable String numFolder,@PathVariable String username){
        List<ReservationDummyEntity> folder= new ArrayList<>();
        String departement=getDepartement(username);
        folder=getFolder2(typeDossier,numFolder,departement);
        if(folder!=null) {
            if (folder.size() != 0) {
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }
    }

    public String getDepartement(String login){
        PersonnelEntity personnel= personnelDao.getPersonnelByLogin(login);
        String departement= personnel.getDepartment();
        return departement;
    }


    public List<ReservationDummyEntity> getFolder2( String TypeDossier, String n_Dossier ,String depart) {
        //police
        String[] autoP = {"20", "21", "22", "23", "24", "25", "26", "27", "28", "29","76","74","79","86","93","94"};
        String[] dprod = {"32","50", "51", "52", "53", "54", "55", "70", "71", "72", "73", "76", "77", "80", "81", "82", "83", "84", "85", "86", "87", "92", "93"};
        String[] rprod = {"60", "79", "86", "93"};
        String[] tprod = {"10", "11", "12", "15", "16", "17", "18"};
        String[] vprod = {"39", "61"};

        //sinistre
        String[] autoS = {"20", "21", "22", "23", "24", "25", "26", "28", "29","76","74","79","86","93","94"};
        String[] dSinistre = {"33","34","50", "51", "52", "53", "54", "55", "70", "71", "72", "73", "80", "81", "82", "83", "84", "85", "86", "87","91"};
        String[] rSinistre = {"60"};
        String[] tSinistre = {"10", "11", "12", "15", "16", "17", "18","19"};

        if (TypeDossier.equals("Police")) {
            String categorie = n_Dossier.substring(0, 2);
            System.out.println(categorie);
            List<ReservationDummyEntity> resDammy = new ArrayList<>();
            for (String str : autoP) {
                if (categorie.equals(str) && ( (depart.equals("AUTO")) || (depart.equals("AUDIT")) )){
                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<AutoPoliceEntity> autoPolice = new ArrayList<>();
                    autoPolice = autoPoliceDao.getAllPolice(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    int i = 0;
                    for (AutoPoliceEntity cp : autoPolice) {
                        if (i < consultation.size()) {
                            res.setConsulterPar(consultation.get(i).getConsulterPar());
                            res.setDateConsultation(consultation.get(i).getDateConsultation());
                            res.setDateRetour(consultation.get(i).getDateRetour());
                        }
                        res.setNPolice(cp.getNPolice());
                        res.setEtat(cp.getEtat());
                        resDammy.add(res);
                        res = new ReservationDummyEntity();
                        i++;
                    }

                    return resDammy;
                }


            }

            for (String str : dprod) {
                if (categorie.equals(str) && ( (depart.equals("IRDS")) || (depart.equals("AUDIT")) )) {
                    //return dPoliceDao.getFolder(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<DPoliceEntity> dPolice = new ArrayList<>();
                    dPolice = dPoliceDao.getFolder(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    int i = 0;
                    for (DPoliceEntity cp : dPolice) {
                        if (i < consultation.size()) {
                            res.setConsulterPar(consultation.get(i).getConsulterPar());
                            res.setDateConsultation(consultation.get(i).getDateConsultation());
                            res.setDateRetour(consultation.get(i).getDateRetour());
                        }
                        res.setNPolice(cp.getNPolice());
                        res.setEtat(cp.getEtat());
                        resDammy.add(res);
                        res = new ReservationDummyEntity();
                        i++;
                    }

                    return resDammy;
                }


            }

            for (String str : rprod) {
                if (categorie.equals(str) && ( (depart.equals("RC")) || (depart.equals("AUDIT")) )) {
                    //  return rPoliceDao.getFolderRpolice(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<RPoliceEntity> rPolice = new ArrayList<>();
                    rPolice = rPoliceDao.getFolderRpolice(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    int i = 0;
                    for (RPoliceEntity cp : rPolice) {
                        if (i < consultation.size()) {
                            res.setConsulterPar(consultation.get(i).getConsulterPar());
                            res.setDateConsultation(consultation.get(i).getDateConsultation());
                            res.setDateRetour(consultation.get(i).getDateRetour());
                        }
                        res.setNPolice(cp.getNPolice());
                        res.setEtat(cp.getEtat());
                        resDammy.add(res);
                        res = new ReservationDummyEntity();
                        i++;
                    }

                    return resDammy;
                }


            }

            for (String str : tprod) {
                if (categorie.equals(str) && ( (depart.equals("TRANSPORT")) || (depart.equals("AUDIT")) )) {
                    //  return tPoliceDao.getFolderTpolice(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<TPoliceEntity> tPolice = new ArrayList<>();
                    tPolice = tPoliceDao.getFolderTpolice(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    int i = 0;
                    for (TPoliceEntity cp : tPolice) {
                        if (i < consultation.size()) {
                            res.setConsulterPar(consultation.get(i).getConsulterPar());
                            res.setDateConsultation(consultation.get(i).getDateConsultation());
                            res.setDateRetour(consultation.get(i).getDateRetour());
                        }
                        res.setNPolice(cp.getNPolice());
                        res.setEtat(cp.getEtat());
                        resDammy.add(res);
                        res = new ReservationDummyEntity();
                        i++;
                    }

                    return resDammy;
                }


            }

            for (String str : vprod) {
                if (categorie.equals(str) && ( (depart.equals("VIE")) || (depart.equals("AUDIT")) )) {
                    // return vPoliceDao.getFolderVpolice(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<VPoliceEntity> vPolice = new ArrayList<>();
                    vPolice = vPoliceDao.getFolderVpolice(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    int i = 0;
                    for (VPoliceEntity cp : vPolice) {
                        if (i < consultation.size()) {
                            res.setConsulterPar(consultation.get(i).getConsulterPar());
                            res.setDateConsultation(consultation.get(i).getDateConsultation());
                            res.setDateRetour(consultation.get(i).getDateRetour());
                        }
                        res.setNPolice(cp.getNPolice());
                        res.setEtat(cp.getEtat());
                        resDammy.add(res);
                        res = new ReservationDummyEntity();
                        i++;
                    }

                    return resDammy;
                }


            }

        }

        if (TypeDossier.equals("Sinistre")) {
            String categorie = n_Dossier.substring(4, 6);
            System.out.println(categorie);
            List<ReservationDummyEntity> resDammy = new ArrayList<>();
            for (String str : autoS) {
                if (categorie.equals(str) && ( (depart.equals("AUTO")) || (depart.equals("COMMERCIAL")) || (depart.equals("AUDIT")) )) {
                    // return autoSinistreDao.getFolderAutoSinistre(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<AutoSinistreEntity> autoSinistre = new ArrayList<>();
                    autoSinistre = autoSinistreDao.getFolderAutoSinistre(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    int i = 0;
                    for (AutoSinistreEntity cp : autoSinistre) {
                        if (i < consultation.size()) {
                            res.setConsulterPar(consultation.get(i).getConsulterPar());
                            res.setDateConsultation(consultation.get(i).getDateConsultation());
                            res.setDateRetour(consultation.get(i).getDateRetour());
                        }
                        res.setNPolice(cp.getNPolice());
                        res.setEtat(cp.getEtat());
                        resDammy.add(res);
                        res = new ReservationDummyEntity();
                        i++;
                    }

                    return resDammy;
                }


            }

            for (String str : dSinistre) {
                if (categorie.equals(str) && ( (depart.equals("IRDS")) || (depart.equals("AUDIT")) )) {
                    //return dSinistreDao.getFoldetDsinistre(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<DsinistreEntity> dSinistr = new ArrayList<>();
                    dSinistr = dSinistreDao.getFoldetDsinistre(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    int i = 0;
                    for (DsinistreEntity cp : dSinistr) {
                        if (i < consultation.size()) {
                            res.setConsulterPar(consultation.get(i).getConsulterPar());
                            res.setDateConsultation(consultation.get(i).getDateConsultation());
                            res.setDateRetour(consultation.get(i).getDateRetour());
                        }
                        res.setNPolice(cp.getNPolice());
                        res.setEtat(cp.getEtat());
                        resDammy.add(res);
                        res = new ReservationDummyEntity();
                        i++;
                    }

                    return resDammy;
                }


            }

            for (String str : rSinistre) {
                if (categorie.equals(str) && ( (depart.equals("RC")) || (depart.equals("AUDIT")) )) {
                    //  return rSinistreDao.getFolderRsinistre(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<RsinistreEntity> rSinistr = new ArrayList<>();
                    rSinistr = rSinistreDao.getFolderRsinistre(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    int i = 0;
                    for (RsinistreEntity cp : rSinistr) {
                        if (i < consultation.size()) {
                            res.setConsulterPar(consultation.get(i).getConsulterPar());
                            res.setDateConsultation(consultation.get(i).getDateConsultation());
                            res.setDateRetour(consultation.get(i).getDateRetour());
                        }
                        res.setNPolice(cp.getNPolice());
                        res.setEtat(cp.getEtat());
                        resDammy.add(res);
                        res = new ReservationDummyEntity();
                        i++;
                    }

                    return resDammy;
                }


            }

            for (String str : tSinistre) {
                if (categorie.equals(str) && ( (depart.equals("TRANSPORT")) || (depart.equals("AUDIT")) )) {
                    //return tSinistreDao.getFolderTsinistre(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<TsinistreEntity> tSinistr = new ArrayList<>();
                    tSinistr = tSinistreDao.getFolderTsinistre(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    int i = 0;
                    for (TsinistreEntity cp : tSinistr) {
                        if (i < consultation.size()) {
                            res.setConsulterPar(consultation.get(i).getConsulterPar());
                            res.setDateConsultation(consultation.get(i).getDateConsultation());
                            res.setDateRetour(consultation.get(i).getDateRetour());
                        }
                        res.setNPolice(cp.getNPolice());
                        res.setEtat(cp.getEtat());
                        resDammy.add(res);
                        res = new ReservationDummyEntity();
                        i++;
                    }

                    return resDammy;
                }


            }


        }
        if (TypeDossier.equals("Cxp") && ( (depart.equals("CXP")) || (depart.equals("AUDIT")) )) {
            //  return cxpDao.getFolderCxp(n_Dossier);
            List<ReservationDummyEntity> resDammy = new ArrayList<>();
            List<ConsultationEntity> consultation = new ArrayList<>();
            ReservationDummyEntity res = new ReservationDummyEntity();
            List<CxpEntity> cxp = new ArrayList<>();
            cxp = cxpDao.getFolderCxp(n_Dossier);
            consultation = consultationDao.getCosultation(n_Dossier);
            int i = 0;
            for (CxpEntity cp : cxp) {
                if (i < consultation.size()) {
                    res.setConsulterPar(consultation.get(i).getConsulterPar());
                    res.setDateConsultation(consultation.get(i).getDateConsultation());
                    res.setDateRetour(consultation.get(i).getDateRetour());
                }
                res.setNPolice(cp.getNCxp());

                resDammy.add(res);
                res = new ReservationDummyEntity();
                i++;
            }

            return resDammy;
        }


        return null;

    }


    @DeleteMapping(path = "login/ArchiMenu/deleteDossier/{TypeDossier}/{n_Dossier}")
    public void deleteFolder(@PathVariable String TypeDossier, @PathVariable String n_Dossier) {
        //police
        String[] autoP = {"20", "21", "22", "23", "24", "25", "26", "27", "28", "29","76","74","79","86","93","94"};
        String[] dprod = {"32","50", "51", "52", "53", "54", "55", "70", "71", "72", "73", "76", "77", "80", "81", "82", "83", "84", "85", "86", "87", "92", "93"};
        String[] rprod = {"60", "79", "86", "93"};
        String[] tprod = {"10", "11", "12", "15", "16", "17", "18"};
        String[] vprod = {"39", "61"};

        //sinistre
        String[] autoS = {"20", "21", "22", "23", "24", "25", "26", "28", "29","76","74","79","86","93","94"};
        String[] dSinistre = {"33","34","50", "51", "52", "53", "54", "55", "70", "71", "72", "73", "80", "81", "82", "83", "84", "85", "86", "87","91"};
        String[] rSinistre = {"60"};
        String[] tSinistre = {"10", "11", "12", "15", "16", "17", "18","19"};

        if (TypeDossier.equals("Police")) {
            String categorie = n_Dossier.substring(0, 2);
            System.out.println(categorie);
            List<ReservationDummyEntity> resDammy = new ArrayList<>();
            for (String str : autoP) {
                if (categorie.equals(str)) {
                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<AutoPoliceEntity> autoPolice = new ArrayList<>();
                    autoPolice = autoPoliceDao.getAllPolice(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    if(autoPolice.size()!=0 || consultation.size()!=0){
                        for(AutoPoliceEntity cp: autoPolice){
                            autoPoliceDao.deleteDossier(cp.getNPolice());
                        }
                        for(ConsultationEntity cons: consultation){
                            consultationDao.deleteConsultation(cons.getNaffaire());
                        }
                    }


                }


            }

            for (String str : dprod) {
                if (categorie.equals(str)) {
                    //return dPoliceDao.getFolder(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<DPoliceEntity> dPolice = new ArrayList<>();
                    dPolice = dPoliceDao.getFolder(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    if(dPolice.size()!=0 || consultation.size()!=0){
                        for(DPoliceEntity cp: dPolice){
                            dPoliceDao.deleteDossier(cp.getNPolice());
                        }
                        for(ConsultationEntity cons: consultation){
                            consultationDao.deleteConsultation(cons.getNaffaire());
                        }
                    }

                }


            }

            for (String str : rprod) {
                if (categorie.equals(str)) {
                    //  return rPoliceDao.getFolderRpolice(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<RPoliceEntity> rPolice = new ArrayList<>();
                    rPolice = rPoliceDao.getFolderRpolice(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    if(rPolice.size()!=0 || consultation.size()!=0){
                        for(RPoliceEntity cp: rPolice){
                            rPoliceDao.deleteDossier(cp.getNPolice());
                        }
                        for(ConsultationEntity cons: consultation){
                            consultationDao.deleteConsultation(cons.getNaffaire());
                        }
                    }

                }


            }

            for (String str : tprod) {
                if (categorie.equals(str)) {
                    //  return tPoliceDao.getFolderTpolice(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<TPoliceEntity> tPolice = new ArrayList<>();
                    tPolice = tPoliceDao.getFolderTpolice(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    if(tPolice.size()!=0 || consultation.size()!=0){
                        for(TPoliceEntity cp: tPolice){
                            tPoliceDao.deleteDossier(cp.getNPolice());
                        }
                        for(ConsultationEntity cons: consultation){
                            consultationDao.deleteConsultation(cons.getNaffaire());
                        }
                    }

                }


            }

            for (String str : vprod) {
                if (categorie.equals(str)) {
                    // return vPoliceDao.getFolderVpolice(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<VPoliceEntity> vPolice = new ArrayList<>();
                    vPolice = vPoliceDao.getFolderVpolice(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    if(vPolice.size()!=0 || consultation.size()!=0){
                        for(VPoliceEntity cp: vPolice){
                            vPoliceDao.deleteDossier(cp.getNPolice());
                        }
                        for(ConsultationEntity cons: consultation){
                            consultationDao.deleteConsultation(cons.getNaffaire());
                        }
                    }

                }


            }

        }

        if (TypeDossier.equals("Sinistre")) {
            String categorie = n_Dossier.substring(4, 6);
            System.out.println(categorie);
            List<ReservationDummyEntity> resDammy = new ArrayList<>();
            for (String str : autoS) {
                if (categorie.equals(str)) {
                    // return autoSinistreDao.getFolderAutoSinistre(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<AutoSinistreEntity> autoSinistre = new ArrayList<>();
                    autoSinistre = autoSinistreDao.getFolderAutoSinistre(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    if(autoSinistre.size()!=0 || consultation.size()!=0){
                        for(AutoSinistreEntity cp: autoSinistre){
                            autoSinistreDao.deleteDossier(cp.getNPolice());
                        }
                        for(ConsultationEntity cons: consultation){
                            consultationDao.deleteConsultation(cons.getNaffaire());
                        }
                    }

                }


            }

            for (String str : dSinistre) {
                if (categorie.equals(str)) {
                    //return dSinistreDao.getFoldetDsinistre(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<DsinistreEntity> dSinistr = new ArrayList<>();
                    dSinistr = dSinistreDao.getFoldetDsinistre(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    if(dSinistr.size()!=0 || consultation.size()!=0){
                        for(DsinistreEntity cp: dSinistr){
                            dSinistreDao.deleteDossier(cp.getNPolice());
                        }
                        for(ConsultationEntity cons: consultation){
                            consultationDao.deleteConsultation(cons.getNaffaire());
                        }
                    }

                }


            }

            for (String str : rSinistre) {
                if (categorie.equals(str)) {
                    //  return rSinistreDao.getFolderRsinistre(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<RsinistreEntity> rSinistr = new ArrayList<>();
                    rSinistr = rSinistreDao.getFolderRsinistre(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    if(rSinistr.size()!=0 || consultation.size()!=0){
                        for(RsinistreEntity cp: rSinistr){
                            rSinistreDao.deleteDossier(cp.getNPolice());
                        }
                        for(ConsultationEntity cons: consultation){
                            consultationDao.deleteConsultation(cons.getNaffaire());
                        }
                    }

                }


            }

            for (String str : tSinistre) {
                if (categorie.equals(str)) {
                    //return tSinistreDao.getFolderTsinistre(n_Dossier);

                    List<ConsultationEntity> consultation = new ArrayList<>();
                    ReservationDummyEntity res = new ReservationDummyEntity();
                    List<TsinistreEntity> tSinistr = new ArrayList<>();
                    tSinistr = tSinistreDao.getFolderTsinistre(n_Dossier);
                    consultation = consultationDao.getCosultation(n_Dossier);
                    if(tSinistr.size()!=0 || consultation.size()!=0){
                        for(TsinistreEntity cp: tSinistr){
                            tSinistreDao.deleteDossier(cp.getNPolice());
                        }
                        for(ConsultationEntity cons: consultation){
                            consultationDao.deleteConsultation(cons.getNaffaire());
                        }
                    }
                }


            }


        }
        if (TypeDossier.equals("Cxp")) {
            //  return cxpDao.getFolderCxp(n_Dossier);
            List<ReservationDummyEntity> resDammy = new ArrayList<>();
            List<ConsultationEntity> consultation = new ArrayList<>();
            ReservationDummyEntity res = new ReservationDummyEntity();
            List<CxpEntity> cxp = new ArrayList<>();
            cxp = cxpDao.getFolderCxp(n_Dossier);
            consultation = consultationDao.getCosultation(n_Dossier);
            if(cxp.size()!=0 || consultation.size()!=0){
                for(CxpEntity cp: cxp){
                    cxpDao.deleteDossier(cp.getNCxp());
                }
                for(ConsultationEntity cons: consultation){
                    consultationDao.deleteConsultation(cons.getNaffaire());
                }
            }

        }



    }


    @PostMapping(path = "Dossier/checkdossier/proasurDb/{typeDossier}")
    public List<?> getFolderExisteProassur(@PathVariable String typeDossier,@RequestBody List<Map<String,String>>  ListDossier){
        List<List<ViewPoliceProassurEntity>> auxPol = new ArrayList<>();
        List<List<ViewSinisterProassurEntity>>  auxSin = new ArrayList<>();
        List<List<ViewCxpProassurEntity>>  auxCxp = new ArrayList<>();
    for(Map<String,String> dossier: ListDossier) {
        if (typeDossier.equals("Police")) {
            auxPol.add(viewPoliceProassurDao.getPolice(dossier.get("dossier"))) ;
        } else if (typeDossier.equals(("Sinistre"))) {
            auxSin.add(viewSinistreProassurDao.getSinistre(dossier.get("dossier"))) ;
        } else if (typeDossier.equals(("Cxp"))) {
            auxCxp.add(viewCxpProassurDao.getcxp(dossier.get("dossier"))) ;
        } else {
            return null;
        }
    }
        if (typeDossier.equals("Police")) {
            return auxPol;
        }
        else if (typeDossier.equals("Sinistre")) {
            return auxSin;
        }
        else if (typeDossier.equals("Cxp")) {
            return auxCxp;
        }
        else{
            return null;
        }


    }

    @PostMapping(path = "Dossier/AjoutD/{typedossier}/{nameArchiviste}")
   public List<List<?>> addfolder_v2(@PathVariable String typedossier,@PathVariable String nameArchiviste,@RequestBody List<Map<String,String>>  ListDossier) {
        LocalDate localDate = LocalDate.now();
        Date currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        //List<?> outFolder = new ArrayList<>();

        List<PoliceEntity> auxPol = new ArrayList<>();
        List<SinistreEntity> auxSin = new ArrayList<>();
        List<CxpEntity> auxCxp = new ArrayList<>();
        List<Map<String,String>> listErr = new ArrayList<>();
        List<List<?>> outList = new ArrayList<>();

        for (Map<String,String> dossier: ListDossier) {

            String code = dossier.get("dossier");

            if (typedossier.equals("Police")) {


                List<ViewPoliceProassurEntity> dossierPolice = viewPoliceProassurDao.getPolice(code);
                if(dossierPolice.size() == 0){
                    dossier.put("erreur","Dossier inexistant dans PROASSUR");
                    listErr.add(dossier);
                    //return listErr;
                }
                List<?> checkExiste = getFolder_V2(typedossier, code);

                if (checkExiste.size() != 0) {
                    dossier.put("erreur","Dossier existant dans la base archive");
                    listErr.add(dossier);
                }

                if (checkExiste.size() == 0 && dossierPolice.size()>0) {

                    dossier.put("message","Dossier est ajout avec succes");
                    listErr.add(dossier);

                }



                if (checkExiste.size() == 0 && dossierPolice.size()>0) {
                    PoliceEntity police = new PoliceEntity();
                    police.setNPolice(dossierPolice.get(0).getNPolice());
                    police.setCodebranche(dossierPolice.get(0).getCodeBranche());
                    police.setCodeAgence(Integer.parseInt(dossierPolice.get(0).getCodeAgence()));
                    police.setCodeClient(dossierPolice.get(0).getCodeClient());
                    police.setCodeSousBrache(dossierPolice.get(0).getCodeSousBranche());
                    police.setLibBranche(dossierPolice.get(0).getLibBranche());
                    police.setLibSousBranche(dossierPolice.get(0).getLibSousBranche());
                    police.setNomClient(dossierPolice.get(0).getNomClient());
                    police.setUtilisateur(nameArchiviste);
                    police.setDateAjout(currentDate);
                    policeDao.addContrat(police);
                    auxPol.add(police);
                    //viewPoliceProassurDao.getPolice(code).clear();
                    //outFolder.add(police);
                    //return aux;

                }
                //return null;
            }

            if (typedossier.equals("Sinistre")) {

                List<ViewSinisterProassurEntity> dossierSinister = viewSinistreProassurDao.getSinistre(dossier.get("dossier"));


                if(dossierSinister.size() == 0){
                    dossier.put("erreur","Dossier inexistant dans PROASSUR");
                    listErr.add(dossier);
                    //listErr.add(dossier);
                    //return listErr;
                }

                List<?> checkExiste = getFolder_V2(typedossier, code);

                if (checkExiste.size() != 0) {
                    dossier.put("erreur","Dossier existant dans la base archive");
                    listErr.add(dossier);
                }

                if (checkExiste.size() == 0 && dossierSinister.size()>0) {

                    dossier.put("message","Dossier est ajout avec succes");
                    listErr.add(dossier);

                }

                if (checkExiste.size() == 0 && dossierSinister.size()>0) {
                    SinistreEntity sinistre = new SinistreEntity();
                    sinistre.setNPolice(dossierSinister.get(0).getNPolice());
                    sinistre.setNSinistre(dossierSinister.get(0).getNSinister());
                    sinistre.setCodebranche(dossierSinister.get(0).getCodeBranche());
                    sinistre.setCodeAgence(Integer.parseInt(dossierSinister.get(0).getCodeAgence()));
                    sinistre.setCodeClient(dossierSinister.get(0).getCodeClient());
                    sinistre.setCodeSousBrache(dossierSinister.get(0).getCodeSousBranche());
                    sinistre.setNomClient(dossierSinister.get(0).getNomClient());
                    sinistre.setImmatriculation(dossierSinister.get(0).getImmatriculation());
                    sinistre.setTypeDossier(dossierSinister.get(0).getTypeDossier());
                    sinistre.setLibBranche(dossierSinister.get(0).getLibBranche());
                    sinistre.setLibSousBranche(dossierSinister.get(0).getLibSousBranche());
                    sinistre.setEtatDossier(dossierSinister.get(0).getEtatDossier());
                    sinistre.setGestionnaire(dossierSinister.get(0).getGestionnaire());
                    sinistre.setUtilisateur(nameArchiviste);
                    sinistre.setDate_Ouverture(dossierSinister.get(0).getDateOuverture());
                    sinistre.setDate_Survenance(dossierSinister.get(0).getDateSurvenance());
                    sinistre.setDateAjout(currentDate);
                    sinistreDao.addSinistre(sinistre);
                    auxSin.add(sinistre);
                    //return aux;

                }
                //return null;
            }
            if (typedossier.equals("Cxp")) {
                List<ViewCxpProassurEntity> dossierCxp = viewCxpProassurDao.getcxp(dossier.get("dossier"));


                if(dossierCxp.size() == 0){
                    dossier.put("erreur","Dossier inexistant dans PROASSUR");
                    listErr.add(dossier);
                    //listErr.add(dossier);
                    //return listErr;
                }
                List<?> checkExiste = getFolder_V2(typedossier, code);

                if (checkExiste != null) {
                    dossier.put("erreur","Dossier existant dans la base archive");
                    listErr.add(dossier);
                }

                if (checkExiste==null && dossierCxp.size()>0) {

                    dossier.put("message","Dossier est ajout avec succes");
                    listErr.add(dossier);

                }

                if (checkExiste == null && dossierCxp.size()>0) {
                    CxpEntity cxp = new CxpEntity();
                    cxp.setNCxp(dossierCxp.get(0).getNCxp());
                    cxp.setCodeAgence(dossierCxp.get(0).getCodeAgence());
                    cxp.setNomClienCxp(dossierCxp.get(0).getNom());
                    cxp.setVehicule(dossierCxp.get(0).getVehicule());
                    cxp.setAdresseClientCxp((dossierCxp.get(0).getAdresse()));
                    cxp.setObservationCxp(dossierCxp.get(0).getObservation());
                    cxp.setVilleClientcxp(dossierCxp.get(0).getVille());
                    cxp.setUtilisateur(nameArchiviste);
                    cxp.setDateCxp(dossierCxp.get(0).getDateCxp());
                    cxp.setDateAjout(currentDate);
                    cxpDao.addDossier(cxp);
                    auxCxp.add(cxp);
                    //viewCxpProassurDao=null;
                    //return aux;

                }
                //return null;
            }
        }
        if (typedossier.equals("Police")) {
            outList.add(listErr);
            outList.add(auxPol);
            return outList;
        }
        else if (typedossier.equals("Sinistre")) {
            outList.add(listErr);
            outList.add(auxSin);
            return outList;
            //return auxSin;
        }
        else if (typedossier.equals("Cxp")) {
            outList.add(listErr);
            outList.add(auxCxp);
            return outList;
        }
        else{
            return null;
        }

       // return null;
    }
    @GetMapping(path = "Dossier/recherche/{typedossier}/{dossier}")
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

    @GetMapping(path = "Dossier/RechercheD/{typeDossier}/{n_Dossier}")
    public List<?> getFolder_v2(@PathVariable String typeDossier,@PathVariable String n_Dossier){
        List<?> resultList= new ArrayList<>();
        List<Object> police=new ArrayList<>();
        List<Object> sinistre=new ArrayList<>();
        List<Object> cxp =new ArrayList<>();
        if(typeDossier.equals("Police")){
             police.add(policeDao.findFolder(n_Dossier));
             police.add(consultationDao.getConsultationsByCode(n_Dossier));
             return police;

        }
        else  if(typeDossier.equals("Sinistre")){
            sinistre.add(sinistreDao.findSinistre(n_Dossier));
            sinistre.add(consultationDao.getConsultationsByCode(n_Dossier));
            sinistre.add((viewSinistreProassurDao.getGestionnaire(n_Dossier)));
            sinistre.add((viewSinistreProassurDao.getEtatDossierProassur(n_Dossier)));
            sinistre.add(viewSinistreProassurDao.getTypeDossierProassur(n_Dossier));
            return sinistre;

        }else  if(typeDossier.equals("Cxp")){
            cxp.add(cxpDao.getFolderCxp(n_Dossier));
            cxp.add(consultationDao.getConsultationsByCode(n_Dossier));
            return cxp;

        }
        return null;
    }

    @GetMapping(path = "history/Dossier/{codeDossier}")
    public List<ConsultationHistoriqueEntity> getHistorys(@PathVariable String codeDossier){
        return historyConsultationDao.gethistorys(codeDossier);
    }
/**
    @GetMapping("api/export/excel/police")
    public void exportPoliceToExcel(HttpServletResponse response) throws IOException {
        List<PoliceEntity> dataList = policeDao.getallPolice();  // Fetch data from DB
        excelExportService.exportPoliceToExcel(response, dataList);
    }

    @GetMapping("api/export/excel/sinistre")
    public void exportSinistreToExcel(HttpServletResponse response) throws IOException {
        List<SinistreEntity> dataList = sinistreDao.getAllSinistre();  // Fetch data from DB
        excelExportService.exportSinsterToExcel(response, dataList);
    }

    @GetMapping("api/export/excel/cxp")
    public void exportCxpToExcel(HttpServletResponse response) throws IOException {
        List<CxpEntity> dataList = cxpDao.getAllCxp();  // Fetch data from DB
        excelExportService.exportCxpToExcel(response, dataList);
    }

    @GetMapping("api/export/excel/consultation/type/{typedossier}")
    public void exportConsultationbyTypeToExcel(@PathVariable String typedossier, HttpServletResponse response) throws IOException {
        List<ConsultationEntity> dataList = consultationDao.getAllConsultationFiltreByTypeDossier(typedossier);  // Fetch data from DB
        excelExportService.exportConsultationToExcel(response, dataList);
    }

    @GetMapping("api/export/excel/consultation/date/{dateConsultation}")
    public void exportConsultationByDateToExcel(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateConsultation, HttpServletResponse response) throws IOException {
        String dateString = dateConsultation.toString();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss O yyyy", Locale.ENGLISH);
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, formatter);

        // Extract the LocalDate
        LocalDate localDate = zonedDateTime.toLocalDate();
        List<ConsultationEntity> dataList = consultationDao.getAllConsultationFiltreByDate(localDate);  // Fetch data from DB
        excelExportService.exportConsultationToExcel(response, dataList);
    }

    @GetMapping("api/export/excel/consultation/type/{typedossier}/date/{dateConsultation}")
    public void exportConsultationByTypeAndToExcel(@PathVariable String typedossier,  @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateConsultation, HttpServletResponse response) throws IOException {
        String dateString = dateConsultation.toString();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss O yyyy", Locale.ENGLISH);
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, formatter);

        // Extract the LocalDate
        LocalDate localDate = zonedDateTime.toLocalDate();
        List<ConsultationEntity> dataList = consultationDao.getAllConsultationBydateAndType(typedossier,localDate);  // Fetch data from DB
        excelExportService.exportConsultationToExcel(response, dataList);
    }


    @GetMapping("api/export/excel/consultation/all")
    public void exportAllConsultationExcel(HttpServletResponse response) throws IOException {
        List<ConsultationEntity> dataList = consultationDao.getAllConsultation();  // Fetch data from DB
        excelExportService.exportConsultationToExcel(response, dataList);
    }
 */
@GetMapping("api/export/excel/dossier/{typeDossier}/{dateConsultation}")
public void exportPoliceToExcel(@PathVariable String typeDossier,@PathVariable String dateConsultation, HttpServletResponse response) throws IOException {
    LocalDate localDate;
    if (typeDossier.equals("police")) {
        if ((dateConsultation.equals("undefined")) || (dateConsultation.equals(""))) {
            List<PoliceEntity> dataList = policeDao.getallPolice();  // Fetch data from DB
            excelExportService.exportPoliceToExcel(response, dataList);
        } else if (dateConsultation != null) {
            if (dateConsultation.toString() != "") {

                String dateString = dateConsultation;

                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                //ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, formatter);
                LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);

                // Extract the LocalDate
               // localDate = zonedDateTime.toLocalDate();
                localDate=date;
                List<PoliceEntity> dataList = policeDao.getPoliceByDate(localDate);  // Fetch data from DB
                excelExportService.exportPoliceToExcel(response, dataList);
            }
        }
    }else if(typeDossier.equals("sinistre")){
        if((dateConsultation.equals("undefined")) || (dateConsultation.equals(""))) {
            List<SinistreEntity> dataList = sinistreDao.getAllSinistre();  // Fetch data from DB
            excelExportService.exportSinsterToExcel(response, dataList);
        } else if(dateConsultation!=null ){
            if(dateConsultation.toString()!="") {

                String dateString = dateConsultation;

                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                //ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, formatter);
                LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);

                // Extract the LocalDate
                // localDate = zonedDateTime.toLocalDate();
                localDate=date;
                List<SinistreEntity> dataList = sinistreDao.getSinistreByDate(localDate);  // Fetch data from DB
                excelExportService.exportSinsterToExcel(response, dataList);
            }
        }
    }else if(typeDossier.equals("cxp")){
        if((dateConsultation.equals("undefined")) || (dateConsultation.equals(""))) {
            List<CxpEntity> dataList = cxpDao.getAllCxp();  // Fetch data from DB
            excelExportService.exportCxpToExcel(response, dataList);

        }else if(dateConsultation!=null ){
            if  (dateConsultation.toString()!="") {

                String dateString = dateConsultation;

                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                //ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, formatter);
                LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);

                // Extract the LocalDate
                // localDate = zonedDateTime.toLocalDate();
                localDate=date;
                List<CxpEntity> dataList = cxpDao.getCxpByDate(localDate);  // Fetch data from DB
                excelExportService.exportCxpToExcel(response, dataList);
            }
        }
    }
}

    @GetMapping("api/export/excel/consultation/{typedossier}/{dateConsultation}")
    public void exportConsultationToExcel(@PathVariable String typedossier,  @PathVariable String dateConsultation, HttpServletResponse response) throws IOException {
        LocalDate localDate;

        if((dateConsultation.equals("undefined")) || (dateConsultation.equals(""))) {
            List<ConsultationEntity> dataList = consultationDao.getAllConsultationFiltreByTypeDossier(typedossier);  // Fetch data from DB
            excelExportService.exportConsultationToExcel(response, dataList);
        }else {

            String dateString = dateConsultation;

            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, formatter);
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);

            // Extract the LocalDate
            // localDate = zonedDateTime.toLocalDate();
            localDate = date;
            List<ConsultationEntity> dataList = consultationDao.getAllConsultationBydateAndType(typedossier, localDate);  // Fetch data from DB
            excelExportService.exportConsultationToExcel(response, dataList);
        }
    }
}




