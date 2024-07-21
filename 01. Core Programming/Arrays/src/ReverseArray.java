import java.util.Scanner;

public class ReverseArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputArr = scanner.nextLine().split("\\s+");
        String[] reverseArr = new String[inputArr.length];

        for (int i = 0; i < inputArr.length; i++) {
            reverseArr[i] = inputArr[inputArr.length - 1 - i];
        }

        System.out.println(String.join(", ", reverseArr));
    }
}