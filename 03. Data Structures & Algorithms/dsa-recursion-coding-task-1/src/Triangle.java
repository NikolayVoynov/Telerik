import java.util.Scanner;

public class Triangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        System.out.println(totalNumberOfBlocks(n));
    }

    private static int totalNumberOfBlocks(int n) {
        if (n == 0) {
            return 0;
        }

        return n + totalNumberOfBlocks(n - 1);
    }
}
