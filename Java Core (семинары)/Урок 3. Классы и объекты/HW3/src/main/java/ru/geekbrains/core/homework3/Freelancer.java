package ru.geekbrains.core.homework3;

public class Freelancer extends BaseWorker{
    private  double hourPayment;
    public Freelancer(String name, double hourPayment) {
        super(name);
        this.hourPayment = hourPayment;
    }

    @Override
    public double calculateSalary() {
        return 20.8 * 8 * hourPayment;
    }
}
