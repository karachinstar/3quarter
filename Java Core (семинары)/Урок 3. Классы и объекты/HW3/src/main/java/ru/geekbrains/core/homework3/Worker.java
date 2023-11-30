package ru.geekbrains.core.homework3;

public class Worker extends BaseWorker{
    private double fixeMonthPayment;


    public Worker(String name, double fixeMonthPayment) {
        super(name);
        this.fixeMonthPayment = fixeMonthPayment;
    }

    @Override
    public double calculateSalary() {
        return fixeMonthPayment;
    }
}
