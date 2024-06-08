import java.util.Scanner;

public class AboveTheMainDiagonal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        long[][] matrix = new long[size][size];

        long sum = 0;

        for (int r = 0; r < size; r++) {
            int power = r;
            for (int c = 0; c < size; c++) {
                matrix[r][c] = (long) Math.pow(2, power);
                power++;
            }
        }

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (r != c) {
                    sum += matrix[r][c];
                }
            }
        }

        System.out.println(sum /2);

//        for (int row = 0; row < size; row++) {
//            for (int col = 0; col < size; col++) {
//                System.out.print(matrix[row][col] + " ");
//            }
//            System.out.println();
//        }
    }
}
