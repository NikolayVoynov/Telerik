import java.util.Scanner;

public class MirrorCodes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s+");
        String first = input[0];
        String second = input[1];
        StringBuilder firstRev = new StringBuilder();
        StringBuilder secondRev = new StringBuilder();

        for (int i = first.length() - 1; i >= 0; i--) {
            firstRev.append(first.charAt(i));
        }

        for (int i = second.length() - 1; i >= 0; i--) {
            secondRev.append(second.charAt(i));
        }

        int rev1 = Integer.parseInt(firstRev.toString());
        int rev2 = Integer.parseInt(secondRev.toString());

        if (rev1 > rev2) {
            System.out.println(rev1);
        } else {
            System.out.println(rev2);
        }
    }
}
