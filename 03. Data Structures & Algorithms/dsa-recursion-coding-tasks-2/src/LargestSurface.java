import java.util.Scanner;

public class LargestSurface {
    private static int[][] matrix;
    private static boolean[][] visited;
    private static int rows;
    private static int cols;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        rows = scanner.nextInt();
        cols = scanner.nextInt();
        matrix = new int[rows][cols];
        visited = new boolean[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = scanner.nextInt();
            }
        }

        int largestArea = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!visited[row][col]) {

                    int currentArea = dfs(row, col, matrix[row][col]);

                    largestArea = Math.max(largestArea, currentArea);
                }
            }
        }

        System.out.println(largestArea);
    }


    private static int dfs(int row, int col, int value) {

        if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col] || matrix[row][col] != value) {
            return 0;
        }

        visited[row][col] = true;

        int areaSize = 1;
        areaSize += dfs(row - 1, col, value); // up
        areaSize += dfs(row + 1, col, value); // down
        areaSize += dfs(row, col - 1, value); // left
        areaSize += dfs(row, col + 1, value); // right

        return areaSize;
    }
}