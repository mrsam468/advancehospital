package hopitalentities;

public class People {
    private String firstName;
    private String lastName;
    private String otherName;
    private int ID;
    private int age;
    private Gender gender;

    public People(String firstName, String lastName, String otherName, int ID, int age, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherName = otherName;
        this.ID = ID;
        this.age = age;
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

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getOtherName() {
        return otherName;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public String getFUllName() {
        return firstName + " " + lastName + " " + otherName;
    }
}
