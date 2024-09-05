import java.util.Scanner;

public class CountX {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] charArray = scanner.nextLine().toCharArray();

        int index = 0;

        System.out.println(countOccurrencesX(charArray, index));
    }

    private static int countOccurrencesX(char[] charArray, int index) {

        if (index == charArray.length) {
            return 0;
        }

        if (charArray[index] == 'x') {
            index++;
            return 1 + countOccurrencesX(charArray, index);
        }

        index++;
        return countOccurrencesX(charArray, index);
    }
}
