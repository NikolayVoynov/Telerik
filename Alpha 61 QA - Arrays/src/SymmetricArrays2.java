import java.util.Scanner;

public class SymmetricArrays2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < count; i++) {
            String[] input = scanner.nextLine().split(" ");
            boolean isSymetric = true;

            for (int j = 0; j < input.length / 2; j++) {
                if (!input[j].equals(input[input.length - 1 - j])) {
                    isSymetric = false;
                    break;
                }
            }

            if (isSymetric) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}
