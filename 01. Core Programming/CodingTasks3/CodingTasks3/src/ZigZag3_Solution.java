import java.util.Scanner;

public class ZigZag3_Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        long sum = 0L;


        for (int row = 0; row < n; row++) {
            int startValue;
            if (row % 2 == 0) {
                startValue = 0;
            } else {
                startValue = 1;
            }
            for (int col = startValue; col < m; col += 2) {
                if ((col != 0) && (col != m - 1) && (row != 0) && (row != n - 1)) {
                    sum = sum + (((row + col) * 3 + 1) * 2);
                } else {
                    sum += ((row + col) * 3 + 1);
                }
            }
        }

        System.out.println(sum);
    }
}


