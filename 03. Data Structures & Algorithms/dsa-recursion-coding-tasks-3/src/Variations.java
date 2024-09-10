import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Variations {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lengthVariation = Integer.parseInt(scanner.nextLine());
        String[] symbols = scanner.nextLine().split(" ");
        Set<String> sortedSymbols = new TreeSet<>();
        sortedSymbols.addAll(Arrays.asList(symbols));
        String currentVariation = "";

        generateVariation(lengthVariation, sortedSymbols, currentVariation);
    }

    private static void generateVariation(int lengthVariation,
                                          Set<String> sortedSymbols,
                                          String currentVariation) {

        if (currentVariation.length() == lengthVariation) {
            System.out.println(currentVariation);
            return;
        }

        for (String symbol : sortedSymbols) {
            generateVariation(lengthVariation, sortedSymbols, currentVariation + symbol);
        }
    }
}
