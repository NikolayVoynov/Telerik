import java.util.Scanner;

public class LargestSurfaceDFS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        int maxSequence = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]){
                    int currentMaxSequence = dfs(matrix, visited, matrix[i][j], i, j);
                    maxSequence = Math.max(maxSequence, currentMaxSequence);
                }
            }
        }

        System.out.println(maxSequence);

    }

    private static int dfs(int[][] matrix, boolean[][] visited, int target, int currenRow, int currenCol) {
        //Base scenario 1? -> out of bounds check
        if (isOutOfMatrix(matrix.length, matrix[0].length, currenRow, currenCol)){
            return 0;
        }

        //Base scenario 2? -> is the cell visited
        if (visited[currenRow][currenCol]){
            return 0;
        }

        //Base scenario 3? -> the current cell is not the target
        if (matrix[currenRow][currenCol] != target){
            return 0;
        }

        visited[currenRow][currenCol] = true;

        int goLeft = dfs(matrix, visited, target, currenRow, currenCol - 1);
        int goRight = dfs(matrix, visited, target, currenRow, currenCol + 1);
        int goUp = dfs(matrix, visited, target, currenRow - 1, currenCol);
        int goDown = dfs(matrix, visited, target, currenRow + 1, currenCol);

        int total = goLeft + goRight + goUp + goDown + 1;

        return total;
    }

    private static boolean isOutOfMatrix(int rowLength, int colLength, int currenRow, int currenCol) {
        return currenRow < 0 || currenRow >= rowLength || currenCol < 0 || currenCol >= colLength;
    }
}
