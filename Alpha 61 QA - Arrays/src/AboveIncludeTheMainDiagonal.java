import java.util.Scanner;

public class AboveIncludeTheMainDiagonal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int side = Integer.parseInt(scanner.nextLine());
        long sum = 0;

        for (int row = 0; row < side; row++) {
            for (int col = row; col < side; col++) {
                sum += (long) Math.pow(2, row + col);
            }
        }

        System.out.println(sum);
    }
}
