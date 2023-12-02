package ru.geekbrains.hw4;

import java.util.Locale;
import java.util.Scanner;

public class HomeWork4{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account account = new Account();
        System.out.print("Введите начальный баланс счета: ");
        double balance = scanner.nextDouble();
        if (balance < 0) {
            throw new IllegalArgumentException("Баланс не может быть отрицательным");
        } else {
            account.setBalance(balance);


            while (true) {
                System.out.printf("\nБаланс счета: %s", account.getBalance());
                System.out.println("\nМеню операций:");
                System.out.println("1 - Внести депозит");
                System.out.println("2 - Снять средства");
                System.out.println("0 - Выйти");
                System.out.print("Введите необходимый пункт меню: ");

                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.print("Сумма депозита: ");
                        double deposit = scanner.nextDouble();
                        if (deposit < 0) {
                            throw new IllegalArgumentException("Сумма депозита не может быть отрицательной");
                        }
                        account.deposit(deposit);
                        break;
                    case 2:
                        System.out.print("Требуемая сумма снятия: ");
                        double withdrawal = scanner.nextDouble();
                        try {
                            account.withdraw(withdrawal);
                        } catch (InsufficientFundsException e) {
                            System.err.println(e.getMessage());
                            continue;
                        }
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Некорректный пункт меню!");
                }
            }
        }
    }}

