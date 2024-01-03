package jdk.ru.heekbrains.hw3.task1;

/*
Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы: sum(), multiply(), divide(),
subtract(). Параметры этих методов – два числа разного типа, над которыми должна быть произведена операция.
 */
public class Calculator {
    public static <F extends Number, S extends Number> double sum(F first, S second) {
        return first.doubleValue() + second.doubleValue();
    }

    public static <F extends Number, S extends Number> double multiply(F first, S second) {
        return first.doubleValue() * second.doubleValue();
    }

    public static <F extends Number, S extends Number> double divide(F first, S second) {
        return first.doubleValue() / second.doubleValue();
    }

    public static <F extends Number, S extends Number> double subtract(F first, S second) {
        return first.doubleValue() - second.doubleValue();
    }
}
