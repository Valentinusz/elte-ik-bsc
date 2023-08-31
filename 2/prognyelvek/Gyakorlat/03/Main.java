import stringutils.IterLetter;

public class Main {
    public static void main(String[] args) {//
        String str = null;

        //IterLetter str1 = new IterLetter(str);
        IterLetter str2 = new IterLetter("asd");

        str2.printNext();
        str2.printNext();
        str2.printNext();
        str2.printNext();

        str2.restart();

        str2.printNext();
        str2.printNext();

        str2.restart();

        str2.printNext();
    }
}
