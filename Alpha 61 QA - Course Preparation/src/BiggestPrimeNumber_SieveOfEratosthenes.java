import java.util.Scanner;

public class BiggestPrimeNumber_SieveOfEratosthenes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int biggestPrime = Integer.MIN_VALUE;
        boolean[] prime = new boolean[n + 1];

        for (int i = 0; i < prime.length; i++) {
            prime[i] = true;
        }

        for (int p = 2; p * p <= n; p++) {

            if (prime[p] == true) {
                for (int i = p * p; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (prime[i] == true && i > biggestPrime) {
                biggestPrime = i;
            }
        }

        System.out.println(biggestPrime);
    }
}
