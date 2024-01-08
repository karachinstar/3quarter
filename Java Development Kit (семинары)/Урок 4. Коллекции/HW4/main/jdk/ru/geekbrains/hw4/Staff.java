package jdk.ru.geekbrains.hw4;

public class Staff {
    private int idNumber;
    private int phoneNumber;
    private String name;
    private int workExperience;



    public Staff(int idNumber, int phoneNumber, String name, int workExperience){
        this.idNumber = idNumber;
        this.workExperience = workExperience;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }

    @Override
    public String toString() {
        return "\n" +
                "Табельный номер - " + idNumber +
                ", Имя - " + name +
                ", Номер телефона - " + phoneNumber +
                ", Стаж - " + workExperience;
    }
}

