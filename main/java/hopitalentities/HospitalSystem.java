package hopitalentities;

import exception.InvalidPatientIdException;
import exception.InvalidPatientNameException;
import exception.InvalidUserIdException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class HospitalSystem {
    private final String activePatientStorage = "C:\\Users\\USER-PC\\IdeaProjects\\advancehospitalmanagement\\src\\main\\java\\hospitaldatabase\\activepatient.xlsx";
    private final String dischargedPatientStorage = "C:\\Users\\USER-PC\\IdeaProjects\\advancehospitalmanagement\\src\\main\\java\\hospitaldatabase\\dischargedpatient.xlsx";

    public void registerPatient(Patient patient) throws IOException {
        HashSet<Integer> idCheck = new HashSet<>();
        try (FileInputStream fileInputStream = new FileInputStream(activePatientStorage)) {
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                int ID = (int) row.getCell(4).getNumericCellValue();
                idCheck.add(ID);
            }
            if (idCheck.contains(patient.getID())) try {
                throw new InvalidUserIdException("patient with ID already exist");
            } catch (InvalidUserIdException e) {
                System.out.println(e.getMessage());
            }
            else {
                try (FileInputStream patientInputStream = new FileInputStream(activePatientStorage);
                     Workbook book = new XSSFWorkbook(patientInputStream)) {
                    Sheet booksheet = book.getSheetAt(0);
                    int lastRow = booksheet.getLastRowNum();
                    Row newRow = booksheet.createRow(lastRow + 1);
                    newRow.createCell(0).setCellValue(patient.getFirstName());
                    newRow.createCell(1).setCellValue(patient.getLastName());
                    newRow.createCell(2).setCellValue(patient.getOtherName());
                    newRow.createCell(3).setCellValue(String.valueOf(patient.getGender()));
                    newRow.createCell(4).setCellValue(patient.getID());
                    newRow.createCell(5).setCellValue(patient.getAge());
                    newRow.createCell(6).setCellValue(patient.getAssignedDoctor());
                    newRow.createCell(7).setCellValue(patient.getIllnessName());
                    newRow.createCell(8).setCellValue(patient.getOutstandingBill());
                    newRow.createCell(9).setCellValue(patient.getWardAssigned());

                    try (FileOutputStream fileOutputStream = new FileOutputStream(activePatientStorage)) {
                        book.write(fileOutputStream);
                        System.out.println("patient details saved");
                    }
                }
            }
        }

    }

    private Map<Integer, Patient> allPatientsWithIdAsKey() throws IOException {
        Map<Integer, Patient> patients = new HashMap<>();

        try (FileInputStream fileInputStream = new FileInputStream(activePatientStorage)) {
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
                String wardAssigned = row.getCell(9).getStringCellValue();

                patients.put(ID, new Patient(firstName, lastName, otherName, Gender.valueOf(gender), ID, age, doctorAssigned, illness, outstandingBill, wardAssigned));
            }

        }
        return patients;
    }

    public Patient searchPatientWithId(int patientId) throws IOException {
        if (!allPatientsWithIdAsKey().containsKey(patientId)) {
            throw new InvalidPatientIdException("this patient do not exist");
        } else {
            return allPatientsWithIdAsKey().get(patientId);
        }
    }

    public List<Patient> searchPatientWithName(String firstName, String lastName, String otherName) throws IOException {
        String fullName = firstName + " " + lastName + " " + otherName;
        List<Patient> allPatientsWithName = new ArrayList<>();
        allPatientsWithIdAsKey().forEach((k, v) -> {
            if (v.getFUllName().equals(fullName)) {
                allPatientsWithName.add(v);
            }
        });
        return allPatientsWithName;
    }

    public void updatePatientIllness(int patientId, String illness) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(activePatientStorage);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;
            int ID = (int) row.getCell(4).getNumericCellValue();
            if (ID == patientId) {
                Cell cell = row.getCell(7);
                cell.setCellValue(illness);
            }
            fileInputStream.close();
            FileOutputStream fileOutputStream = new FileOutputStream(activePatientStorage);
            workbook.write(fileOutputStream);
            fileOutputStream.close();

        }
        System.out.println("excel file have benn updated successfully");
        workbook.close();

    }

    public void updateAssignedDoctor(int patientId, String doctorName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(activePatientStorage);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;
            int ID = (int) row.getCell(4).getNumericCellValue();
            if (ID == patientId) {
                Cell cell = row.getCell(6);
                cell.setCellValue(doctorName);
            }
            fileInputStream.close();
            FileOutputStream fileOutputStream = new FileOutputStream(activePatientStorage);
            workbook.write(fileOutputStream);
            fileOutputStream.close();

        }
        System.out.println("excel file have benn updated successfully");
        workbook.close();
    }

    public void updateOutstandingBill(int patientId, double outstandingBill) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(activePatientStorage);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;
            int ID = (int) row.getCell(4).getNumericCellValue();
            if (ID == patientId) {
                Cell cell = row.getCell(8);
                cell.setCellValue(outstandingBill);
            }
            fileInputStream.close();
            FileOutputStream fileOutputStream = new FileOutputStream(activePatientStorage);
            workbook.write(fileOutputStream);
            fileOutputStream.close();

        }
        System.out.println("excel file have benn updated successfully");
        workbook.close();
    }

    public List<Patient> patientsSortedByName() throws IOException {
        List<Patient> patients = new ArrayList<>();
        allPatientsWithIdAsKey().forEach((k, v) -> patients.add(v));
        return patients.stream().sorted((p1, p2) -> p1.getFUllName().compareTo(p2.getFUllName()))
                .collect(Collectors.toList());
    }

    public List<Patient> patientsSortedByAge() throws IOException {
        List<Patient> patients = new ArrayList<>();
        allPatientsWithIdAsKey().forEach((k, v) -> patients.add(v));
        return patients.stream().sorted((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge())).toList();
    }

    public List<Patient> patientsSortedByOutstandingBill() throws IOException {
        List<Patient> patients = new ArrayList<>();
        allPatientsWithIdAsKey().forEach((k, v) -> patients.add(v));
        return patients.stream().sorted((p1, p2) -> Double.compare(p1.getOutstandingBill(), p2.getOutstandingBill())).toList();
    }

    public List<Patient> patientsAssignedToDoctor(String doctorsFullname) throws IOException {
        List<Patient> patientsAssingedToDoctor = new ArrayList<>();
        allPatientsWithIdAsKey().forEach((k, v) -> {
            if (v.getAssignedDoctor().equals(doctorsFullname)) {
                patientsAssingedToDoctor.add(v);
            }
        });
        return patientsAssingedToDoctor;
    }

    public List<Patient> patientsSufferingFromIllness(String illnessName) throws IOException {
        List<Patient> patientsSufferingFromIllness = new ArrayList<>();
        allPatientsWithIdAsKey().forEach((k, v) -> {
            if (v.getIllnessName().equals(illnessName)) {
                patientsSufferingFromIllness.add(v);
            }
        });
        return patientsSufferingFromIllness;
    }

    public List<Patient> patientsWithOutstandingBill(double outstandingBill) throws IOException {
        List<Patient> patientsAssingedToDoctor = new ArrayList<>();
        allPatientsWithIdAsKey().forEach((k, v) -> {
            if (v.getOutstandingBill() > outstandingBill) {
                patientsAssingedToDoctor.add(v);
            }
        });
        return patientsAssingedToDoctor;
    }

    public void dischargePatient(int patientId) throws IOException {
        FileInputStream dischargedPatientInputStream = new FileInputStream(dischargedPatientStorage);
        FileInputStream activePatientInputStream = new FileInputStream(activePatientStorage);
        XSSFWorkbook activePatientWorkbook = new XSSFWorkbook(activePatientInputStream);
        XSSFWorkbook dischargedPatientWorkbook = new XSSFWorkbook(dischargedPatientInputStream);
        XSSFSheet dischargedPatientSheet = dischargedPatientWorkbook.getSheetAt(0);
        XSSFSheet activePatientSheet = activePatientWorkbook.getSheetAt(0);
        Row dischargedPatientRow = dischargedPatientSheet.createRow(dischargedPatientSheet.getLastRowNum() + 1);

        for (int i = activePatientSheet.getLastRowNum(); i >= 1; i--) {
            Row activePatientRow = activePatientSheet.getRow(i);
            if (activePatientRow == null) continue;
            int ID = (int) activePatientRow.getCell(4).getNumericCellValue();
            if (ID == patientId) {
                dischargedPatientRow.createCell(0).setCellValue(activePatientRow.getCell(0).getStringCellValue());
                dischargedPatientRow.createCell(1).setCellValue(activePatientRow.getCell(1).getStringCellValue());
                dischargedPatientRow.createCell(2).setCellValue(activePatientRow.getCell(2).getStringCellValue());
                dischargedPatientRow.createCell(3).setCellValue(activePatientRow.getCell(3).getStringCellValue());
                dischargedPatientRow.createCell(4).setCellValue(activePatientRow.getCell(4).getNumericCellValue());
                dischargedPatientRow.createCell(5).setCellValue(activePatientRow.getCell(5).getNumericCellValue());
                dischargedPatientRow.createCell(6).setCellValue(activePatientRow.getCell(6).getStringCellValue());
                dischargedPatientRow.createCell(7).setCellValue(activePatientRow.getCell(7).getStringCellValue());
                dischargedPatientRow.createCell(8).setCellValue(activePatientRow.getCell(8).getNumericCellValue());

                activePatientSheet.removeRow(activePatientRow);
                int totalRow = activePatientSheet.getLastRowNum();
                if (totalRow >= 2) {
                    activePatientSheet.shiftRows(2, totalRow, -1);
                }
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream(dischargedPatientStorage);
        FileOutputStream fileOutputStream1 = new FileOutputStream(activePatientStorage);
        activePatientWorkbook.write(fileOutputStream1);
        dischargedPatientWorkbook.write(fileOutputStream);
    }

    public Map<String,Integer> numberOfPeopleAssignedInToAWard() throws IOException {
        List<String> wardAssigned = new ArrayList<>();
        Map<String,Integer> patientCount = new HashMap<>();
        allPatientsWithIdAsKey().forEach((k,v)->wardAssigned.add(v.getWardAssigned()));
        for (String ward : wardAssigned){
            Integer count = patientCount.get(ward);
            patientCount.put(ward,count+1);
        }
        return patientCount;
    }

    public void hopitalReport() throws IOException {
        FileInputStream activePatients = new FileInputStream(activePatientStorage);
        FileInputStream dischargedPatients = new FileInputStream(dischargedPatientStorage);
        XSSFWorkbook activePatinetWorkBook = new XSSFWorkbook(activePatients);
        XSSFWorkbook dischargePatientWorkBook = new XSSFWorkbook(dischargedPatients);
        XSSFSheet activePatientSheet = activePatinetWorkBook.getSheetAt(0);
        XSSFSheet dischargedPatientSheet = dischargePatientWorkBook.getSheetAt(0);
        List<Double> num = new ArrayList<>();
        double sum = 0;
        int totalNumberOfPatients = activePatientSheet.getLastRowNum() + dischargedPatientSheet.getLastRowNum();
        int totalNumberOfDischargedPatient = dischargedPatientSheet.getLastRowNum();
        for (Row row : activePatientSheet) {
            if (row.getRowNum() == 0) continue;
            num.add(row.getCell(8).getNumericCellValue());
        }
        for (double n : num) {
            sum += n;
        }
        activePatients.close();
        dischargedPatients.close();
        activePatinetWorkBook.close();
        dischargedPatients.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\USER-PC\\IdeaProjects\\advancehospitalmanagement\\src\\main\\java\\hospitaldatabase\\Report.txt"));
        writer.write("total NumberOfPatients: " + totalNumberOfPatients + "\n" + "totalOustandingbil: " + sum + "\n" + "totalnumberofdischargedpatient : " + totalNumberOfDischargedPatient);
        writer.close();
        System.out.println("total NumberOfPatients: " + totalNumberOfPatients + "\n" + "totalOustandingbil: " + sum + "\n" + "totalnumberofdischargedpatient : " + totalNumberOfDischargedPatient);
    }
}