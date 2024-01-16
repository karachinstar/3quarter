package jdk.ru.geekbrains.hw5;

import java.util.concurrent.Semaphore;

public class Fork {
    private final int id;
    private final Semaphore semaphore = new Semaphore(1);

    public Fork(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void up(){
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void down(){
        semaphore.release();
    }
}