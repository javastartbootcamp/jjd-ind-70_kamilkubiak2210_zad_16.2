package pl.javastart.task;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run(new Scanner(System.in));
    }

    public void run(Scanner scanner) {
        printGeneralInfo(scanner);
    }

    public static void printGeneralInfo(Scanner scanner) {
        System.out.println("Podaj datę:");
        String userInput = scanner.nextLine();
        int dotUnicode = 46;

        LocalDateTime dateTime;
        if (userInput.length() == 10) {
            LocalDate date = LocalDate.parse(userInput, DateTimeFormatter.ISO_LOCAL_DATE);
            dateTime = date.atStartOfDay();
        } else if (userInput.charAt(2) == (char) dotUnicode) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            dateTime = LocalDateTime.parse(userInput, formatter);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateTime = LocalDateTime.parse(userInput, formatter);
        }

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
}
