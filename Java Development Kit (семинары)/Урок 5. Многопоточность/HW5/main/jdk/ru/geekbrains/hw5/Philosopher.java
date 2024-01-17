package jdk.ru.geekbrains.hw5;

import java.util.concurrent.locks.ReentrantLock;

public class Philosopher implements Runnable {
    private final int id;
    Fork leftFork;
    Fork rightFork;
    private int countEaten = 0;
    private int countThinks = 0;

    public int getId() {
        return id;
    }

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        sitDownTable();
        while (countEaten < 3) {
                leftFork.up();
                rightFork.up();
                eating();
                rightFork.down();
                leftFork.down();
                thinking();
        }
    }

    private void eating() {
        countEaten++;
        System.out.println(time() + " Филосов №" + id + " обедает. В левой руке держит вилку - " + leftFork.getId() +
                ", в правой " + rightFork.getId() + ". Он поел " + countEaten + " раз. ");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void thinking() {
        countThinks++;
        System.out.println(time() + " Филосов №" + id + " размышляет. 0_о (" + countThinks + ")!");
        try {
            Thread.sleep((long) (Math.random() * 1000));

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void sitDownTable() {
        System.out.println(time() + " Филосов №" + id + " садится за стол");
        try {
            Thread.sleep((long) (Math.random() * 1000));

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String time() {
        long finish = System.nanoTime();
        long times = (finish - Main.getStart()) / 1000;
        return times + " мкс со старта:";
    }
}