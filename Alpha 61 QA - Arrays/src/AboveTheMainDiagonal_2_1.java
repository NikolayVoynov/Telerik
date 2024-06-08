import java.util.Scanner;

public class AboveTheMainDiagonal_2_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        long value = 0;
        for (int i = 1; i < n; i++)
            for (int j = i; j < n; j++)
                value += (long) Math.pow(2, i + j - 1);
        System.out.println(value);
    }
}
