package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int secretCode = random.nextInt(10000);
        String secretCodeString = String.format("%04d", secretCode);

        int input = scanner.nextInt();
        String inputString = String.format("%04d", input);

        char[] secretCodeArray = secretCodeString.toCharArray();
        char[] inputArray = inputString.toCharArray();

        int bullsCount = 0;
        int cowsCount = 0;
        for (int i = 0; i < 4; i++) {
            if (secretCodeArray[i] == inputArray[i]) {
                bullsCount++;
            }
            for (int j = 0; j < 4; j++) {
                if (secretCodeArray[i] == inputArray[j]) {
                    cowsCount++;
                }
            }
        }
        cowsCount = cowsCount - bullsCount;

        if (input == secretCode) {
            System.out.println("Grade: 4 bulls. The secret code is " + secretCodeString + ".");
        } else if (cowsCount > 0 || bullsCount > 0) {
            System.out.println("Grade: " + bullsCount + " bull(s) and " + cowsCount + " cow(s). The secret code is " + secretCodeString + ".");
        } else {
            System.out.println("Grade: None. The secret code is " + secretCodeString + ".");
        }
    }
}
