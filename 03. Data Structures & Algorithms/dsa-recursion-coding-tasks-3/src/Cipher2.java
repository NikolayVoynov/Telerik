import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class Cipher2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner((System.in));
        String message = scanner.nextLine();

        String input = scanner.nextLine();

        HashMap<String, String> map = new HashMap<>();

        int indexOfLetter = 0;
        String val = null;
        for (int i = 0; i < input.length(); i++) {
            if (Character.isAlphabetic(input.charAt(i))) {
                if (i == 0) {
                    val = input.substring(i, i + 1);
                    continue;
                }
                map.put( input.substring(indexOfLetter + 1, i), val);
                val = input.substring(i, i + 1);
                indexOfLetter = i;
            }
        }
        map.put(input.substring(indexOfLetter + 1, input.length()), val);

        String res = "";
        dfs(message, res, map);
        System.out.println(toPrint.size());
        toPrint.forEach(System.out::println);
    }

    private static TreeSet<String> toPrint = new TreeSet<>();
    private static void dfs(String inp, String result, HashMap<String, String> map) {
        int length = inp.length();
        if(length == 0){
            toPrint.add(result);
        }

        for (int i = 0; i < length; i++) {
            String current = inp.substring(0, i + 1);
            if (map.containsKey(current)) {
                dfs(inp.substring(i + 1), result + map.get(current), map);
            }
        }
    }
}
