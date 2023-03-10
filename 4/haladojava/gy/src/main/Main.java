package main;

import city.City;
import weekday.WeekDay;

public class Main {
    public static void main(String[] args) {
        for (City city: City.values()) {
            System.out.println(city);
        }

        System.out.println(WeekDay.MON);
        System.out.println(WeekDay.MON.nextDay());
        System.out.println(WeekDay.MON.nextDay(2));
        System.out.println(WeekDay.MON.nextDay(-3));
        System.out.println(WeekDay.WED.nextDay(-2));
        System.out.println(WeekDay.WED.nextDay(-7));
        System.out.println(WeekDay.SUN.getLocale("en"));
        System.out.println(WeekDay.MON.getLocale("hu"));
        System.out.println(WeekDay.WED.getLocale("de"));
    }
}