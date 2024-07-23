import java.util.Scanner;

public class LongestBlockInString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        char[] characters = input.toCharArray();
        char mostFrequentCharacter = characters[0];
        int maxLengthSequence = 1;
        int currentLengthSequence = 1;

        for (int i = 0; i < characters.length - 1; i++) {
            if (characters[i] == characters[i + 1]) {
                currentLengthSequence++;
            } else {
                currentLengthSequence = 1;
            }

            if (currentLengthSequence > maxLengthSequence) {
                maxLengthSequence = currentLengthSequence;
                mostFrequentCharacter = characters[i];
            }
        }

        for (int i = 0; i < maxLengthSequence; i++) {
            System.out.print(mostFrequentCharacter);
        }
    }
}

