import java.util.Arrays;
import java.util.Scanner;

public class ArraysContaining11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int index = Integer.parseInt(scanner.nextLine());

        System.out.println(countOccurrencesEleven(array, index));
    }

    private static int countOccurrencesEleven(int[] array, int index) {

        if (index == array.length) {
            return 0;
        }

        if (array[index] == 11) {
            index++;
            return 1 + countOccurrencesEleven(array, index);
        }

        index++;
        return countOccurrencesEleven(array, index);
    }
}
