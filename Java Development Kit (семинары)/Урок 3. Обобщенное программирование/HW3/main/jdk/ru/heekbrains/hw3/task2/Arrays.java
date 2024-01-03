package jdk.ru.heekbrains.hw3.task2;
/*
Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true, если они одинаковые, и
false в противном случае. Массивы могут быть любого типа данных, но должны иметь одинаковую длину и содержать элементы
одного типа по парно.
 */
public class Arrays {
    public static <T> boolean compareArrays(T[] arr, T[] arr2) {
        boolean  isLengthsEquals = arr.length == arr2.length;
        boolean isClaasEquals = arr.getClass().getName().equals(arr2.getClass().getName());
        return isLengthsEquals && isClaasEquals;
    }
}
