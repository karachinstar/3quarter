package jdk.ru.heekbrains.hw3.task2;

public class Main {
    public static void main(String[] args) {

        String[] arr1 = new String[]{"God", "Dog", "Cat"};
        String[] arr2 = new String[]{"God", "Dog", "Cat"};

        Integer[] arr3 = new Integer[]{3, 2, 1, 1};
        Integer[] arr4 = new Integer[]{3, 2, 1, 1};

        Integer[] arr5 = new Integer[]{1, 2, 3};
        Integer[] arr6 = new Integer[]{1, 1, 2, 2};

        Integer[] arr7 = new Integer[]{1, 2, 3, 3};
        String[] arr8 = new String[]{"AAa", "Vvv", "Rrr"};

        System.out.println(Arrays.compareArrays(arr1, arr2));
        System.out.println(Arrays.compareArrays(arr3, arr4));
        System.out.println(Arrays.compareArrays(arr5, arr6));
        System.out.println(Arrays.compareArrays(arr7, arr8));
    }
}
