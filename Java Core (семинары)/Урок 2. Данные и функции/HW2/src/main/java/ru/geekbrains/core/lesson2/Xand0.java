package ru.geekbrains.core.lesson2;

import java.util.Random;
import java.util.Scanner;

public class Xand0 {


    private static final char DOT_HUMAN = 'X'; //маркер игрока
    private static final char DOT_AI = '0'; //маркер ИИ
    private static final char DOT_EMPTY = ' '; //маркер свободного поля
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static char[][] field;

    private static int fieldSizeX; //значение размерности игрового поля
    private static int fieldSizeY; //значение размерности игрового поля

    private static int winCombination;


    public static void main(String[] args) {
        while (true) {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (checkGameState(DOT_HUMAN, "Вы победили!"))
                    break;
                aiTurn();
                printField();
                if (checkGameState(DOT_AI, "Победил компьютер!"))
                    break;
            }
            System.out.print("Желаете сыграть еще раз? (Y - да): ");
            if (!scanner.next().equalsIgnoreCase("Y"))
                break;
        }
    }

    /**
     * Инициализация игрового поля
     */
    static void initialize() {
        fieldSizeY = 5;
        fieldSizeX = 5;
        winCombination = 4;

        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    /**
     * Печать состояния текущего поля
     */

    private static void printField() {
        System.out.print("+");
        for (int i = 0; i < fieldSizeX; i++) {
            System.out.print("-" + (i + 1));
        }
        System.out.println("-");


        for (int y = 0; y < fieldSizeY; y++) {
            System.out.print(y + 1 + "|");
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }

        for (int i = 0; i < fieldSizeX * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Ход игрока (человека)
     */
    static void humanTurn() {
        int x;
        int y;

        do {
            System.out.printf("Введите координаты хода X и Y (от 1 до %s)\nчерез пробел: ", fieldSizeY);
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));

        field[y][x] = DOT_HUMAN;
    }

    /**
     * Ход игрока (компьютера)
     */
    static void aiTurn() {
        checkWin(DOT_HUMAN);
//        int x;
//        int y;
//
//        do {
//            x = random.nextInt(fieldSizeX);
//            y = random.nextInt(fieldSizeY);
//        }
//        while (!isCellEmpty(x, y));
//
//        field[y][x] = DOT_AI;
    }


    /**
     * Проверка, является ли ячейка игрового поля пустой
     *
     * @param x
     * @param y
     * @return
     */
    static boolean isCellEmpty(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    /**
     * Проверка доступности ячейки игровоого поля
     *
     * @param x
     * @param y
     * @return
     */
    static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }


    /**
     * Метод проверки состояния игры
     *
     * @param dot фишка игрока
     * @param s   победный слога
     * @return результат проверки состояния игры
     */
    static boolean checkGameState(char dot, String s) {
        if (checkWin2(dot)) {
            System.out.println(s);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }
        return false; //игра продолжается
    }


    /**
     * Проверка на ничью
     *
     * @return
     */
    static boolean checkDraw() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isCellEmpty(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Попытка сделать умного бота
     *
     * @param dot фишка игрока
     * @return
     */
    static char checkWin(char dot) {
        int temp = 0;
        int temp2 = 0;
        int temp3 = 1;
        int temp4 = 0;
        int temp5 = 0;
        int temp6 = 0;
        // Проверка по горизонталям
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == dot) temp += 1;
                else temp = 0;
                if (temp == winCombination - 1 && field[y][x + 1] != DOT_AI || temp == winCombination - 2 && field[y][x + 1] != DOT_AI)
                    if (x >= fieldSizeX - 1) {
                        if (isCellEmpty(y, winCombination - 1)) {
                            return field[y][x - winCombination - 1] = DOT_AI;
                        } else if (isCellEmpty(y, winCombination - 2)) {
                            return field[y][x - winCombination - 2] = DOT_AI;
                        }
                    } else {
                        if (isCellEmpty(y, x + 1)) {
                            return field[y][x + 1] = DOT_AI;
                        }
                    }
            }
            temp = 0;
        }
        int x;
        int y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x, y));

        return field[y][x] = DOT_AI;
    }

    /**
     * Проверка победы игрока
     * @param dot
     * @return
     */
    static boolean checkWin2(char dot) {
        int temp = 0;
        int temp2 = 0;
        int temp3 = 1;
        int temp4 = 0;
        int temp5 = 0;
        int temp6 = 0;
        // Проверка по горизонталям
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == dot) temp += 1;
                else temp = 0;
                if (temp == winCombination) return true;
            }
            temp = 0;
        }


        // Проверка по вертикалям
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (field[y][x] == dot) temp += 1;
                else temp = 0;
                if (temp == winCombination) return true;
            }
            temp = 0;
        }

        // Проверка по дополнительным диагоналям

        while (temp3 <= fieldSizeX) {
            for (int x = 0; x < temp3; x++) {
                if (field[fieldSizeX + x - temp3][x] == dot) {
                    temp += 1;
                } else {
                    temp = 0;
                }
                if (temp == winCombination) return true;

                if (field[x][fieldSizeX + x - temp3] == dot) {
                    temp2 += 1;
                } else {
                    temp2 = 0;
                }
                if (temp2 == winCombination) return true;
                if (field[temp6 - x][x] == dot) {
                    temp4 += 1;
                } else {
                    temp4 = 0;
                }
                if (temp4 == winCombination) return true;

                if (field[fieldSizeX - 1  - temp6 + x][fieldSizeX  - 1 - x]  == dot) {
                    temp5 += 1;
                } else {
                    temp5 = 0;
                }
                if (temp5 == winCombination) return true;



            }

            temp = 0;
            temp2 = 0;
            temp3 += 1;
            temp4 =0;
            temp5 = 0;
            temp6 += 1;


        }
        return false;
    }
}


