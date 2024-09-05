import java.util.Scanner;

public class SumDigits {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        System.out.println(sumOfDigits(n));
    }

    private static int sumOfDigits(int n) {
        if (n == 0) {
            return 0;
        }

        int digit = n % 10;
        int number = n / 10;

        return digit + sumOfDigits(number);
    }
}
