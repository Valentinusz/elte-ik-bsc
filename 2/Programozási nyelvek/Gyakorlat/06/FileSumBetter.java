import java.io.*;

public class FileSumBetter {
    public static void main(String[] args)  {
        // try with resources - java automatikusan gondoskodik a bezárásról 
        try (BufferedReader br = new BufferedReader(new FileReader("in.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"))) {
            
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