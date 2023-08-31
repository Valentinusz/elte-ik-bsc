package main;

import person.*;

public class PersonMain {
    public static void main(String[] args) {
        Person geri = new Person("Gergely", "Karácsony", "Politician", 1975, Gender.MALE);
        Person feri = new Person("Ferenc", "Gyurcsány", "Politician", 1961, Gender.MALE);
        System.out.println(geri);
        System.out.println(feri);
        System.out.println(geri.equals(feri));
        System.out.println(feri.equals(geri));
        System.out.println(geri.equals(null));
        System.out.println(geri.equals(geri));
        System.out.println(feri.equals(feri));
    }
}
