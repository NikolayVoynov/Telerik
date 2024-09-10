import java.util.*;

public class Cipher {
    private static Set<String> resolvedMessagesSet = new TreeSet<>();
    private static String[] lettersValues;
    private static String[] codesKeys;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String secretMessage = scanner.nextLine();
        String cipher = scanner.nextLine();

        codesKeys = Arrays.stream(cipher.split("\\p{L}"))
                .filter(e -> !e.isEmpty())
                .toArray(String[]::new);

        lettersValues = Arrays.stream(cipher.split("\\d"))
                .filter(e -> !e.isEmpty())
                .toArray(String[]::new);

        resolveMessage(secretMessage, "");

        System.out.println(resolvedMessagesSet.size());
        for (String message : resolvedMessagesSet) {
            System.out.println(message);
        }
    }

    private static void resolveMessage(String message,
                                       String currentMessage) {

        if (message.isEmpty()) {
            resolvedMessagesSet.add(currentMessage);
            return;
        }

        for (int i = 0; i < codesKeys.length; i++) {
            String codeKey = codesKeys[i];
            String letterValue = lettersValues[i];

            if (message.startsWith(codeKey)) {
                resolveMessage(message.substring(codeKey.length()), currentMessage + letterValue);
            }
        }
    }
}
