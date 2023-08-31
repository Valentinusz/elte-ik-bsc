import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BagMain {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("input.txt"))) {
            Bag<String> bag = new Bag<>();

            while(scanner.hasNext()) {
                bag.add(scanner.nextLine());
            }
    
            System.out.println(bag.toString());
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        
        /*
        String a = "abc";
        String b = "bcd";
        String[] arr = new String[2];
        arr[0] = a;
        arr[1] = b;
        System.out.println(arr[0] + arr[1]);
        Swap.swap(arr,0,1);
        System.out.println(arr[0] + arr[1]);
        */
    }
}
