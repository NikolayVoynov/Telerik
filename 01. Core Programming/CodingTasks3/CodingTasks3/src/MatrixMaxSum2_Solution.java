import java.util.Arrays;
import java.util.Scanner;

public class MatrixMaxSum2_Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[rows][];
        for (int i = 0; i < rows; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

        }

        int cols = matrix[0].length;

        int[] pairs = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < pairs.length; i += 2) {
            int horizontalDirection = 1;
            if (pairs[i] < 0) {
                horizontalDirection = -1;
            }

            int verticalDirection = -1;
            if (pairs[i + 1] < 0) {
                verticalDirection = 1;
            }

            int row = Math.abs(pairs[i]) - 1;
            int targetRow = verticalDirection < 0 ? 0 : rows - 1;

            int col = horizontalDirection < 0 ? cols - 1 : 0;
            int targetCol = Math.abs(pairs[i + 1]) - 1;

            int newSum = 0;
            for (; col != targetCol; col += horizontalDirection) {
                newSum += matrix[row][col];
            }

            for (; row != targetRow; row += verticalDirection) {
                newSum += matrix[row][col];
            }

            newSum += matrix[row][col];
            if (newSum > maxSum) {
                maxSum = newSum;
            }
        }

        System.out.println(maxSum);
    }
}



