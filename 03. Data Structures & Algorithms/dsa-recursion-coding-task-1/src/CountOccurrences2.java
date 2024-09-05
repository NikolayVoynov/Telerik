import java.util.Scanner;

public class CountOccurrences2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int previousDigit = 0;

        System.out.println(countOccurrencesOfEight(n, previousDigit));
    }

    private static int countOccurrencesOfEight(int n, int previousDigit) {
        if (n == 0) {
            return 0;
        }

        int digit = n % 10;
        int number = n / 10;

        if (digit != 8) {
            return countOccurrencesOfEight(number, digit);
        }

        if (previousDigit == 8){
            return 2 + countOccurrencesOfEight(number, digit);
        }

        return 1 + countOccurrencesOfEight(number, digit);
    }
}
