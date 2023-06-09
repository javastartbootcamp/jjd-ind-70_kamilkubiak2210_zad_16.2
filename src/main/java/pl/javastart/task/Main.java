package pl.javastart.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run(new Scanner(System.in));
    }

    public void run(Scanner scanner) {
        List<String> allFormats = createAllFormats();
        LocalDateTime dateTime = getLocalDateTime(scanner, allFormats);
        if (dateTime != null) {
            printInfoAboutZonedDateTime(dateTime);
        } else {
            System.out.println("Nieobsługiwany format");
        }
    }

    public static List<String> createAllFormats() {
        List<String> formatterList = new LinkedList<>();
        formatterList.add("yyyy-MM-dd HH:mm:ss");
        formatterList.add("dd.MM.yyyy HH:mm:ss");
        return formatterList;
    }

    public static void printInfoAboutZonedDateTime(LocalDateTime dateTime) {
        ZonedDateTime localZone = dateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime utcZone = localZone.withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime londonZone = localZone.withZoneSameInstant(ZoneId.of("Europe/London"));
        ZonedDateTime losAngelesZone = localZone.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
        ZonedDateTime sydneyZone = localZone.withZoneSameInstant(ZoneId.of("Australia/Sydney"));

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Czas lokalny: " + localZone.format(outputFormatter));
        System.out.println("UTC: " + utcZone.format(outputFormatter));
        System.out.println("Londyn: " + londonZone.format(outputFormatter));
        System.out.println("Los Angeles: " + losAngelesZone.format(outputFormatter));
        System.out.println("Sydney: " + sydneyZone.format(outputFormatter));
    }

    private static LocalDateTime getLocalDateTime(Scanner scanner, List<String> allFormats) {
        System.out.println("Podaj datę:");
        String userInput = scanner.nextLine();
        LocalDateTime dateTime = null;

        int dotUnicode = 46;
        if (userInput.length() == 10) {
            LocalDate date = LocalDate.parse(userInput, DateTimeFormatter.ISO_LOCAL_DATE);
            dateTime = date.atStartOfDay();
            return dateTime;
        } else {
            if (userInput.length() > 2) {
                for (String allFormat : allFormats) {
                    if (userInput.charAt(2) == (char) dotUnicode && allFormat.charAt(2) == (char) dotUnicode) {
                        dateTime = getLocalDateTime(allFormat, userInput);
                        return dateTime;
                    } else if ((userInput.length() == 19) && (allFormat.length() == 19) && userInput.charAt(2) != (char) dotUnicode) {
                        dateTime = getLocalDateTime(allFormat, userInput);
                        return dateTime;
                    }
                }
            }
        }
        return dateTime;
    }

    private static LocalDateTime getLocalDateTime(String pattern, String userInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(userInput, formatter);
    }
}
