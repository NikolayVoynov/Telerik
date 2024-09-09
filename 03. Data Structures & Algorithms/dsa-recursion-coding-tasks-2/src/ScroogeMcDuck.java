import java.util.Scanner;

public class ScroogeMcDuck {

    private static int[][] matrix;
    private static int rows;
    private static int cols;
    private static int startRow;
    private static int startCol;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        rows = scanner.nextInt();
        cols = scanner.nextInt();
        matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = scanner.nextInt();

                if (matrix[row][col] == 0) {
                    startRow = row;
                    startCol = col;
                }
            }
        }

        int totalCoins = collectCoins(startRow, startCol);
        System.out.println(totalCoins);
    }

    private static int collectCoins(int row, int col) {
        int collectedCoins = 0;

        int[][] directions = {
                {0, -1},  // left
                {0, 1},   // right
                {-1, 0},  // up
                {1, 0}    // down
        };

        int nextRow = -1;
        int nextCol = -1;
        int maxCoins = -1;

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (isValid(newRow, newCol) && matrix[newRow][newCol] > maxCoins) {
                maxCoins = matrix[newRow][newCol];
                nextRow = newRow;
                nextCol = newCol;
            }
        }

        if (maxCoins > 0) {
            matrix[nextRow][nextCol] = matrix[nextRow][nextCol] - 1;

            collectedCoins = 1 + collectCoins(nextRow, nextCol);
        }

        return collectedCoins;
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}