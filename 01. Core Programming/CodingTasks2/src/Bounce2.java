import java.util.Scanner;

public class Bounce2 {
    public static void main(String[] args) {
        // Read input
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        // Initialize matrix
        long[][] matrix = new long[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = (long) Math.pow(2, i + j);
            }
        }

        if (cols == 1 || rows == 1) {
            System.out.println(1);
            return;
        }

        int currentRow = 0;
        int currentCol = 0;
        int horizontalDirection = 1;
        int verticalDirection = 1;
        long result = 1;

        while (true) {
            // Move to next position
            currentRow += verticalDirection;
            currentCol += horizontalDirection;

            // Check for wall
            if (currentRow == 0 || currentRow == rows - 1) { // Up or down wall
                verticalDirection *= -1;
            }

            if (currentCol == 0 || currentCol == cols - 1) { // Left or right wall
                horizontalDirection *= -1;
            }

            result += matrix[currentRow][currentCol];

            // Check for corner (end bounce)
            if ((currentRow == 0 && currentCol == 0) ||
                    (currentRow == 0 && currentCol == cols - 1) ||
                    (currentRow == rows - 1 && currentCol == 0) ||
                    (currentRow == rows - 1 && currentCol == cols - 1)) {
                break;
            }
        }

        System.out.println(result);
    }
}
