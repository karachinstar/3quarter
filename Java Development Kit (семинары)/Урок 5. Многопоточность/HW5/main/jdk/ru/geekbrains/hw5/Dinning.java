package jdk.ru.geekbrains.hw5;

public class Dinning {


    private static final int NUM_PHILOSOPHERS = 5;
    private final Philosopher[] philosophers;
    private final Fork[] forks;

    public Dinning() {
        philosophers = new Philosopher[NUM_PHILOSOPHERS];
        forks = new Fork[NUM_PHILOSOPHERS];

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Fork(i);
        }

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            if(i % 2 == 0) {
                philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % NUM_PHILOSOPHERS]);

            }else{
                philosophers[i] = new Philosopher(i, forks[(i + 1) % NUM_PHILOSOPHERS], forks[i]);

            }
            new Thread(philosophers[i]).start();
        }
    }
}