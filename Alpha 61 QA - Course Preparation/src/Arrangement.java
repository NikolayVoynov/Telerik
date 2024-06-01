import java.util.Scanner;

public class Arrangement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputNum = scanner.nextLine().split("\\s+");

        int h1 = Integer.parseInt(inputNum[0]);
        int h2 = Integer.parseInt(inputNum[1]);
        int h3 = Integer.parseInt(inputNum[2]);
        int h4 = Integer.parseInt(inputNum[3]);

        if (h1 < h2 && h2 < h3 && h3 < h4) {
            System.out.println("Ascending");
        } else if (h1 > h2 && h2 > h3 && h3 > h4) {
            System.out.println("Descending");
        } else {
            System.out.println("Mixed");
        }
    }
}
