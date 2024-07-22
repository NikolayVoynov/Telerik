import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Navigation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());
        int cols = Integer.parseInt(scanner.nextLine());
        int moves = Integer.parseInt(scanner.nextLine());

        String[] stringsPositions = scanner.nextLine().split("\\s+");
        int[] integerPositions = new int[stringsPositions.length];

        for (int i = 0; i < stringsPositions.length; i++) {
            integerPositions[i] = Integer.parseInt(stringsPositions[i]);
        }

        int coefficient = Math.max(rows, cols);

        int[][] positionsAsRowsCols = new int[moves][2];

        for (int i = 0; i < moves; i++) {
            int row = integerPositions[i] / coefficient;
            int col = integerPositions[i] % coefficient;

            positionsAsRowsCols[i][0] = row;
            positionsAsRowsCols[i][1] = col;
        }

        BigInteger[][] board = new BigInteger[rows][cols];

        for (int c = 0; c < cols; c++) {
            int exponent = c;
            for (int r = rows - 1; r >= 0; r--) {
                board[r][c] = BigInteger.valueOf((long) Math.pow(2, exponent++));
            }
        }

        int currentRow = rows - 1;
        int currentCol = 0;
        BigInteger sum = new BigInteger(String.valueOf(board[currentRow][currentCol]));
        board[currentRow][currentCol] = BigInteger.ZERO;

        for (int i = 0; i < moves; i++) {
            int targetRow = positionsAsRowsCols[i][0];
            int targetCol = positionsAsRowsCols[i][1];

            if (currentCol < targetCol) {
                for (int j = currentCol + 1; j <= targetCol; j++) {
                    BigInteger value = new BigInteger(String.valueOf(board[currentRow][j]));
                    sum = sum.add(value);
                    board[currentRow][j] = BigInteger.ZERO;
                }
                currentCol = targetCol;
            }

            if (currentCol > targetCol) {
                for (int j = currentCol - 1; j >= targetCol; j--) {
                    BigInteger value = new BigInteger(String.valueOf(board[currentRow][j]));
                    sum = sum.add(value);
                    board[currentRow][j] = BigInteger.ZERO;
                }
                currentCol = targetCol;
            }

            if (currentRow < targetRow) {
                for (int j = currentRow + 1; j <= targetRow; j++) {
                    BigInteger value = new BigInteger(String.valueOf(board[j][currentCol]));
                    sum = sum.add(value);
                    board[j][currentCol] = BigInteger.ZERO;
                }

                currentRow = targetRow;
            }

            if (currentRow > targetRow) {
                for (int j = currentRow - 1; j >= targetRow; j--) {
                    BigInteger value = new BigInteger(String.valueOf(board[j][currentCol]));
                    sum = sum.add(value);
                    board[j][currentCol] = BigInteger.ZERO;
                }

                currentRow = targetRow;
            }
        }

        System.out.println(sum);
    }
}



