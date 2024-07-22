import java.util.Scanner;

public class PrimeTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int[] arrayPrimeStatus = new int[n];
        int index = 0;

        for (int number = 1; number <= n; number++) {
            int counter = 1;

            for (int divider = 2; divider <= Math.sqrt(number); divider++) {
                if (number % divider == 0) {
                    counter++;
                }
            }

            if (counter < 2) {
                arrayPrimeStatus[index] = 1;
            }
            index++;
        }

        for (int i = 0; i < n; i++) {
            int value = arrayPrimeStatus[i];

            if (value == 1) {
                for (int j = 0; j <= i; j++) {
                    System.out.print(arrayPrimeStatus[j]);
                }
                System.out.println();
            }
        }
    }
}