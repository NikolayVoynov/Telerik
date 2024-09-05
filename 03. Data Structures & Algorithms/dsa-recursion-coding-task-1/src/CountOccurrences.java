import java.util.Scanner;

public class CountOccurrences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        System.out.println(countOccurrencesOfSeven(n));
    }

    private static int countOccurrencesOfSeven(int n) {
        if (n == 0) {
            return 0;
        }

        int digit = n % 10;
        int number = n / 10;

        if (digit != 7) {
            return countOccurrencesOfSeven(number);
        }


        return 1 + countOccurrencesOfSeven(number);
    }
}
