import java.util.Scanner;

public class ZigZag_NotFullyImpl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        long[][] matrix = new long[n][m];
        int currentElement = 1;

        long sum = 0;

        for (int r = 0; r < n; r++) {
            currentElement = currentElement + 3 * r;
            for (int c = 0; c < m; c++) {
                matrix[r][c] = currentElement;
                currentElement += 3;
            }
            currentElement = 1;
        }
        int currentRow = 0;
        int currentCol = 0;

        while (currentRow != n - 1 && currentCol >= 0) {

            while (currentCol < m && currentRow < n) {
                sum += matrix[currentRow++][currentCol++];
                sum += matrix[currentRow--][currentCol++];
            }

            currentRow += 2;
            currentCol -= 2;

            while (currentCol >= 0 && currentRow < n) {
                sum += matrix[currentRow--][currentCol--];

                if (currentCol >= 0) {
                    sum += matrix[currentRow++][currentCol--];
                }
            }
        }

        System.out.println(sum);
    }
}

