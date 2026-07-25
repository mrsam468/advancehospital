package hopitalentities;

public class Nurse extends People {
    private String qualification;
    private double salary;

    public Nurse(String firstName, String lastName, String otherName, int age, Gender gender, String qualification, double salary, int doctorId) {
        this.qualification = qualification;
        this.salary = salary;
        super(firstName, lastName, otherName, doctorId, age, gender);
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getQualification() {
        return qualification;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "Name: " + getFUllName() + '\'' +
                ", Age: " + getAge() + '\'' +
                ", ID: " + getID() + '\'' +
                ", Gender: " + getGender() + '\'' +
                ", qualification='" + qualification + '\'' +
                ", salary=" + salary +
                '}';
    }
}
