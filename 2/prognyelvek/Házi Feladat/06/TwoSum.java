import java.io.*;
import java.util.Scanner;

public class TwoSum {

    private static int[] getTwoSum(int number, int[] nums) {
        for (int i=0; i<nums.length; ++i) {
            for (int j=i+1; j<nums.length; ++j) {
                if (nums[i] + nums[j] == number) {
                    return new int[]{nums[i],nums[j]};
                }
            }
        }
        return new int[]{0,0};
    }

    public static void main(String[] args) {
        try (
            Scanner scanner = new Scanner(new File("in.txt"));
            Writer writer = new FileWriter("out.txt");
        ) {

            while (scanner.hasNext()) {
                int number = scanner.nextInt();
                String line = scanner.nextLine().replace(" ", "");

                String[] numsString = line.split(",");

                int[] nums = new int[numsString.length];
    
                for (int i=0; i<numsString.length; ++i) {
                    nums[i] = Integer.parseInt(numsString[i]);
                }


                int[] result = getTwoSum(number, nums);

                StringBuilder sb = new StringBuilder();
                sb.append(number);
                sb.append(" ");

                if (result[0] == result[1]) {
                    sb.append("none");
                } else {
                    sb.append(result[0]);
                    sb.append(" ");
                    sb.append(result[1]);
                }
                sb.append("\n");
                
                writer.write(sb.toString());
            }

        } catch (IllegalArgumentException e) {
            System.out.println("");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to locate file.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}