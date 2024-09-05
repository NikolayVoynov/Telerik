import java.util.Scanner;

public class PowerN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int b = Integer.parseInt(scanner.nextLine());
        int n = Integer.parseInt(scanner.nextLine());

        System.out.println(calculatePowerN(b, n));
    }

    private static int calculatePowerN(int b, int n) {
        if (n == 0) {
            return 1;
        }

        return b * calculatePowerN(b, n - 1);
    }
}
