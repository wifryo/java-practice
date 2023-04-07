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
            return;
        } else if (secretLength < 1) {
            System.out.println("Error: insufficient number");
            return;
        } else {
            secretCode = getSecret(secretLength);
        }
        char[] secretCodeArray = secretCode.toCharArray();

        System.out.println("Okay, let's start a game! Maybe slam out a guess? Or not, I'm a sign, not a cop.");
        boolean victory = false;
        int turnCount = 1;

        while (!victory) {
            System.out.println("Turn " + turnCount + ":");

            System.out.println("The secret code is " + secretCode);
            turnCount++;
            Scanner scanner = new Scanner(System.in);

            int guess = scanner.nextInt();
            String stringFormatting = "%0" + String.valueOf(secretLength) + "d";
            String guessString = String.format(stringFormatting, guess);

            char[] guessArray = guessString.toCharArray();

            int bullsCount = 0;
            int cowsCount = 0;
            for (int i = 0; i < secretLength; i++) {
                if (secretCodeArray[i] == guessArray[i]) {
                    bullsCount++;
                }
                for (int j = 0; j < secretLength; j++) {
                    if (secretCodeArray[i] == guessArray[j]) {
                        cowsCount++;
                    }
                }
            }
            cowsCount = cowsCount - bullsCount;

            int secretCodeInt = Integer.parseInt(secretCode);
            if (guess == secretCodeInt) {
                System.out.println("Grade: " + bullsCount + " bulls.");
                System.out.println("Congratulations! You guessed the secret code.");
                victory = true;
            } else if (cowsCount > 0 || bullsCount > 0) {
                System.out.println("Grade: " + bullsCount + " bull(s) and " + cowsCount + " cow(s).");
            } else {
                System.out.println("Grade: None.");
            }
        }


    }



}
