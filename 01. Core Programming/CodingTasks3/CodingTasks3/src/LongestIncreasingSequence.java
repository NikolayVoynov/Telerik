import java.util.Scanner;

public class LongestIncreasingSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int[] numbers = new int[n];

        int maxLengthSequence = 1;
        int currentLengthSequence = 1;

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(scanner.nextLine());
        }

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > numbers[i - 1]) {
                currentLengthSequence++;
            } else {
                if (currentLengthSequence > maxLengthSequence) {
                    maxLengthSequence = currentLengthSequence;
                }

                currentLengthSequence = 1;
            }
        }

        System.out.print(maxLengthSequence);
    }
}