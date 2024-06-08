import java.util.Scanner;

public class ReverseArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] arr = scanner.nextLine().split("\\s+");
        String[] revArr = new String[arr.length];
        int index = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            revArr[index] = arr[i];
            index++;
        }

        System.out.println(String.join(", ", revArr));
    }
}
