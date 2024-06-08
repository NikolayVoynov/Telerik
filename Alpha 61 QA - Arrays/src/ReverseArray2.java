import java.util.Scanner;

public class ReverseArray2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");
        String[] reverse = new String[input.length];

        for (int i = 0; i < input.length; i++) {
            reverse[i] = input[input.length - 1 - i];
        }

        System.out.println(String.join(", ", reverse));
    }
}
