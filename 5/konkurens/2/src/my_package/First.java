package my_package;

class First {
    // Thread: magas szintű interfész párhuzamos programozáshoz
    // Thread sok módon létrehozható
    public static void main(String[] args) throws InterruptedException {
        // 1. Runnable injektálása
        // hátrány: létrehozáskor nem férünk hozzá a szál adattagjaihoz
        Thread a = new Thread(() -> {
            System.out.println("A");
            try {
                // alvás legalább n milliszekundumig
                // InterruptedException, ha megszakad -> try/catch kell
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // aktuális szál lekérése és megszakítása
                Thread.currentThread().interrupt();
            }
            System.out.println("A kilép!");
        });

        Thread b = new B(a);

        // start indítja el a szál futását
        a.start();
        b.start();
    }

    // 2. kiterjesztés - run() metódus felüldefiniálása
    // hátrány: elhasználjuk a kiterjesztést
    public static class B extends Thread {
        private final Thread waitFor;

        B(Thread waitFor) {
            this.waitFor = waitFor;
        }

        @Override
        public void run() {
            System.out.println("B");
            try {
                // adott szál bevárása, megszakadhat
                waitFor.join();
            } catch (InterruptedException e) {
                this.interrupt();
            }
            System.out.println("B kilép A után");
        }
    }
    // 3. ThreadBuilder, Java 21 újdonság
}