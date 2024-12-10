package se.anna.workplacedatabase.employeeUI;

import java.util.Scanner;

public class ScannerHandler {
    private static final Scanner sc = new Scanner(System.in);

    public String getStringInput(String userInput) {
        System.out.println(userInput);
        return sc.nextLine();
    }

    public double getDoubleInput(String userInput) {
        System.out.println(userInput);
        while (!sc.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid double value.");
            sc.next();
        }
        double result = sc.nextDouble();
        sc.nextLine();
        return result;
    }

    public int getIntInput(String userInput) {
        System.out.println(userInput);
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer.");
            sc.next();
        }
        int result = sc.nextInt();
        sc.nextLine();
        return result;
    }

    public void closeScanner() {
        if (sc != null) {
            sc.close();
        }
    }
}
