import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JustCount {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Map<Character, Integer> symbowCountMap = new HashMap<>();
        Map<Character, Integer> lowercaseCountMap = new HashMap<>();
        Map<Character, Integer> uppercaseCountMap = new HashMap<>();

        for (char c : input.toCharArray()) {
            if (Character.isLowerCase(c)) {
                lowercaseCountMap.put(c, lowercaseCountMap.getOrDefault(c, 0) + 1);
            } else if (Character.isUpperCase(c)) {
                uppercaseCountMap.put(c, uppercaseCountMap.getOrDefault(c, 0) + 1);
            } else {
                symbowCountMap.put(c, symbowCountMap.getOrDefault(c, 0) + 1);
            }
        }

        char mostFrequentLowercase = findMostFrequent(lowercaseCountMap);
        char mostFrequentUppercase = findMostFrequent(uppercaseCountMap);
        char mostFrequentSymbol = findMostFrequent(symbowCountMap);

        if (mostFrequentSymbol != '-') {
            System.out.println(mostFrequentSymbol + " " + symbowCountMap.get(mostFrequentSymbol));
        } else {
            System.out.println("-");
        }

        if (mostFrequentLowercase != '-') {
            System.out.println(mostFrequentLowercase + " " + lowercaseCountMap.get(mostFrequentLowercase));
        } else {
            System.out.println("-");
        }

        if (mostFrequentUppercase != '-') {
            System.out.println(mostFrequentUppercase + " " + uppercaseCountMap.get(mostFrequentUppercase));
        } else {
            System.out.println("-");
        }
    }

    private static char findMostFrequent(Map<Character, Integer> countMap) {
        char mostFrequent = '-';
        int maxCount = 0;

        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            char character = entry.getKey();
            int count = entry.getValue();

            if (count > maxCount || (count == maxCount && character < mostFrequent)) {
                mostFrequent = character;
                maxCount = count;
            }
        }

        return mostFrequent;
    }
}
