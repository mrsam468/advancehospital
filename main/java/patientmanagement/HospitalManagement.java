package patientmanagement;

import exception.InvalidPatientIdException;
import exception.InvalidPatientNameException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

import static org.apache.poi.ss.usermodel.CellType.*;

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

    public List<PatientsDetails> viewAllPatients() throws IOException {
        List<PatientsDetails> patients = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(file_path)) {
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                String firstName = row.getCell(0).getStringCellValue();
                String lastName = row.getCell(1).getStringCellValue();
                String otherName = row.getCell(2).getStringCellValue();
                String gender = row.getCell(3).getStringCellValue();
                int ID = (int) row.getCell(4).getNumericCellValue();
                int age = (int) row.getCell(5).getNumericCellValue();
                String doctorAssigned = row.getCell(6).getStringCellValue();
                String illness = row.getCell(7).getStringCellValue();
                double outstandingBill = row.getCell(8).getNumericCellValue();

                patients.add(new PatientsDetails(firstName, lastName, otherName, Gender.valueOf(gender), ID, age, doctorAssigned, illness, outstandingBill));
            }

        }
        return patients;
    }

    public PatientsDetails searchPatientWithId(int patientId) throws IOException {
        for (PatientsDetails patientWithId : viewAllPatients()) {
            if (patientWithId.getPatientId() == patientId) {
                return patientWithId;
            }
        }
        throw new InvalidPatientIdException("patient with id does not exist in this hospital");
    }

    public PatientsDetails searchPatientWithFullName(String fullName) throws IOException {
        for (PatientsDetails patientWithName : viewAllPatients()) {
            if (patientWithName.getFullName().equals(fullName)) {
                return patientWithName;
            }
        }
        throw new InvalidPatientNameException("patient with this name does not exist in this hospital");
    }

    public void updatePatientIllness(int patientId,String illness) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file_path);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet){
            if (row.getRowNum()==0) continue;
            int ID = (int) row.getCell(4).getNumericCellValue();
            if (ID==patientId){
                Cell cell = row.getCell(7);
                cell.setCellValue(illness);
            }
            fileInputStream.close();
            FileOutputStream fileOutputStream = new FileOutputStream(file_path);
            workbook.write(fileOutputStream);
            fileOutputStream.close();

        }
        System.out.println("excel file have benn updated successfully");
        workbook.close();

    }

    public void updateAssignedDoctor(int patientId,String doctorName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file_path);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet){
            if (row.getRowNum()==0) continue;
            int ID = (int) row.getCell(4).getNumericCellValue();
            if (ID==patientId){
                Cell cell = row.getCell(6);
                cell.setCellValue(doctorName);
            }
            fileInputStream.close();
            FileOutputStream fileOutputStream = new FileOutputStream(file_path);
            workbook.write(fileOutputStream);
            fileOutputStream.close();

        }
        System.out.println("excel file have benn updated successfully");
        workbook.close();
    }

    public void updateOutstandingBill(int patientId,double outstandingBill) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file_path);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet){
            if (row.getRowNum()==0) continue;
            int ID = (int) row.getCell(4).getNumericCellValue();
            if (ID==patientId){
                Cell cell = row.getCell(8);
                cell.setCellValue(outstandingBill);
            }
            fileInputStream.close();
            FileOutputStream fileOutputStream = new FileOutputStream(file_path);
            workbook.write(fileOutputStream);
            fileOutputStream.close();

        }
        System.out.println("excel file have benn updated successfully");
        workbook.close();
    }
}