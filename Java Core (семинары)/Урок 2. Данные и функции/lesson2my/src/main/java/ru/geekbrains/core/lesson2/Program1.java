package ru.geekbrains.core.lesson2;

import java.util.Random;
import java.util.Scanner;

public class Program1 {


    private static final char DOT_HUMAN = 'X'; //маркер игрока
    private static final char DOT_AI = '0'; //маркер ИИ
    private static final char DOT_EMPTY = ' '; //маркер свободного поля
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static char[][] field;

    private static int fieldSizeX; //значение размерности игрового поля
    private static int fieldSizeY; //значение размерности игрового поля


    public static void main(String[] args) {
        initialize();
        printField();
    }

    /**
     * Инициализация игрового поля
     */
    static void initialize(){
        fieldSizeY = 3;
        fieldSizeX = 3;

        field = new char[fieldSizeY][fieldSizeX];
        for (int x = 0; x < fieldSizeY; x++){
            for (int y = 0; y < fieldSizeX; y++){
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    /**
     * Печать состояния текущего поля
     */

    private static void printField(){
        System.out.print("+");
        for (int i = 0; i < fieldSizeX; i++) {
            System.out.print("-" + (i + 1));
        }
        System.out.println("-");


        for (int y = 0; y < fieldSizeY; y++){
            System.out.print(y + 1 + "|");
            for (int x = 0; x < fieldSizeX; x++){
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }

        for (int i = 0; i < fieldSizeX * 2 + 2; i++) {
            System.out.print("-");
        }
    }

    /**
     * Ход игрока (человека)
     */
    static void humanTurn(){
        int x;
        int y;
        System.out.print("Введите координаты хода X и Y (");
    }

}
