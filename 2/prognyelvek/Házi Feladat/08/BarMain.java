public class BarMain {
    public static void main(String[] args) {
        Minor a = new Minor("a", 17);
        Adult b = new Adult("b", 31);


        Beverage bev1 = new Beverage("patkany", 18); // csak 18 feletti ihatja
        Beverage bev2 = new Beverage("tej", 0);

        Bartender bartender = new Bartender();

        System.out.println(bartender.order(bev1, a));
        System.out.println(bartender.order(bev1, b));

        System.out.println(bartender.order(bev2, a));
        System.out.println(bartender.order(bev2, b));
    }   
}
