import java.util.Scanner;

public class ChangePi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        System.out.println(replacePiWithNumber(input));
    }

    private static String replacePiWithNumber(String input) {

        if (input.length() < 2) {
            return input;
        }

        if (input.startsWith("pi")) {
            return "3.14" + replacePiWithNumber(input.substring(2));
        } else {
            return input.charAt(0) + replacePiWithNumber(input.substring(1));
        }
    }
}
