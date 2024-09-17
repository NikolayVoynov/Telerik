import java.util.Arrays;
import java.util.Scanner;

public class Sequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int k = input[0];
        int n = input[1];

        int[] sequence = new int[n];

        //[0] = k
        //[1] = [0] + 1
        //[2] = 2*[0] + 1
        //[3] = [0] + 2
        //[4] = [1] + 1
        //[5] = 2*[1] + 1
        //[6] = [1] + 2

        boolean addOne = true;
        boolean multiplyByTwoAndAddOne = false;
        boolean addTwo = false;

        sequence[0] = k;
        int currentIndex = 0;
        for (int i = 1; i < n; i++) {
            if (addOne) {
                sequence[i] = sequence[currentIndex] + 1;

                addOne = false;
                multiplyByTwoAndAddOne = true;

            } else if (multiplyByTwoAndAddOne) {
                sequence[i] = 2 * sequence[currentIndex] + 1;

                multiplyByTwoAndAddOne = false;
                addTwo = true;
            } else if (addTwo) {
                sequence[i] = sequence[currentIndex] + 2;

                addTwo = false;
                addOne = true;
                currentIndex++;
            }
        }

        System.out.println(sequence[n - 1]);
    }
}
