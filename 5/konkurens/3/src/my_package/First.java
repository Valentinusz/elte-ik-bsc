package my_package;

import java.util.Random;

class Car implements Runnable {
    private int fuel;
    private int traveled;

    Car(int fuel) {
        this.fuel = fuel;
        this.traveled = 0;
    }

    @Override
    public void run() {
        while(this.fuel >= 5) {
            System.out.println(Thread.currentThread().getName() + ": brumm-brumm");
            this.fuel -= 5;
            this.traveled += 100;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " stopped after: " + this.traveled);
    }
}

public class First {
    public static void main(String[] args) {
        Random random = new Random();

        new Thread(new Car(random.nextInt(20, 40))).start();
        new Thread(new Car(random.nextInt(20, 40))).start();
        new Thread(new Car(random.nextInt(20, 40))).start();
        new Thread(new Car(random.nextInt(20, 40))).start();
        new Thread(new Car(random.nextInt(20, 40))).start();
    }
}
