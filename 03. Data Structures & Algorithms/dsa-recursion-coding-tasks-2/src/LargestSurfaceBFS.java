import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class LargestSurfaceBFS {
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

        boolean[][] visited = new boolean[rows][cols];
        int maxSize = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    int size = bfs(matrix, visited, i, j, matrix[i][j]);
                    maxSize = Math.max(maxSize, size);
                }
            }
        }

        System.out.println(maxSize);
    }

    private static int bfs(int[][] matrix, boolean[][] visited, int row, int col, int target) {
        //The neighbour cells are visited in the order: down, up, right, left
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        int[] currentCell = new int[]{row, col};
        queue.offer(currentCell);
        visited[row][col] = true;
        int size = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int[] direction : directions) {
                int nextRow = current[0] + direction[0];
                int nextCol = current[1] + direction[1];

                if (isInMaze(matrix.length, matrix[0].length, nextRow, nextCol)
                        && !visited[nextRow][nextCol]
                        && matrix[nextRow][nextCol] == target) {
                    queue.offer(new int[]{nextRow, nextCol});
                    visited[nextRow][nextCol] = true;
                    size++;
                }
            }
        }
        return size;
    }

    private static boolean isInMaze(int rowLength, int colLength, int row, int col) {
        return row >= 0 && row < rowLength && col >= 0 && col < colLength;
    }
}
