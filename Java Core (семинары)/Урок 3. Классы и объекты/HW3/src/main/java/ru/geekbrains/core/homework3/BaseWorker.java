package ru.geekbrains.core.homework3;

abstract class BaseWorker {
    protected String name;

    public BaseWorker(String name) {
        this.name = name;
    }

    public abstract double calculateSalary();
    public String getName() {
        return name;
    }
}
