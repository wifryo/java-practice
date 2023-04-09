import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double density = scanner.nextDouble();
        double height = scanner.nextDouble();
        double pressure = density * height * 9.8;
        System.out.println(pressure);
    }
}