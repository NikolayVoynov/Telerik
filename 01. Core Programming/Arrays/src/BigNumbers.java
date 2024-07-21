import java.util.Scanner;

public class BigNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] sizes = scanner.nextLine().split("\\s+");
        int firstSize = Integer.parseInt(sizes[0]);
        int secondSize = Integer.parseInt(sizes[1]);

        int[] arrFirst = new int[firstSize];
        int[] arrSecond = new int[secondSize];

        String[] firstInput = scanner.nextLine().split("\\s+");
        String[] secondInput = scanner.nextLine().split("\\s+");

        for (int i = 0; i < firstSize; i++) {
            arrFirst[i] = Integer.parseInt(firstInput[i]);
        }

        for (int i = 0; i < secondSize; i++) {
            arrSecond[i] = Integer.parseInt(secondInput[i]);
        }

        addTwoNumbers(firstSize, secondSize, arrFirst, arrSecond);
    }

    private static void addTwoNumbers(int firstSize, int secondSize, int[] arrFirst, int[] arrSecond) {
        int keepInMind = 0;
        int sum = 0;

        int maxSize = Math.max(firstSize, secondSize);

        int[] resultNum = new int[maxSize + 1];

        for (int i = 0; i < maxSize; i++) {
            sum = keepInMind;

            if (i < firstSize) {
                sum += arrFirst[i];
            }

            if (i < secondSize) {
                sum += arrSecond[i];
            }

            resultNum[i] = sum % 10;
            keepInMind = sum / 10;
        }

        for (int i = 0; i <= maxSize - 1; i++) {
            System.out.print(resultNum[i] + " ");
        }
    }
}
