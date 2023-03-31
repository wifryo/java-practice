import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        String substring = scanner.nextLine();
        Pattern pattern = Pattern.compile(substring);
        Matcher matcher = pattern.matcher(string);
        int counter = 0;
        while (matcher.find()) {
            counter++;
        }
        System.out.println(counter);

    }
}