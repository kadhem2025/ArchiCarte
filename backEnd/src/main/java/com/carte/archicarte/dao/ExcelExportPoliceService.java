package com.carte.archicarte.dao;

import com.carte.archicarte.entity.ConsultationEntity;
import com.carte.archicarte.entity.CxpEntity;
import com.carte.archicarte.entity.PoliceEntity;
import com.carte.archicarte.entity.SinistreEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportPoliceService {

    public void exportPoliceToExcel(HttpServletResponse response, List<PoliceEntity> dataList) throws IOException {
        //XSSFWorkbook workbook = new XSSFWorkbook();
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("Data Export Police");

        // Create header row
        Row header = sheet.createRow(0);
        //header.createCell(0).setCellValue("ID");
        header.createCell(0).setCellValue("Numéro Police");
        header.createCell(1).setCellValue("Code Agence");
        header.createCell(2).setCellValue("Code Branche");
        header.createCell(3).setCellValue("Libelle Branche");
        header.createCell(4).setCellValue("Code Sous Branche");
        header.createCell(5).setCellValue("Libelle Sous Branche");
        header.createCell(6).setCellValue("Code Client");
        header.createCell(7).setCellValue("Nom Client");
        header.createCell(8).setCellValue("Ajouter Par");
        header.createCell(9).setCellValue("Date Ajout");
        // Add more columns as needed

        // Fill data rows
        int rowCount = 1;
        for (PoliceEntity data : dataList) {
            Row row = sheet.createRow(rowCount++);
            //row.createCell(0).setCellValue(data.getId());
            row.createCell(0).setCellValue(data.getNPolice());
            row.createCell(1).setCellValue(data.getCodeAgence());
            row.createCell(2).setCellValue(data.getCodebranche());
            row.createCell(3).setCellValue(data.getLibBranche());
            row.createCell(4).setCellValue(data.getCodeSousBrache());
            row.createCell(5).setCellValue(data.getLibSousBranche());
            row.createCell(6).setCellValue(data.getCodeClient());
            row.createCell(7).setCellValue(data.getNomClient());
            row.createCell(8).setCellValue(data.getUtilisateur());
            row.createCell(9).setCellValue(data.getDateAjout().toString());
            // Add more fields as needed

            /*Cell cellDateAjout = row.getCell(9);
            CellStyle cellStyle = workbook.createCellStyle();
            short format = createHelper.createDataFormat().getFormat("dd/mm/yyyy");
            cellStyle.setDataFormat(format);
            cellDateAjout.setCellStyle(cellStyle);
            //cellDateAjout.setCellType(CellType.DATE);

             */
        }

        // Write the output to the response
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=data_export.xlsx");
        workbook.write(response.getOutputStream());
        workbook.close();
    }




    public void exportSinsterToExcel(HttpServletResponse response, List<SinistreEntity> dataList) throws IOException {
        //XSSFWorkbook workbook = new XSSFWorkbook();
        SXSSFWorkbook workbook = new SXSSFWorkbook();

        CreationHelper createHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("Data Export Sinistre");

        // Create header row
        Row header = sheet.createRow(0);
        //header.createCell(0).setCellValue("ID");
        /*header.createCell(0).setCellValue("Numero Sinistre");
        header.createCell(1).setCellValue("Code Agence");
        header.createCell(2).setCellValue("Code Branche");
        header.createCell(3).setCellValue("Libelle Branche");
        header.createCell(4).setCellValue("Code Sous Branche");
        header.createCell(5).setCellValue("Libelle Sous Branche");
        header.createCell(6).setCellValue("Code Client");
        header.createCell(7).setCellValue("Nom Client");
        header.createCell(8).setCellValue("Ajouter Par");
        header.createCell(9).setCellValue("Date Ajout");
        header.createCell(10).setCellValue("Numéro Police");
        header.createCell(11).setCellValue("Type Dossier");
        header.createCell(12).setCellValue("Etat Dossier");
        header.createCell(13).setCellValue("Gestionnaire");
        header.createCell(14).setCellValue("Immatriculation");
        header.createCell(15).setCellValue("Date Servenance");
        header.createCell(16).setCellValue("Date Overture");*/

        header.createCell(0).setCellValue("Numero Sinistre");
        header.createCell(1).setCellValue("Code Branche");
        header.createCell(2).setCellValue("Code Sous Branche");
        header.createCell(3).setCellValue("Ajouter Par");
        header.createCell(4).setCellValue("Date Ajout");
        header.createCell(5).setCellValue("Type Dossier");
        header.createCell(6).setCellValue("Etat Dossier");
        header.createCell(7).setCellValue("Gestionnaire");
        header.createCell(8).setCellValue("Date Survenance");
        header.createCell(9).setCellValue("Date Overture");
        // Add more columns as needed

        // Fill data rows
        int rowCount = 1;
        for (SinistreEntity data : dataList) {
            Row row = sheet.createRow(rowCount++);
            //row.createCell(0).setCellValue(data.getId());
           /* row.createCell(0).setCellValue(data.getNSinistre());
            row.createCell(1).setCellValue(data.getCodeAgence());
            row.createCell(2).setCellValue(data.getCodebranche());
            row.createCell(3).setCellValue(data.getLibBranche());
            row.createCell(4).setCellValue(data.getCodeSousBrache());
            row.createCell(5).setCellValue(data.getLibSousBranche());
            row.createCell(6).setCellValue(data.getCodeClient());
            row.createCell(7).setCellValue(data.getNomClient());
            row.createCell(8).setCellValue(data.getUtilisateur());
            row.createCell(9).setCellValue(data.getDateAjout().toString());
            row.createCell(10).setCellValue(data.getNPolice());
            row.createCell(11).setCellValue(data.getTypeDossier());
            row.createCell(12).setCellValue(data.getEtatDossier());
            row.createCell(13).setCellValue(data.getGestionnaire());
            row.createCell(14).setCellValue(data.getImmatriculation());
            row.createCell(15).setCellValue(data.getDate_Survenance());
            row.createCell(16).setCellValue(data.getDate_Ouverture());

            */
            row.createCell(0).setCellValue(data.getNSinistre());
            row.createCell(1).setCellValue(data.getCodebranche());
            row.createCell(2).setCellValue(data.getCodeSousBrache());
            row.createCell(3).setCellValue(data.getUtilisateur());
            row.createCell(4).setCellValue(data.getDateAjout().toString());
            row.createCell(5).setCellValue(data.getTypeDossier());
            row.createCell(6).setCellValue(data.getEtatDossier());
            row.createCell(7).setCellValue(data.getGestionnaire());
            row.createCell(8).setCellValue(data.getDate_Survenance());
            row.createCell(9).setCellValue(data.getDate_Ouverture());
            // Add more fields as needed

            /*Cell cellDateAjout = row.getCell(9);
            Cell cellDateSurvenance = row.getCell(15);
            Cell cellDateOverture = row.getCell(16);
            CellStyle cellStyle = workbook.createCellStyle();
            short format = createHelper.createDataFormat().getFormat("dd/mm/yyyy");
            cellStyle.setDataFormat(format);
            cellDateAjout.setCellStyle(cellStyle);
            cellDateSurvenance.setCellStyle(cellStyle);
            cellDateOverture.setCellStyle(cellStyle);

             */
            //cellDateAjout.setCellType(CellType.DATE);
        }

        // Write the output to the response
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=data_export.xlsx");
        workbook.write(response.getOutputStream());
        workbook.close();
    }




    public void exportCxpToExcel(HttpServletResponse response, List<CxpEntity> dataList) throws IOException {
        //XSSFWorkbook workbook = new XSSFWorkbook();
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("Data Export Cxp");

        // Create header row
        Row header = sheet.createRow(0);
        //header.createCell(0).setCellValue("ID");
        header.createCell(0).setCellValue("Numéro Dossier Cxp");
        header.createCell(1).setCellValue("Code Agence");
        header.createCell(2).setCellValue("Date Cxp");
        header.createCell(3).setCellValue("Vehicule");
        header.createCell(4).setCellValue("Adresse Client");
        header.createCell(5).setCellValue("Ville Client");
        header.createCell(6).setCellValue("Observation");
        header.createCell(7).setCellValue("Nom Client");
        header.createCell(8).setCellValue("Ajouter Par");
        header.createCell(9).setCellValue("Date Ajout");
        // Add more columns as needed

        // Fill data rows
        int rowCount = 1;
        for (CxpEntity data : dataList) {
            Row row = sheet.createRow(rowCount++);
            //row.createCell(0).setCellValue(data.getId());
            row.createCell(0).setCellValue(data.getNCxp());
            row.createCell(1).setCellValue(data.getCodeAgence());
            row.createCell(2).setCellValue(data.getDateCxp());
            row.createCell(3).setCellValue(data.getVehicule());
            row.createCell(4).setCellValue(data.getAdresseClientCxp());
            row.createCell(5).setCellValue(data.getVilleClientcxp());
            row.createCell(6).setCellValue(data.getObservationCxp());
            row.createCell(7).setCellValue(data.getNomClienCxp());
            row.createCell(8).setCellValue(data.getUtilisateur());
            row.createCell(9).setCellValue(data.getDateAjout().toString());
            // Add more fields as needed

            /*Cell cellDateAjout = row.getCell(9);
            Cell cellDateCxp = row.getCell(2);
            CellStyle cellStyle = workbook.createCellStyle();
            short format = createHelper.createDataFormat().getFormat("dd/mm/yyyy");
            cellStyle.setDataFormat(format);
            cellDateAjout.setCellStyle(cellStyle);
            cellDateCxp.setCellStyle(cellStyle);

             */
            //cellDateAjout.setCellType(CellType.DATE);
        }

        // Write the output to the response
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=data_export.xlsx");
        workbook.write(response.getOutputStream());
        workbook.close();
    }



    public void exportConsultationToExcel(HttpServletResponse response, List<ConsultationEntity> dataList) throws IOException {
        //XSSFWorkbook workbook = new XSSFWorkbook();
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("Data Export");

        // Create header row
        Row header = sheet.createRow(0);
        //header.createCell(0).setCellValue("ID");
        header.createCell(0).setCellValue("Numéro Dossier");
        header.createCell(1).setCellValue("Consulté par");
        header.createCell(2).setCellValue("Date Consultation");
        header.createCell(3).setCellValue("Date Retour Estimé");
        header.createCell(4).setCellValue("Date Insertion");
        header.createCell(5).setCellValue("Archiviste Consultation");
        header.createCell(6).setCellValue("Archiviste Ajout");
        header.createCell(7).setCellValue("Type Dossier");
        header.createCell(8).setCellValue("Archiviste Retour");
        header.createCell(9).setCellValue("Date Retour Réel");

        // Add more columns as needed

        // Fill data rows
        int rowCount = 1;
        for (ConsultationEntity data : dataList) {
            Row row = sheet.createRow(rowCount++);
            //row.createCell(0).setCellValue(data.getId());
            row.createCell(0).setCellValue(data.getNaffaire());
            row.createCell(1).setCellValue(data.getConsulterPar());
            row.createCell(2).setCellValue(data.getDateConsultation().toString());
            row.createCell(3).setCellValue(data.getDateRetour());
            row.createCell(4).setCellValue(data.getDateInsertion());
            row.createCell(5).setCellValue(data.getArchivisteCons());
            row.createCell(6).setCellValue(data.getArchivisteAjout());
            row.createCell(7).setCellValue(data.getTypeAffaire());
            row.createCell(8).setCellValue(data.getArchivisteRetour());
            row.createCell(9).setCellValue(data.getDateRetourReel());
            // Add more fields as needed

            /*Cell cellDateConsultation = row.getCell(2);
            Cell cellDateRetour = row.getCell(3);
            Cell cellDateInsertion = row.getCell(4);
            CellStyle cellStyle = workbook.createCellStyle();
            short format = createHelper.createDataFormat().getFormat("dd/mm/yyyy");
            cellStyle.setDataFormat(format);
            cellDateConsultation.setCellStyle(cellStyle);
            cellDateRetour.setCellStyle(cellStyle);
            cellDateInsertion.setCellStyle(cellStyle);

             */
            //cellDateAjout.setCellType(CellType.DATE);
        }

        // Write the output to the response
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=data_export.xlsx");
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}

