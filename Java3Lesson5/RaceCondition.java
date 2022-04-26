package Java3Lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class RaceCondition {
        public static final int CARS_COUNT = 4;
        public static final CyclicBarrier WARNING_CB = new CyclicBarrier(CARS_COUNT);
        public static final Semaphore TUNNEL_SEMAPHORE = new Semaphore(CARS_COUNT/2);
        public static final CountDownLatch CDL_START = new CountDownLatch(CARS_COUNT);
        public static final CountDownLatch CDL_FINISH = new CountDownLatch(CARS_COUNT);
        public static Race race;
        public static void main(String[] args) throws InterruptedException {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
            race = new Race(new Road(60), new Tunnel(80), new Road(40));
            Car[] cars = new Car[CARS_COUNT];
            for (int i = 0; i < cars.length; i++) {
                cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
            }
            for (int i = 0; i < cars.length; i++) {
                new Thread(cars[i]).start();
            }
            CDL_START.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> START Гонка началась!!!");
            CDL_FINISH.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> FINISH Гонка закончилась!!!");
        }
}

