public class ConsoleLogger extends Logger {
    
    @Override
    public void log(String str) {
        System.out.println(str);
    }
}
