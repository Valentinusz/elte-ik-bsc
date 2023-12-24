import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TaskTwo {
    static class Client extends Thread {
        Worker worker;

        Client(Worker worker) {
            this.worker = worker;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(0, 1000));
                if (!worker.doWork(this)) {
                    System.out.println(this + " is not waiting anymore.");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        public void serve(String msg) throws InterruptedException {
            System.out.println("Client:" + this + "is getting served:" + msg);
            Thread.sleep(ThreadLocalRandom.current().nextInt(300, 500));
            System.out.println("Client:" + this + "is getting served:" + msg);
        }
    }

    static class Worker {
        Semaphore selectionSemaphore = new Semaphore(1);

        Semaphore workSemaphore = new Semaphore(2);

        Client slot1 = null;

        Client slot2 = null;

        public boolean doWork(Client client) throws InterruptedException {
            if (workSemaphore.tryAcquire(450L, TimeUnit.MILLISECONDS)) {
                try {
                    selectionSemaphore.acquireUninterruptibly();

                    int slot;

                    if (slot1 == null) {
                        this.slot1 = client;
                        slot = 1;
                    } else {
                        this.slot2 = client;
                        slot = 2;
                    }

                    selectionSemaphore.release();
                    client.serve("in slot: "  + slot);

                    if (slot == 1) {
                        this.slot1 = null;
                    } else {
                        this.slot2 = null;
                    }



                } finally {
                    workSemaphore.release();
                }
                return true;
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();

        for (int i = 0; i < 12; i++) {
            Client client = new Client(worker);
            client.start();
        }
    }
}
