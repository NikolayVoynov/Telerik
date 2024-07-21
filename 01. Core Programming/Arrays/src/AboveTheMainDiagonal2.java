import java.util.Scanner;

public class AboveTheMainDiagonal2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        long sum = 0;

        long[][] matrix = new long[size][size];

        for (int row = 0; row < size; row++) {
            int power = row;
            for (int col = 0; col < size; col++) {
                matrix[row][col] = (long) Math.pow(2, power);
                power++;
            }
        }

        int increaseCol = 0;
        for (int r = 0; r < size; r++) {
            for (int c = increaseCol; c < size; c++) {
                sum += matrix[r][c];
            }
            increaseCol++;
        }

        System.out.println(sum);
    }
}
