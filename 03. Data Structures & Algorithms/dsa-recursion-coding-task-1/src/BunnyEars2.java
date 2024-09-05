import java.util.Scanner;

public class BunnyEars2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        System.out.println(totalNumberOfBunnyEars2(n));
    }

    private static int totalNumberOfBunnyEars2(int n) {
        if (n == 0) {
            return 0;
        }

        if (n % 2 == 0) {
            return 3 + totalNumberOfBunnyEars2(n - 1);
        }

        return 2 + totalNumberOfBunnyEars2(n - 1);
    }
}
