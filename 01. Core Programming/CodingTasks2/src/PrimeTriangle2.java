import java.util.Scanner;

public class PrimeTriangle2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        String row = "";

        for (int number = 1; number <= n; number++) {
            int maxDivider = (int) Math.sqrt(number);
            boolean isPrime = true;

            for (int divider = 2; divider <= maxDivider; divider++) {
                if (number % divider == 0) {
                    isPrime = false;
                }
            }

            if (isPrime) {
                row += "1";
                System.out.println(row);
            } else {
                row += "0";
            }
        }
    }
}
