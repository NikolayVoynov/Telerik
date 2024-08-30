import java.util.Arrays;
import java.util.Scanner;

public class Jumps {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String[] inputArr = scanner.nextLine().split(" ");

        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(inputArr[i]);
        }

        String[] jumpsArr = new String[n];
        int maxJumps = 0;

        for (int i = 0; i < n; i++) {
            int counter = 0;
            int currentNum = numbers[i];

            for (int j = i + 1; j < n; j++) {
                if (currentNum < numbers[j]) {
                    currentNum = numbers[j];
                    counter++;
                }
            }


            if (counter > maxJumps) {
                maxJumps = counter;
            }

            jumpsArr[i] = String.valueOf(counter);
        }

        System.out.println(maxJumps);

        System.out.println(String.join(" ", jumpsArr));
    }

}
