import java.io.*;
import java.util.Scanner;

public class FileSumEvenBetter {
    public static void main(String[] args)  {
        // try with resources - java automatikusan gondoskodik a bezárásról 
        try {

            Scanner sc = new Scanner(new File("in.txt"));
            double d = sc.nextDouble();


            String line = null;
            while (null != (line = br.readLine())) {
                String[] num = line.split(",");
                long sum = 0;
                for (int i = 0; i < num.length; ++i) {
                    sum += Long.parseLong(num[i]);
                }
                bw.write("Sum: " + sum);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find in.txt.");
        } catch (IllegalArgumentException e) {
            System.out.println("Bad arguments.");
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (Exception e) {
            System.out.println("Exception");
        } 
    }
}