import java.util.Scanner;

public class SymmetricArrays {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] currentArr = scanner.nextLine().split("\\s+");
            boolean isSymmetric = true;

            for (int j = 0; j < currentArr.length / 2; j++) {
                if (!currentArr[j].equals(currentArr[currentArr.length - 1 - j])) {
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
