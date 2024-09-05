import java.util.Scanner;

public class BunnyEars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        System.out.println(totalNumberOfBunnyEars(n));
    }

    private static int totalNumberOfBunnyEars(int n) {
        if (n == 0) {
            return 0;
        }

        return 2 + totalNumberOfBunnyEars(n - 1);
    }
}
