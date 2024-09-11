import java.util.Scanner;

public class ScroogeMcDuckRecursive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];
        int startRow = 0;
        int startCol = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
                if (matrix[i][j] == 0) {
                    startRow = i;
                    startCol = j;
                }
            }
        }

        System.out.println(collectCoins(matrix, startRow, startCol));
    }

    public static int collectCoins(int[][] matrix, int currentRow, int currentCol) {
        int collectedCoins = 0;
        while (true) {
            int nextStepCoinsMax = 0;
            int nextRow = -1;
            int nextCol = -1;

            if (currentRow + 1 < matrix.length && matrix[currentRow + 1][currentCol] >= nextStepCoinsMax) {
                nextStepCoinsMax = matrix[currentRow + 1][currentCol];
                nextRow = currentRow + 1;
                nextCol = currentCol;
            }

            if (currentRow - 1 >= 0 && matrix[currentRow - 1][currentCol] >= nextStepCoinsMax) {
                nextStepCoinsMax = matrix[currentRow - 1][currentCol];
                nextRow = currentRow - 1;
                nextCol = currentCol;
            }

            if (currentCol + 1 < matrix[0].length && matrix[currentRow][currentCol + 1] >= nextStepCoinsMax) {
                nextStepCoinsMax = matrix[currentRow][currentCol + 1];
                nextRow = currentRow;
                nextCol = currentCol + 1;
            }

            if (currentCol - 1 >= 0 && matrix[currentRow][currentCol - 1] >= nextStepCoinsMax) {
                nextStepCoinsMax = matrix[currentRow][currentCol - 1];
                nextRow = currentRow;
                nextCol = currentCol - 1;
            }

            if (nextStepCoinsMax == 0) {
                return collectedCoins;
            }

            matrix[nextRow][nextCol]--;
            collectedCoins++;
            currentRow = nextRow;
            currentCol = nextCol;
        }
    }
}
