import java.util.Scanner;

public class ElectronicMessage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        int maxLength = 0;
        int counter = 0;

        for (int i = 0; i < input.length(); i++) {
            int ASCII = input.charAt(i);

            if (ASCII == 46) {
                break;
            }

            if (ASCII == 32 || (ASCII > 47 && ASCII < 58) || (ASCII > 64 && ASCII < 91) || (ASCII > 96 && ASCII < 123)) {
                if (counter > maxLength) {
                    maxLength = counter;
                }
                counter = 0;
                continue;
            } else {
                counter++;
            }
        }

        System.out.println(maxLength);
    }
}
