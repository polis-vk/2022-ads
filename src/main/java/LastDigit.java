import java.util.Scanner;

public class LastDigit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextInt() % 10);
        scanner.close();
    }
}
