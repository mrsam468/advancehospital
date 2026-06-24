import patientmanagement.Gender;

import patientmanagement.HospitalManagement;
import patientmanagement.PatientsDetails;

import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        HospitalManagement hospitalManagement = new HospitalManagement();
        hospitalManagement.storageHeader();
        PatientsDetails patientsDetails = new PatientsDetails("james","daniel","gabriel",Gender.Male,1,23,"julies","typhoid",251.02);
        PatientsDetails patientsDetails2 = new PatientsDetails("SOPHIA","BETTY","DINE",Gender.Female,2,25,"SIMON","typhoid",251.02);
        hospitalManagement.registerPatient(patientsDetails);
        hospitalManagement.registerPatient(patientsDetails2);
        hospitalManagement.viewAllPatients();

    }
}