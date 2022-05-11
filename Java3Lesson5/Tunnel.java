package Java3Lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private static Semaphore semaphore;
    public Tunnel(int length) {
        semaphore = RaceCondition.TUNNEL_SEMAPHORE;
        this.length = length;
        this.description = "������� " + length + " ������";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " ��������� � �����(����): " + description);
                semaphore.acquire();
                System.out.println(c.getName() + " ����� ����: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " �������� ����: " + description);
                semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
