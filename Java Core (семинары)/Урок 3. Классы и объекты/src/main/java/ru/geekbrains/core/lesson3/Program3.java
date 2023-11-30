package ru.geekbrains.core.lesson3;

public class Program3 {
    public static void main(String[] args) {
        BaseHuman human1 = Human.create("Name", 18, 100, 1000);
        human1.printDisplayInfo();
        human1.setAge(22);
        human1.printDisplayInfo();
    }
}
