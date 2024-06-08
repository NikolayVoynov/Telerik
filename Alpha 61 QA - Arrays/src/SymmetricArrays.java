import java.util.Scanner;

public class SymmetricArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] arr = scanner.nextLine().split("\\s+");
            int[] arrNumbs = new int[arr.length];
            boolean isSymmetric = true;

            for (int j = 0; j < arr.length; j++) {
                arrNumbs[j] = Integer.parseInt(arr[j]);
            }

            for (int k = 0; k < arr.length / 2; k++) {
                int firstNum = arrNumbs[k];
                int secondNum = arrNumbs[arr.length - 1 - k];

                if (firstNum != secondNum) {
                    isSymmetric = false;
                    break;
                }
            }

            if (isSymmetric) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}
