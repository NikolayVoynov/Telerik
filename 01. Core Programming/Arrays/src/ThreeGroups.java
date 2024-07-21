import java.util.ArrayList;
import java.util.Scanner;

public class ThreeGroups {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] line = scanner.nextLine().split("\\s+");
        ArrayList<Integer> remainder0 = new ArrayList<>();
        ArrayList<Integer> remainder1 = new ArrayList<>();
        ArrayList<Integer> remainder2 = new ArrayList<>();

        int[] numbers = new int[line.length];

        for (int i = 0; i < line.length; i++) {
            numbers[i] = Integer.parseInt(line[i]);
        }

        for (int i = 0; i < numbers.length; i++) {
            int remainder = numbers[i] % 3;

            switch (remainder) {
                case 0:
                    remainder0.add(numbers[i]);
                    break;
                case 1:
                    remainder1.add(numbers[i]);
                    break;
                case 2:
                    remainder2.add(numbers[i]);
                    break;
            }
        }

        for (Integer num : remainder0) {
            System.out.print(num + " ");
        }
        System.out.println();

        for (Integer num : remainder1) {
            System.out.print(num + " ");
        }
        System.out.println();

        for (Integer num : remainder2) {
            System.out.print(num + " ");
        }
    }
}
