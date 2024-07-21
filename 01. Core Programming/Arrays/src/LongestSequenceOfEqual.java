import java.util.Scanner;

public class LongestSequenceOfEqual {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int[] arr = new int[n];


        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(scanner.nextLine());
            arr[i] = num;
        }
        int maxLengthSequence = 1;
        int currentMaxSequence = 1;

        int currentNum = arr[0];

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] == currentNum) {
                currentMaxSequence++;

                if (currentMaxSequence > maxLengthSequence) {
                    maxLengthSequence = currentMaxSequence;
                }

            } else {
                currentNum = arr[i];
                currentMaxSequence = 1;
            }
        }

        System.out.println(maxLengthSequence);
    }
}
