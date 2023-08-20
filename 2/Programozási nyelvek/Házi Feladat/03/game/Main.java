package game;

import game.utils.Vehicle;

public class Main {
    public static void main(String[] args) {
        Vehicle v1 = new Vehicle();
        v1.setRegNum("ABC-123");

        Vehicle v2 = new Vehicle();
        v2.setRegNum("KGB-420");

        Vehicle v3 = new Vehicle();
        v3.setRegNum("FID-357");


        Player p1 = new Player("Kovács Sándor", "42", 100, v2);
        System.out.println(p1.toString());

        Player p2 = new Player("Hegedüs Bence", "69", 420, null);
        System.out.println(p2.toString());


    }
}
