import java.util.Arrays;
import java.util.Scanner;

public class CombineLists {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] arr1 = scanner.nextLine().split(",");
        String[] arr2 = scanner.nextLine().split(",");

        String[] resultArray = new String[arr1.length + arr2.length];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = (i < arr1.length ? arr1[i] : arr2[i - arr1.length]);
        }

        System.out.println(String.join(",", resultArray));
    }
}
