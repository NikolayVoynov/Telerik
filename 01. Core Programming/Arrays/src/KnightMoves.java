import java.util.Scanner;

public class KnightMoves {
    private static int currentRow = 0;
    private static int currentCol = 0;
    private static int moves = 1;
    private static int[][] chessBoard;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        chessBoard = new int[size][size];

        chessBoard[currentRow][currentCol] = 1;

        int maxMoves = size * size;

        while (moves < maxMoves) {

            boolean successfulMove = move(currentRow, currentCol);

            if (!successfulMove) {
                checkForNotVisitedLeftMostTopMost(size);
            }
        }

        printChessBoard(size);
    }

    private static void checkForNotVisitedLeftMostTopMost(int size) {
        boolean found = false;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int currentCell = chessBoard[row][col];
                if (currentCell == 0) {
                    currentRow = row;
                    currentCol = col;
                    chessBoard[row][col] = ++moves;
                    found = true;
                    break;
                }
            }

            if (found) {
                break;
            }
        }
    }

    private static boolean move(int row, int col) {
        if (isInBounds(row - 2, col - 1) && !isVisited(row - 2, col - 1)) {
            chessBoard[row - 2][col - 1] = ++moves;
            currentRow = row - 2;
            currentCol = col - 1;

            return true;
        }

        if (isInBounds(row - 2, col + 1) && !isVisited(row - 2, col + 1)) {
            chessBoard[row - 2][col + 1] = ++moves;
            currentRow = row - 2;
            currentCol = col + 1;

            return true;
        }

        if (isInBounds(row - 1, col - 2) && !isVisited(row - 1, col - 2)) {
            chessBoard[row - 1][col - 2] = ++moves;
            currentRow = row - 1;
            currentCol = col - 2;

            return true;
        }

        if (isInBounds(row - 1, col + 2) && !isVisited(row - 1, col + 2)) {
            chessBoard[row - 1][col + 2] = ++moves;
            currentRow = row - 1;
            currentCol = col + 2;

            return true;
        }

        if (isInBounds(row + 1, col - 2) && !isVisited(row + 1, col - 2)) {
            chessBoard[row + 1][col - 2] = ++moves;
            currentRow = row + 1;
            currentCol = col - 2;

            return true;
        }

        if (isInBounds(row + 1, col + 2) && !isVisited(row + 1, col + 2)) {
            chessBoard[row + 1][col + 2] = ++moves;
            currentRow = row + 1;
            currentCol = col + 2;

            return true;
        }

        if (isInBounds(row + 2, col - 1) && !isVisited(row + 2, col - 1)) {
            chessBoard[row + 2][col - 1] = ++moves;
            currentRow = row + 2;
            currentCol = col - 1;

            return true;
        }

        if (isInBounds(row + 2, col + 1) && !isVisited(row + 2, col + 1)) {
            chessBoard[row + 2][col + 1] = ++moves;
            currentRow = row + 2;
            currentCol = col + 1;

            return true;
        }


        return false;
    }

    private static boolean isVisited(int row, int col) {
        if (chessBoard[row][col] != 0) {
            return true;
        }

        return false;
    }

    private static boolean isInBounds(int row, int col) {
        return row >= 0 && row < chessBoard.length && col >= 0 && col < chessBoard[row].length;
    }

    private static void printChessBoard(int size) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(chessBoard[row][col] + " ");
            }
            System.out.println();
        }
    }
}
