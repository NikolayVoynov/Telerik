import java.util.Scanner;

public class MatrixMaxSum_NotFullyImpl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[n][];

        for (int r = 0; r < n; r++) {
            String[] input = scanner.nextLine().split("\\s+");
            int[] colNumbers = new int[input.length];
            for (int c = 0; c < input.length; c++) {
                colNumbers[c] = Integer.parseInt(input[c]);
            }
            matrix[r] = colNumbers;
        }

        String[] inputCoordinates = scanner.nextLine().split("\\s+");
        int[][] coordinates = new int[inputCoordinates.length / 2][2];

        int rowCounter = 0;

        for (int i = 0; i < inputCoordinates.length; i += 2) {
            int rowCoordinate = Integer.parseInt(inputCoordinates[i]);
            int colCoordinate = Integer.parseInt(inputCoordinates[i + 1]);

            if (rowCoordinate < 0) {
                rowCoordinate += 1;
            } else {
                rowCoordinate -= 1;
            }

            if (colCoordinate < 0) {
                colCoordinate += 1;
            } else {
                colCoordinate -= 1;
            }

            coordinates[rowCounter][0] = rowCoordinate;
            coordinates[rowCounter][1] = colCoordinate;
            rowCounter++;
        }

        long maxSum = 0;
        long currentSum = 0;

        for (int i = 0; i < coordinates.length; i++) {
            int currentRow = coordinates[i][0];
            int currentCol = coordinates[i][1];

            int value = matrix[Math.abs(currentRow)][Math.abs(currentCol)];

            int startRow = 0;
            int endRow = Math.abs(currentRow);

            int startCol = 0;
            int endCol = Math.abs(currentCol);

            if (currentRow < 0) {
                startCol = Math.abs(currentCol);
                endCol = matrix[0].length - 1;
            }

            if (currentCol < 0) {
                startRow = Math.abs(currentRow);
                endRow = n - 1;
            }

            for (int j = startCol; j <= endCol; j++) {
                currentSum += matrix[Math.abs(currentRow)][j];
            }

            for (int j = startRow; j <= endRow; j++) {
                currentSum += matrix[j][Math.abs(currentCol)];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum - value;
                currentSum = 0;
            }
        }

        System.out.println(maxSum);
    }
}
