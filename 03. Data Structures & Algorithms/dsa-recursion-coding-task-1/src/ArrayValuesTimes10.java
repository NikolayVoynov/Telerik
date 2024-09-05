import java.util.Arrays;
import java.util.Scanner;

public class ArrayValuesTimes10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int index = Integer.parseInt(scanner.nextLine());

        System.out.println(containsValueFollowedByValueTenTimesBigger(array, index));
    }

    private static boolean containsValueFollowedByValueTenTimesBigger(int[] array, int index) {
        if (index + 1 == array.length || array.length < 2) {
            return false;
        }

        if (array[index + 1] == array[index] * 10) {
            return true;
        }

        return containsValueFollowedByValueTenTimesBigger(array, index + 1);
    }
}
