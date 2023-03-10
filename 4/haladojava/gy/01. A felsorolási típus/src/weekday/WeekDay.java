package weekday;


import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Set;

public enum WeekDay {
    // 2.1
    MON(new AbstractMap.SimpleEntry<>("hu", "hétfő"), new AbstractMap.SimpleEntry<>("en", "monday")),
    TUE(new AbstractMap.SimpleEntry<>("hu", "kedd"), new AbstractMap.SimpleEntry<>("en", "tuesday")),
    WED(new AbstractMap.SimpleEntry<>("de", "mittwoch")),
    THU(),
    FRI(),
    SAT(),
    SUN();

    final HashMap<String, String> locales = new HashMap<>();

    // 2.4
    static final Set<String> supportedLocales = Set.of("hu", "de", "en");

    // 2.3, 2.4.2
    // java nem örül ha paraméterezett típusokat használunk vararagba, mert a castolásnál problémák lehetnek
    // ezért meg kell győződnünk arról, hogy a paraméterben megadott változók valóban
    // AbstractMap.SimpleEntry<String, String> típusúak
    // https://stackoverflow.com/questions/12462079/possible-heap-pollution-via-varargs-parameter
    @SafeVarargs
    WeekDay(AbstractMap.SimpleEntry<String, String>... locale) {
        for (var entry: locale) {
            // redundánsnak tűnhet de ez kell hogy biztonságos legyen
            if (entry instanceof AbstractMap.SimpleEntry<String, String>) {
                this.locales.put(entry.getKey(), entry.getValue());
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    // 2.2
    public WeekDay nextDay(int skipAmount) {
        var values = WeekDay.values();

        int index = this.ordinal();

        if (skipAmount < 0) {
            index += (values.length + skipAmount);
        } else {
            index += skipAmount;
        }

        index %= values.length;

        return values[index];
    }

    public WeekDay nextDay() {
        return nextDay(1);
    }

    // 2.4.1
    public String getLocale(String lang) {
        if (supportedLocales.contains(lang)) {
            String locale = this.locales.get(lang);

            if (locale != null) {
                return locale;
            }
        }

        return "?";
    }

}
