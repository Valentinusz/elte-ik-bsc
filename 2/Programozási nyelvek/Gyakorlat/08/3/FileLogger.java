import java.io.FileWriter;
import java.io.IOException;

public class FileLogger extends Logger {
    private FileWriter file;

    FileLogger(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException();
        }
        try {
            FileWriter file = new FileWriter(fileName);
            this.file = file;
        } catch (IOException e) {
            System.out.println("File opening error.");
        }
    }

    @Override
    public void log(String str) {
        try {
            this.file.write(str);
            this.file.write("\n");
        } catch (IOException e) {
            System.out.println("Writing error.");
        }
    }

    public void finalize() {
        try {
            this.file.close();
        } catch (IOException e) {
            System.out.println("File closing error.");
        }
    }
}
