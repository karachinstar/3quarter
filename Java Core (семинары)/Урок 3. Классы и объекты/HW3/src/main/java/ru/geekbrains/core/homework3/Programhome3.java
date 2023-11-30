package ru.geekbrains.core.homework3;

public class Programhome3 {
    public static void main(String[] args) {
        BaseWorker[] workers = new BaseWorker[6];
        workers[0] = new Worker("Epov", 7000);
        workers[1] = new Freelancer("Ivanov", 60);
        workers[2] = new Freelancer("Timofei", 200);
        workers[3] = new Worker("Jargalov", 4000);
        workers[4] = new Worker("Pechatkin", 5000);
        workers[5] = new Freelancer("Yudickii", 150);


        WorkersArray workerArray = new WorkersArray(workers);

        workerArray.sortByName();

        workerArray.printWorkersInfo();

        workerArray.sortBySalary();
        workerArray.printWorkersInfo();
    }
}
