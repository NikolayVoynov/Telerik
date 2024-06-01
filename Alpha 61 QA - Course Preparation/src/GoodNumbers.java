import java.util.Scanner;

public class GoodNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s+");

        int start = Integer.parseInt(input[0]);
        int last = Integer.parseInt(input[1]);

        int allNumbs = last - start + 1;
        int notGood = 0;

        for (int i = start; i <= last; i++) {
            String[] number = String.valueOf(i).split("");

            for (int j = 0; j < number.length; j++) {
                int digit = Integer.parseInt(number[j]);
                if (digit != 0) {
                    if (i % digit != 0) {
                        notGood++;
                        break;
                    }
                }
            }
        }

        System.out.println(allNumbs - notGood);
    }
}
