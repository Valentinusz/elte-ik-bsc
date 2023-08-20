import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CountInFile {
    public static void main(String[] args) {
        try {
            if (args.length > 1) {
                Scanner scanner = new Scanner(new File(args[0]));
                long count = 0;
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    if (line.contains(args[1])) {
                        count++;
                    }
                }
                System.out.println("Count: " + count);

            } else {
                throw new IllegalArgumentException("Please supply a filename.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Unable to locate file.");
        }

    }
}
