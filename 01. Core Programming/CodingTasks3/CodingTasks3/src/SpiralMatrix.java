import java.util.Scanner;

public class SpiralMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int[n][n];

        int elementCounter = 1;
        int maxElement = n * n;

        int topBorder = 0;
        int leftBorder = 0;
        int bottomBorder = n - 1;
        int rightBorder = n - 1;

        while (elementCounter <= maxElement) {

            for (int i = leftBorder; i <= rightBorder; i++) {
                matrix[topBorder][i] = elementCounter++;
            }

            topBorder++;

            for (int i = topBorder; i <= bottomBorder; i++) {
                matrix[i][rightBorder] = elementCounter++;
            }

            rightBorder--;

            for (int i = rightBorder; i >= leftBorder; i--) {
                matrix[bottomBorder][i] = elementCounter++;
            }

            bottomBorder--;

            for (int i = bottomBorder; i >= topBorder; i--) {
                matrix[i][leftBorder] = elementCounter++;
            }

            leftBorder++;
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }
}
