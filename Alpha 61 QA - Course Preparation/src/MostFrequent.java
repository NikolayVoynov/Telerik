import java.util.Scanner;

public class MostFrequent {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int[] arr = new int[n];

        int mostRepeatedNum = 0;
        int timesRepeated = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(scanner.nextLine());
        }

        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                }
            }

            if (count > timesRepeated) {
                timesRepeated = count;
                mostRepeatedNum = arr[i];
            }
        }

        System.out.printf("%d (%d times)", mostRepeatedNum, timesRepeated);
    }
}
