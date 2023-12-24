import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Airport {
    public static List<String> getDestinations() throws IOException {
        return Files.lines(Path.of("src", "Malev2012Summer.ssim"))
                .filter(line -> line.charAt(0) == '3')
                .filter(line -> line.startsWith("BUD", 36))
                .map(line -> line.substring(54, 57))
                .distinct()
                .filter(IATA -> !IATA.equals("BUD"))
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<String> getDestinationsInTime(int maximumHours) throws IOException {
        return Files.lines(Path.of("src", "Malev2012Summer.ssim"))
                .filter(line -> line.charAt(0) == '3')
                .filter(line -> line.startsWith("BUD", 54))
                .filter(line -> {
                    LocalTime departureTime = LocalTime.parse(line.substring(39, 43), DateTimeFormatter.ofPattern("HHmm"));
                    LocalTime arrivalTime = LocalTime.parse(line.substring(39, 43), DateTimeFormatter.ofPattern("HHmm"));

                    return ChronoUnit.HOURS.between(departureTime, arrivalTime) < maximumHours;
                })
                .map(line -> line.substring(36, 39))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
    public static void main(String[] args) throws IOException {
        getDestinations().stream().forEach(System.out::println);
        getDestinationsInTime(3).stream().forEach(System.out::println);
    }
}
