package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static int getSecretLength() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static StringBuilder getRandomData() {
        long pseudoRandomNumber = System.nanoTime();
        StringBuilder bleb = new StringBuilder(String.valueOf(pseudoRandomNumber));
        bleb.reverse();
        return bleb;
    }

    private static String getSecret(int secretLength) {
        StringBuilder secret = new StringBuilder(secretLength);
        StringBuilder randomData = getRandomData();
        int i = 0;

        while (secret.length() < secretLength) {
            Random rand = new Random();
            // Add nonzero first number
            if (secret.length() == 0) {
                secret.append(rand.nextInt((9 - 1) + 1) + 1);

            }
            // If the end of randomData is reached, refresh it
            if (i == randomData.length()) {
                randomData = getRandomData();
                i = 0;
            }
            // IndexOf returns -1 if no match is found, so the below means
            // If the ith character of randomData is not already in secret
            if (secret.toString().indexOf(randomData.charAt(i)) == -1 && secret.length() < secretLength) {
                // Add it to secret
                    secret.append(randomData.charAt(i));

                }


            i++;
        }


        return secret.toString();
    }



    public static void main(String[] args) {
        System.out.println("Please enter the secret code's length:");
        int secretLength = getSecretLength();
        String secretCode = null;
        if (secretLength > 10) {
            System.out.printf("Error: too number");
        } else if (secretLength < 1) {
            System.out.println("Error: insufficient number");
        } else {
            secretCode = getSecret(secretLength);
        }

        System.out.println("Okay, let's start a game! Maybe slam out a guess? Or not, I'm a sign, not a cop.");
        Scanner scanner = new Scanner(System.in);

        int guess = scanner.nextInt();
        String guessString = String.format("%04d", guess);

        char[] secretCodeArray = secretCode.toCharArray();
        char[] guessArray = guessString.toCharArray();

        int bullsCount = 0;
        int cowsCount = 0;
        for (int i = 0; i < 4; i++) {
            if (secretCodeArray[i] == guessArray[i]) {
                bullsCount++;
            }
            for (int j = 0; j < 4; j++) {
                if (secretCodeArray[i] == guessArray[j]) {
                    cowsCount++;
                }
            }
        }
        cowsCount = cowsCount - bullsCount;

        // make this two ints
        if (guess == secretCode) {
            System.out.println("Grade: 4 bulls. The secret code is " + secretCode + ".");
        } else if (cowsCount > 0 || bullsCount > 0) {
            System.out.println("Grade: " + bullsCount + " bull(s) and " + cowsCount + " cow(s). The secret code is " + secretCodeString + ".");
        } else {
            System.out.println("Grade: None. The secret code is " + secretCode + ".");
        }
    }



}
