import java.util.*;

public class SmallWorld {

    static int n;
    static int m;
    static int[][] matrix;
    static boolean[][] visited;

    static int[] rowDirection = {-1, 1, 0, 0};
    static int[] colDirection = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] inputSize = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        n = inputSize[0];
        m = inputSize[1];

        matrix = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = line.charAt(j) - '0';
            }
        }

        List<Integer> listOfConquestsSizes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (matrix[i][j] == 1 && !visited[i][j]) {
                    int conquestSize = dfs(i, j);
                    listOfConquestsSizes.add(conquestSize);
                }
            }
        }

        listOfConquestsSizes.sort(Collections.reverseOrder());

        for (int size : listOfConquestsSizes) {
            System.out.println(size);
        }
    }

    static int dfs(int row, int col) {
        visited[row][col] = true;
        int size = 1;


        for (int move = 0; move < 4; move++) {
            int newRow = row + rowDirection[move];
            int newCol = col + colDirection[move];

            if (isValid(newRow, newCol) && matrix[newRow][newCol] == 1 && !visited[newRow][newCol]) {
                size += dfs(newRow, newCol);
            }
        }

        return size;
    }

    static boolean isValid(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < m;
    }
}