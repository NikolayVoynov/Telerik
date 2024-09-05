import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Fibonacci {
    private static Map<Long,Long> memoization = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long n = Long.parseLong(scanner.nextLine());

        System.out.println(fibonacci(n));
    }

    private static long fibonacci(long n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        if (memoization.containsKey(n)){
            return memoization.get(n);
        }

        long fibonacciNum =  fibonacci(n - 1) + fibonacci(n - 2);

        memoization.put(n,fibonacciNum);
        return fibonacciNum;
    }
}
