package jdk.ru.geekbrains.hw4;

import java.util.ArrayList;

public class StaffDirectory {
    ArrayList<Staff> staffs;

    public StaffDirectory(){
        this.staffs = new ArrayList<>();
    }

    public void addStaff(Staff staff){
        this.staffs.add(staff);
    }

    public ArrayList<Staff> searchByExperience(int workExperience) {
        ArrayList<Staff> result = new ArrayList<>();
        for (Staff employee : this.staffs) {
            if (employee.getWorkExperience() == workExperience) {
                result.add(employee);
            }
        }
        return result;
    }

    public ArrayList getPhoneByName(String name) {
        ArrayList result = new ArrayList<>();
        for (Staff staff : this.staffs) {
            if (staff.getName().equals(name)) {
                result.add(staff.getPhoneNumber());
            }
        }
        return result;
    }

    public Staff searchByServiceNumber(int serviceNumber) {
        for (Staff employee : this.staffs) {
            if (employee.getIdNumber() == serviceNumber) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Сотрудники: " + staffs;
    }
}
