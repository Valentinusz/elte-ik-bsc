package my_package;

public class Second {
    public static void main(String[] args) {
        // Thread.activeCount() csak az adott threadgroup-ban lévőket adja vissza
        // esetünkben minden ugyan abba a groupba kerül

        for (int i = 0; i < 1000; i++) {
            // anonim osztály
            // szálakat el lehet nevezni konstruktorral, setName()
            Thread thread = new Thread("sz-" + i) {
                @Override
                public void run() {
                    System.out.println("Név: " + this.getName() + " él még: " + Thread.activeCount());
                }
            };
            thread.start();
        }
    }
}
