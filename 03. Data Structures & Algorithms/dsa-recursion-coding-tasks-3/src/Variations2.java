import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Variations2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstLine = scanner.nextLine();
        int length = Integer.parseInt(firstLine);

        String secondLine = scanner.nextLine();
        String[] symbols = secondLine.split(" ");

        List<String> list = new ArrayList<>();

        variations(symbols, "", symbols.length, length, list);
        Collections.sort(list);

        for (int i = 0; i < list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }
    static void variations(String[] inputSymbolsArray, String prefix, int symbolsCount, int combinationLength, List<String> list) {
        if (combinationLength == 0) {
            list.add(prefix);
            return;
        }
        for (int i = 0; i < symbolsCount; i++) {
            String output = prefix + inputSymbolsArray[i];
            variations(inputSymbolsArray, output, symbolsCount, combinationLength - 1, list);
        }
    }
}
