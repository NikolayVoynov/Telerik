import java.math.BigInteger;
import java.util.Scanner;

public class Navigation2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int n = scanner.nextInt();
        int[] targetCells = new int[n];
        for (int i = 0; i < n; i++) {
            targetCells[i] = scanner.nextInt();
        }

        // Initialize matrix
        BigInteger[][] matrix = new BigInteger[rows][cols];
        BigInteger firstRowElement = BigInteger.ONE;
        for (int i = rows - 1; i > -1; i--) {
            matrix[i][0] = firstRowElement;
            for (int j = 1; j < cols; j++) {
                matrix[i][j] = matrix[i][j - 1].multiply(BigInteger.valueOf(2));
            }
            firstRowElement = firstRowElement.multiply(BigInteger.valueOf(2));
        }

        // Calculate route
        int currentRow = rows - 1;
        int currentCol = 0;
        int coef = Math.max(rows, cols);
        BigInteger result = BigInteger.ZERO;
        for (int targetCell : targetCells) {
            int targetRow = targetCell / coef;
            int targetCol = targetCell % coef;

            // Calc horizontal direction
            int horizontalDirection;
            if (currentCol <= targetCol) {
                horizontalDirection = 1;
            } else {
                horizontalDirection = -1;
            }

            // Calc vertical direction
            int verticalDirection;
            if (currentRow <= targetRow) {
                verticalDirection = 1;
            } else {
                verticalDirection = -1;
            }

            // Add cells from current row
            for (int i = currentCol; i != targetCol; i += horizontalDirection) {
                result = result.add(matrix[currentRow][i]);
                matrix[currentRow][i] = BigInteger.ZERO;
            }

            // Add cells from target col
            for (int i = currentRow; i != targetRow; i += verticalDirection) {
                result = result.add(matrix[i][targetCol]);
                matrix[i][targetCol] = BigInteger.ZERO;
            }

            // Change current position
            currentRow = targetRow;
            currentCol = targetCol;
        }

        // Add last cell
        result = result.add(matrix[currentRow][currentCol]);

        System.out.println(result);
    }
}