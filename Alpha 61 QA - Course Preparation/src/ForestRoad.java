import java.util.Scanner;

public class ForestRoad {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int width = Integer.parseInt(scanner.nextLine());
        int height = 2 * width - 1;

        String[][] forest = new String[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                forest[i][j] = ".";
            }
        }

        int c = 0;

        for (int r = 0; r <= height / 2 + 1; r++) {
            if (c < width) {
                forest[r][c] = "*";
                c++;
            }
        }

        c = width - 2;

        for (int r = height / 2 + 1; r < height; r++) {
            if (c >= 0) {
                forest[r][c] = "*";
                c--;
            }
        }

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                System.out.print(forest[row][col]);
            }
            System.out.println();
        }
    }
}
