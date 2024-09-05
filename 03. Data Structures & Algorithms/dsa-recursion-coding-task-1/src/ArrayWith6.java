import java.util.Arrays;
import java.util.Scanner;

public class ArrayWith6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int index = Integer.parseInt(scanner.nextLine());

        System.out.println(containsSix(array, index));
    }

    private static boolean containsSix(int[] array, int index) {

        if (index == array.length) {
            return false;
        }

        if (array[index] == 6) {
            return true;
        }

        index++;
        return containsSix(array, index);
    }
}
