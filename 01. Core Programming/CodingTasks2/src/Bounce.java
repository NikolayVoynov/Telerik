import java.util.Scanner;

public class Bounce {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] sizeMatrix = scanner.nextLine().split("\\s+");
        int rows = Integer.parseInt(sizeMatrix[0]);
        int cols = Integer.parseInt(sizeMatrix[1]);

        long[][] matrix = new long[rows][cols];

        for (int row = 0; row < rows; row++) {
            int power = row;
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = (long) Math.pow(2, power);
                power++;
            }
        }

        long sum = getSum(cols, rows, matrix);
        {
            System.out.println(sum);
        }
    }

    private static long getSum(int cols, int rows, long[][] matrix) {
        int currentCol = 0;
        int currentRow = 0;
        long sum = 1;
        int deltaCol = 1;
        int deltaRow = 1;
        int maxValidCol = cols - 1;
        int maxValidRow = rows - 1;

        do {
            currentCol += deltaCol;
            currentRow += deltaRow;

            sum += matrix[currentRow][currentCol];

            // hit left or right wall
            if (currentCol == 0 || currentCol >= maxValidCol) {
                deltaCol = -deltaCol;
            }

            // hit top or bottom wall
            if (currentRow == 0 || currentRow >= maxValidRow) {
                deltaRow = -deltaRow;
            }
        }
        while (!((currentRow == 0 || currentRow == maxValidRow) && (currentCol == 0 || currentCol == maxValidCol)));
        return sum;
    }
}
