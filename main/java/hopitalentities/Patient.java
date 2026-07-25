package hopitalentities;

public class Patient extends People {
    private String assignedDoctor;
    private String illnessName;
    private double outstandingBill;
    private String wardAssigned;

    public Patient(String firstName, String lastName, String otherName, Gender gender, int patientId, int age, String assignedDoctor, String illnessName, double outstandingBill, String wardAssigned) {
        this.assignedDoctor = assignedDoctor;
        this.illnessName = illnessName;
        this.outstandingBill = outstandingBill;
        this.wardAssigned = wardAssigned;
        super(firstName, lastName, otherName, patientId, age, gender);
    }

    public void setAssignedDoctor(String assignedDoctor) {
        this.assignedDoctor = assignedDoctor;
    }

    public void setIllnessName(String illnessName) {
        this.illnessName = illnessName;
    }

    public void setOutstandingBill(double outstandingBill) {
        this.outstandingBill = outstandingBill;
    }

    public String getAssignedDoctor() {
        return assignedDoctor;
    }

    public String getIllnessName() {
        return illnessName;
    }

    public double getOutstandingBill() {
        return outstandingBill;
    }

    public String getWardAssigned() {
        return wardAssigned;
    }

    public String toString() {
        return "Patient{" + " " +
                "FirstName: " + getFirstName() + '\'' +
                ", LastName: " + getLastName() + '\'' +
                ", OtherName: " + getOtherName() + '\'' +
                ", ID: " + getID() + '\'' +
                ", Age: " + getAge() + '\'' +
                ", Gender: " + getGender() + '\'' +
                ", assignedDoctor:'" + assignedDoctor + '\'' +
                ", illnessName:'" + illnessName + '\'' +
                ", outstandingBill: " + outstandingBill +
                ", wardAssigned='" + wardAssigned + '\'' +
                '}';
    }
}
