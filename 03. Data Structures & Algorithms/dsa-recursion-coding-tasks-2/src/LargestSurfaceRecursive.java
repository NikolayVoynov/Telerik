import java.util.Scanner;

public class LargestSurfaceRecursive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        int maxSurface = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != 0) {
                    int surface = calculateSurface(matrix, i, j);
                    if (surface > maxSurface) {
                        maxSurface = surface;
                    }
                }
            }
        }

        System.out.println(maxSurface);
    }

    public static int calculateSurface(int[][] matrix, int row, int col) {
        int currentValue = matrix[row][col];
        matrix[row][col] = 0;

        int totalSurface = 1;
        if (row - 1 >= 0 && matrix[row - 1][col] == currentValue) {
            totalSurface += calculateSurface(matrix, row - 1, col);
        }

        if (row + 1 < matrix.length && matrix[row + 1][col] == currentValue) {
            totalSurface += calculateSurface(matrix, row + 1, col);
        }

        if (col - 1 >= 0 && matrix[row][col - 1] == currentValue) {
            totalSurface += calculateSurface(matrix, row, col - 1);
        }

        if (col + 1 < matrix[0].length && matrix[row][col + 1] == currentValue) {
            totalSurface += calculateSurface(matrix, row, col + 1);
        }

        return totalSurface;
    }
}
