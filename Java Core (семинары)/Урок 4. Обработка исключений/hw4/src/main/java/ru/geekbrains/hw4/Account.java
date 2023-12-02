package ru.geekbrains.hw4;

class Account {
    private double balance;

    public void setBalance(double balance) {

        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            String message = String.format("Недостаточно средств. Остаток: %.2f", balance);
            throw new InsufficientFundsException(message);
        } else {
            balance -= amount;
        }
    }
}

