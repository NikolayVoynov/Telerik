import java.util.Scanner;

public class KnightMoves2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] matrix = new int[n][n];

        // All possible horse moves (8) ordered by priority (top most, left most)
        int[] horseMovesVertical = {-2, -2, -1, -1, +1, +1, +2, +2};
        int[] horseMovesHorizontal = {-1, +1, -2, +2, -2, +2, -1, +1};

        int movesCounter = 1;
        // For each cell in the matrix that is not used try to make horse moves
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int currentRow = r;
                int currentCol = c;

                // Make moves while cell is not visited
                while (matrix[currentRow][currentCol] == 0) {
                    matrix[currentRow][currentCol] = movesCounter;
                    movesCounter++;

                    // Search for possible move
                    for (int move = 0; move < horseMovesHorizontal.length; move++) {
                        int nextRow = currentRow + horseMovesVertical[move];
                        int nextCol = currentCol + horseMovesHorizontal[move];

                        // Check if move goes out of the matrix
                        if (nextRow < 0 || nextRow >= matrix.length ||
                                nextCol < 0 || nextCol >= matrix.length) {
                            continue;
                        }

                        // Check if the cell is visited
                        if (matrix[nextRow][nextCol] != 0) {
                            continue;
                        }

                        currentRow = nextRow;
                        currentCol = nextCol;
                        break;
                    }
                }
            }
        }

        // Print the output
        for (int[] row : matrix) {
            String result = "";
            for (int cell : row) {
                result += cell + " ";
            }
            System.out.println(result.trim());
        }
    }
}
