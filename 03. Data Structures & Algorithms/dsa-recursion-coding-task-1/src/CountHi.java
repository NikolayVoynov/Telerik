import java.util.Scanner;

public class CountHi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] charArray = scanner.nextLine().toCharArray();

        int index = 0;

        System.out.println(countOccurrencesHi(charArray, index));
    }

    private static int countOccurrencesHi(char[] charArray, int index) {

        if (index + 1 == charArray.length) {
            return 0;
        }

        if (charArray[index] == 'h' && charArray[index+1] == 'i') {
            index++;
            return 1 + countOccurrencesHi(charArray, index);
        }

        index++;
        return countOccurrencesHi(charArray, index);
    }
}
