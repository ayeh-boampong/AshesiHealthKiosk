import java.util.Scanner;
import java.util.Random;

public class HealthKiosk {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        // Task 1
        System.out.print("Enter service code (p/l/t/c): ");
        char serviceCode = input.next().charAt(0);
        String serviceName = " ";

        switch (serviceCode) {
            case 'p':
                serviceName = "PHARMACY";
                System.out.println("head to : Pharmacy Desk");
                break;
            case 'l':
                serviceName = "LAB";
                System.out.println("head to: Lab Desk");
                break;
            case 't':
                serviceName = "TRIAGE";
                System.out.println("head to: Triage Desk");
                break;
            case 'c':
                serviceName = "COUNSELING";
                System.out.println("head to: Counseling Desk");
                break;
            default:
                System.out.println("Invalid service code");
                input.close();
                return;
        }


        double metricValue = 0.0;
        String metricType = "";


        if (serviceCode == 'T') {
            System.out.println("Select health metric:");
            System.out.println("1 - BMI");
            System.out.println("2 - Dosage round-up");
            System.out.println("3 - Simple trig helper");
            System.out.print("Enter choice (1/2/3): ");
            int metricChoice = input.nextInt();

            if (metricChoice == 1) {

                System.out.print("Enter weight(kg): ");
                double weight = input.nextDouble();
                System.out.print("Enter height(m): ");
                double height = input.nextDouble();

                double bmi = weight / Math.pow(height, 2);
                double roundedBMI = Math.round(bmi * 10) / 10.0;
                metricValue = roundedBMI;
                metricType = "BMI";

                String category;
                if (bmi < 18.5) {
                    category = "Underweight";
                } else if (bmi >= 18.5 && bmi <= 24.9) {
                    category = "Normal";
                } else if (bmi >= 25.0 && bmi <= 29.9) {
                    category = "Overweight";
                } else {
                    category = "Obese";
                }

                System.out.println("BMI: " + roundedBMI + " Category: " + category);

            } else if (metricChoice == 2) {

                System.out.print("Enter  dosage (mg): ");
                double dosage = input.nextDouble();

                int tablets = (int) Math.ceil(dosage / 250.0);
                metricValue = tablets;
                metricType = "TABLETS";

                System.out.println("Tablets  to take: " + tablets);

            } else if (metricChoice == 3) {

                System.out.print("Enter angle : ");
                double degrees = input.nextDouble();

                double radians = Math.toRadians(degrees);
                double sinValue = Math.round(Math.sin(radians) * 1000) / 1000.0;
                double cosValue = Math.round(Math.cos(radians) * 1000) / 1000.0;

                System.out.println("sin(" + degrees + "°) = " + sinValue);
                System.out.println("cos(" + degrees + "°) = " + cosValue);


                metricValue = Math.round(sinValue * 100);
                metricType = "TRIG";
            }
        }


        char randomChar = (char) ('A' + random.nextInt(26));


        String randomDigits = "";
        for (int i = 0; i < 4; i++) {
            randomDigits += (3 + random.nextInt(7));
        }


        String shortID = randomChar + randomDigits;

        System.out.println("Generated ID: " + shortID);


        boolean isValid = true;
        String errorMessage = "";

        if (shortID.length() != 5) {
            isValid = false;
            errorMessage = "Invalid length";
        } else if (!Character.isLetter(shortID.charAt(0))) {
            isValid = false;
            errorMessage = "Invalid: first char must be a letter";
        } else if (!Character.isDigit(shortID.charAt(1)) ||
                !Character.isDigit(shortID.charAt(2)) ||
                !Character.isDigit(shortID.charAt(3)) ||
                !Character.isDigit(shortID.charAt(4))) {
            isValid = false;
            errorMessage = "Invalid: last 4 must be digits";
        }

        if (isValid) {
            System.out.println("ID OK");
        } else {
            System.out.println(errorMessage);
        }

        System.out.print("Enter your first name: ");
        String firstName = input.next();


        char baseCode = Character.toUpperCase(firstName.charAt(0));
        System.out.println("Base code = " + baseCode);


        char shiftedLetter = (char) ('A' + (baseCode - 'A' + 2) % 26);
        System.out.println("Shifted letter of base code = " + shiftedLetter);


        String lastTwo = shortID.substring(3, 5);
        System.out.println("Last two characters for ID (task 3): " + lastTwo);


        int metricInt = (int) Math.round(metricValue);


        String displayCode = shiftedLetter + lastTwo + "-" + metricInt;
        System.out.println("Display Code: " + displayCode);


        System.out.print("Summary: " + serviceName + " | ID=" + shortID);


        if (serviceCode == 'T' && metricType.equals("BMI")) {
            System.out.print(" | BMI=" + metricValue);
        }

        System.out.println(" | Code=" + displayCode);

        input.close();
    }
}