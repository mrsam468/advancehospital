package patientmanagement;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;

public class HospitalManagement {
    private final String file_path = "C:\\Users\\USER-PC\\IdeaProjects\\advancehospitalmanagement\\src\\main\\java\\hospitaldatabase\\patientdetail.xlsx";

    public void storageHeader() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file_path);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("patientDetails");
        String[] header = {"FirstName", "LastName", "Othername", "Gender", "ID", "Age", "AssignedDoctor", "illness", "Outstandingbill"};
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < header.length; i++) {
            headerRow.createCell(i).setCellValue(header[i]);
        }
        workbook.write(fileOutputStream);
    }

    public void registerPatient(PatientsDetails patientsDetails) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(file_path);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            int lastRow = sheet.getLastRowNum();
            Row newRow = sheet.createRow(lastRow + 1);
            newRow.createCell(0).setCellValue(patientsDetails.getFirstName());
            newRow.createCell(1).setCellValue(patientsDetails.getLastName());
            newRow.createCell(2).setCellValue(patientsDetails.getOtherName());
            newRow.createCell(3).setCellValue(String.valueOf(patientsDetails.getGender()));
            newRow.createCell(4).setCellValue(patientsDetails.getPatientId());
            newRow.createCell(5).setCellValue(patientsDetails.getAge());
            newRow.createCell(6).setCellValue(patientsDetails.getAssignedDoctor());
            newRow.createCell(7).setCellValue(patientsDetails.getLllnessName());
            newRow.createCell(8).setCellValue(patientsDetails.getOutstandingBill());

            try (FileOutputStream fileOutputStream = new FileOutputStream(file_path)) {
                workbook.write(fileOutputStream);
                System.out.println("patient details saved");
            }
        }
    }

    public void viewAllPatients() throws IOException {
        List<String> patients = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(file_path)) {
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            Iterator<Row> rowIterator = sheet.iterator();

            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                System.out.println();
                if (row != null) {
                    String firstName = row.getCell(0).getStringCellValue();
                    String lastName = row.getCell(1).getStringCellValue();
                    String otherName = row.getCell(2).getStringCellValue();
                    String gender = row.getCell(3).getStringCellValue();
                    int ID = (int) row.getCell(4).getNumericCellValue();
                    int age = (int) row.getCell(5).getNumericCellValue();
                    String doctorAssigned = row.getCell(6).getStringCellValue();
                    String illness = row.getCell(7).getStringCellValue();
                    Double outstandingBill = row.getCell(8).getNumericCellValue();

                }
            }

        }
    }
}