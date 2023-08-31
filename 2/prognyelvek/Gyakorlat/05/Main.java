public class Main {
    public static void main(String[] args) {
        WildAnimal micimacko = WildAnimal.RACOON;
        System.out.println(WildAnimal.listAllAnimals());

        int[] linearData = {1,2,3,4,5,6};
        IntegerMatrix im = new IntegerMatrix(2, 3, linearData);
        System.out.println(im.toString());
    }   
}
