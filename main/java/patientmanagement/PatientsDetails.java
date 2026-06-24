package patientmanagement;


public class PatientsDetails {
    private String firstName;
    private String lastName;
    private String otherName;
    private Gender gender;
    private int patientId;
    private int age;
    private String assignedDoctor;
    private String illnessName;
    private double outstandingBill;

    public PatientsDetails() {
    }

    ;

    public PatientsDetails(String firstName, String lastName, String otherName, Gender gender, int  patientId, int age, String assignedDoctor, String illnessName, double outstandingBill) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherName = otherName;
        this.gender = gender;
        this.patientId = patientId;
        this.age = age;
        this.assignedDoctor = assignedDoctor;
        this.illnessName = illnessName;
        this.outstandingBill = outstandingBill;
    }

    public String getFullName() {
        return firstName + " " + lastName + " " + otherName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAssignedDoctor(String assignedDoctor) {
        this.assignedDoctor = assignedDoctor;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setIllnessName(String illnessName) {
        this.illnessName = illnessName;
    }

    public void setOutstandingBill(double outstandingBill) {
        this.outstandingBill = outstandingBill;
    }

    public int getAge() {
        return age;
    }

    public String getAssignedDoctor() {
        return assignedDoctor;
    }

    public String getFirstName() {
        return firstName;
    }

    public Gender getGender() {
        return gender;
    }

    public String getLllnessName() {
        return illnessName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getOtherName() {
        return otherName;
    }

    public double getOutstandingBill() {
        return outstandingBill;
    }

    public int getPatientId() {
        return patientId;
    }

    public String toString() {
        return
                "age=" + age +
                        ", firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", otherName='" + otherName + '\'' +
                        ", gender=" + gender +
                        ", patientId=" + patientId +
                        ", assignedDoctor='" + assignedDoctor + '\'' +
                        ", illnessName='" + illnessName + '\'' +
                        ", outstandingBill=" + outstandingBill;
    }
}
