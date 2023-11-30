package ru.geekbrains.core.homework3;

import java.util.Arrays;
import java.util.Comparator;

public class WorkersArray {
    private BaseWorker[] workers;

    public WorkersArray(BaseWorker[] workers){
        this.workers = workers;
    }

    public void sortByName(){
        System.out.println("Sorted by name:");
        Arrays.sort(workers, Comparator.comparing(BaseWorker::getName));
    }

    public void sortBySalary(){
        System.out.println("Sorted by salary:");
        Arrays.sort(workers, Comparator.comparing(BaseWorker::calculateSalary));
    }

    public void printWorkersInfo(){

        for(BaseWorker worker : workers){
            System.out.println("Name: " + worker.getName() + ", Salary: " + worker.calculateSalary());
        }
    }
}
