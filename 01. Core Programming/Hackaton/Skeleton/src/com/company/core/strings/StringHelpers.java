package com.company.core.strings;

@SuppressWarnings("StringConcatenationInLoop")
public class StringHelpers {

    /**
     * Abbreviates a string using ellipses.
     *
     * @param source    The string to modify
     * @param maxLength Maximum length of the resulting string
     * @return The abbreviated String.
     * @author Nikolay Voynov
     */
    public static String abbreviate(String source, int maxLength) {
        if (maxLength >= source.length()) {
            return source;
        }

        String first = source.substring(0, maxLength);
        String second = "...";

        return concat(first, second);
    }

    /**
     * Capitalizes a string changing the first character to title case. No other characters are changed.
     * If source is empty returns empty string.
     *
     * @param source The string to modify
     * @return The capitalized string or empty string if `source` is empty.
     * @author Nikolay Voynov
     */
    public static String capitalize(String source) {
        if (checkIfIsEmpty(source)) {
            return source;
        }

        char capitalLetter = source.charAt(0);
        String secondStr = source.substring(1);

        return Character.toUpperCase(capitalLetter) + secondStr;
    }

    /**
     * Concatenates `string1` to the end of `string2`.
     *
     * @param string1 The left part of the new string
     * @param string2 The right part of the new string
     * @return A string that represents the concatenation of string1 followed by string2's characters.
     * @author Nikolay Voynov
     */
    public static String concat(String string1, String string2) {
        return string1 + string2;
    }

    /**
     * Checks if `source` contains a `symbol`.
     *
     * @param source The string to check
     * @param symbol The character to check for
     * @return `true` if `symbol` is within `source` or `false` if not.
     * @author Nikolay Voynov
     */
    public static boolean contains(String source, char symbol) {
        char[] charArr = source.toCharArray();

        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == symbol) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the string `source` ends with the given character.
     *
     * @param source The string to check
     * @param target The character to check for
     * @return `true` if the string ends with `target`, else `false`.
     * @author Nikolay Voynov
     */
    public static boolean endsWith(String source, char target) {
        if (checkIfIsEmpty(source)) {
            return false;
        }

        return source.charAt(source.length() - 1) == target;
    }

    /**
     * Finds the first index of `target` within `source`.
     *
     * @param source The string to check
     * @param target The character to check for
     * @return The first index of `target` within `source`, otherwise, -1
     * @author Nikolay Voynov
     */
    public static int firstIndexOf(String source, char target) {
        char[] charArr = source.toCharArray();

        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == target) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Finds the last index of `target` within `source`.
     *
     * @param source The string to check
     * @param target The character to check for
     * @return The last index `symbol` within `source` or -1 if no match
     * @author Nikolay Voynov
     */
    public static int lastIndexOf(String source, char symbol) {
        char[] charArr = source.toCharArray();
        int lastIndex = -1;

        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == symbol) {
                lastIndex = i;
            }
        }

        return lastIndex;
    }

    /**
     * Pads string on the left and right sides if it's shorter than length.
     *
     * @param source The string to pad
     * @param length The length of the string to achieve
     * @param target The character used as padding
     * @return The padded string
     * @author Nikolay Voynov
     */
    public static String pad(String source, int length, char paddingSymbol) {
        if (checkIfIsEmpty(source) || source.length() >= length) {
            return source;
        }

        String result = "";

        int repetitionOfSymbolOnEachSide = (length - source.length()) / 2;

        String leftSide = padStart(source, repetitionOfSymbolOnEachSide + source.length(), paddingSymbol);
        String rightSide = repeat(String.valueOf(paddingSymbol), repetitionOfSymbolOnEachSide);

        result += concat(leftSide, rightSide);

        return result;
    }

    /**
     * Pads `source` on the right side with `paddingSymbol` enough times to reach length `length`.
     *
     * @param source The string to pad
     * @param length The length of the string to achieve
     * @param paddingSymbol The character used as padding
     * @return The padded string
     * @author Nikolay Voynov
     */
    public static String padEnd(String source, int length, char paddingSymbol) {
        if (checkIfIsEmpty(source) || source.length() >= length) {
            return source;
        }

        String result = "";
        result += source;

        while (result.length() < length) {
            result += paddingSymbol;
        }

        return result;
    }

    /**
     * Pads `source` on the left side with `paddingSymbol` enough times to reach length `length`.
     *
     * @param source The string to pad
     * @param length The length of the string to achieve
     * @param paddingSymbol The character used as padding
     * @return The padded string
     * @author Nikolay Voynov
     */
    public static String padStart(String source, int length, char paddingSymbol) {
        if (checkIfIsEmpty(source) || source.length() >= length) {
            return source;
        }

        String result = "";

        while (result.length() < length - source.length()) {
            result += paddingSymbol;
        }

        result += source;

        return result;
    }

    /**
     * Repeats the given string `times` times.
     *
     * @param source The string to repeat
     * @param times The number of times to repeat the string
     * @return The repeated string
     * @author Nikolay Voynov
     */
    public static String repeat(String source, int times) {
        if (times == 0) {
            return source;
        }

        String result = "";

        for (int i = 0; i < times; i++) {
            result += source;
        }

        return result;
    }

    /**
     * Reverses `source` so that the first element becomes the last, the second element becomes the second to last, and so on.
     *
     * @param source The string to reverse
     * @return The reversed string
     * @author Nikolay Voynov
     */
    public static String reverse(String source) {
        char[] charArr = source.toCharArray();

        String result = "";

        for (int i = charArr.length - 1; i >= 0; i--) {
            result += charArr[i];
        }

        return result;
    }

    /**
     * Returns a new string, starting from `start` and ending at `end`.
     *
     * @param source The string to be modified
     * @param start The starting position in `source` (inclusive)
     * @param end The end position in `source` (inclusive)
     * @return A new string, formed by the characters in `source`, starting from `start` to `end`
     * @author Nikolay Voynov
     */
    public static String section(String source, int start, int end) {
        String result = "";

        for (int i = start; i <= end; i++) {
            result += source.charAt(i);
        }

        return result;
    }

    /**
     * Returns a new string, starting from `start` and ending at `end`.
     *
     * @param source The string to inspect
     * @param target The character to search for
     * @return `true` if string starts with target, otherwise `false`
     * @author Nikolay Voynov
     */
    public static boolean startsWith(String source, char target) {
        return source.charAt(0) == target;
    }

    private static boolean checkIfIsEmpty(String source) {
        return source.length() == 0;
    }

}
