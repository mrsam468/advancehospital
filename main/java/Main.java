import patientmanagement.Gender;

import patientmanagement.HospitalManagement;
import patientmanagement.PatientsDetails;

import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        HospitalManagement hospitalManagement = new HospitalManagement();
        hospitalManagement.storageHeader();
        PatientsDetails patientsDetails1 = new PatientsDetails("james","daniel","gabriel",Gender.Male,1,23,"julies","typhoid",251.02);
        PatientsDetails patientsDetails2 = new PatientsDetails("SOPHIA","BETTY","DINE",Gender.Female,2,25,"SIMON","typhoid",251.02);
        hospitalManagement.registerPatient(patientsDetails1);
        hospitalManagement.registerPatient(patientsDetails2);
        patientsDetails1.setAge(24);
        System.out.println();
        System.out.println(hospitalManagement.searchPatientWithFullName("SOPHIA BETTY DINE"));
        System.out.println(hospitalManagement.searchPatientWithId(1));
        hospitalManagement.updateAssignedDoctor(1,"johnson");
        hospitalManagement.updatePatientIllness(2,"HIV");
        hospitalManagement.updateOutstandingBill(2,250.01);


    }
}