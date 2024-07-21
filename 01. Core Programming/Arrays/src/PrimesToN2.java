import java.util.Scanner;

public class PrimesToN2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int target = Integer.parseInt(scanner.nextLine());

        for (int number = 1; number <= target; number++) {
            int counter = 1;

            for (int divider = 2; divider <= Math.sqrt(number); divider++) {
                if (number % divider == 0) {
                    counter++;
                }
            }

            if (counter < 2) {
                System.out.print(number + " ");
            }
        }
    }
}
