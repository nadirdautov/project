package Java3Lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable {
    private static int CARS_COUNT = 0;
    private static boolean WINNER = false;
    private static final Lock lock = new ReentrantLock();
    private static final CountDownLatch START_CDL = RaceCondition.CDL_START;
    private static final CountDownLatch FINISH_CDL = RaceCondition.CDL_FINISH;
    private static final CyclicBarrier WARNING_CB = RaceCondition.WARNING_CB;
    private final Race race;
    private final int speed;
    private final String name;



    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            START_CDL.countDown();
            System.out.println(this.name + " готов");
            WARNING_CB.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        FINISH_CDL.countDown();
        Finisher(this.getName());

    }

    public static void Finisher(String name){
        if(!WINNER){
            try {
                lock.lock();
                WINNER = true;
                System.out.println("ПОБЕДИТЕЛЬ >>> " + name);
            } finally {
                lock.unlock();
            }
        }
    }

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }

}
