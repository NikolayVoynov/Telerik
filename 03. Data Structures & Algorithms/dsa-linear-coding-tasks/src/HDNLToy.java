import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class HDNLToy {

    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int inputLinesNumber = Integer.parseInt(scanner.nextLine());

        String[] tags = new String[inputLinesNumber];
        int[] digits = new int[inputLinesNumber];
        Stack<Tag> stack = new Stack<>();

        for (int i = 0; i < inputLinesNumber; i++) {
            String input = scanner.nextLine();
            int digit = Integer.parseInt(input.split("")[1]);
            tags[i] = input;
            digits[i] = digit;
        }

        int currentValue = digits[0];
        int indentLevel = 0;
        printOpenTag(indentLevel, tags[0]);
        stack.push(new Tag(tags[0], digits[0], indentLevel));

        for (int i = 1; i < inputLinesNumber; i++) {
            if (digits[i] > currentValue) {
                currentValue = digits[i];
                indentLevel++;
                printOpenTag(indentLevel, tags[i]);
                stack.push(new Tag(tags[i], digits[i], indentLevel));
            } else {
                currentValue = digits[i];
                while (!stack.isEmpty() && currentValue <= stack.peek().digit) {
                    Tag newTag = stack.pop();
                    printCloseTag(newTag.indentLevel, newTag.tag);
                    if (currentValue < newTag.digit) {
                        indentLevel--;
                        indentLevel = Math.max(indentLevel, 0);
                    }
                }
                printOpenTag(indentLevel, tags[i]);
                stack.push(new Tag(tags[i], digits[i], indentLevel));
            }
        }
        while (!stack.isEmpty()) {
            Tag newTag = stack.pop();
            printCloseTag(newTag.indentLevel, newTag.tag);
        }
        System.out.print(result);
    }

    public static class Tag {
        public String tag;
        public int digit;
        public int indentLevel;

        public Tag(String tag, int value, int nestLvl) {
            this.tag = tag;
            this.digit = value;
            this.indentLevel = nestLvl;
        }
    }

    private static void printCloseTag(int indentLevel, String tag) {
        String spaces = String.join("", Collections.nCopies(indentLevel, " "));
        result.append(spaces).append("<").append("/").append(tag).append(">").append("\n");
    }

    private static void printOpenTag(int indentLevel, String tag) {
        String spaces = String.join("", Collections.nCopies(indentLevel, " "));
        result.append(spaces).append("<").append(tag).append(">").append("\n");
    }
}
