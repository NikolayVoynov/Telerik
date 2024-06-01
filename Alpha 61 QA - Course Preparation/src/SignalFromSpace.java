import java.util.Scanner;

public class SignalFromSpace {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("");
        String previous = "";
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length; i++) {
            if (!previous.equals(input[i])){
                result.append(input[i]);
            }
            previous = input[i];
        }

        System.out.println(result.toString());
    }
}
