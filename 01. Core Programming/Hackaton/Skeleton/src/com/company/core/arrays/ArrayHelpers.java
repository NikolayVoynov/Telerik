package com.company.core.arrays;

@SuppressWarnings({"ManualArrayCopy", "ExplicitArrayFilling"})
public class ArrayHelpers {

    /**
     * Adds `element` to the end of `source`.
     *
     * @param source  The array to add to
     * @param element The element to add
     * @return A new array, the original array with `element` at the end
     * @author Nikolay Voynov
     */
    public static int[] add(int[] source, int element) {
        int increasedLength = source.length + 1;
        int[] newArr = new int[increasedLength];

        copy(source, newArr, increasedLength);
        newArr[increasedLength - 1] = element;

        return newArr;
    }

    /**
     * Adds `element` at the start of `source`.
     *
     * @param source  The array to add to
     * @param element The element to add
     * @return A new array, the original array with `element` at head position
     * @author Nikolay Voynov
     */
    public static int[] addFirst(int[] source, int element) {
        int increasedLength = source.length + 1;
        int[] newArr = new int[increasedLength];

        copyFrom(source, 0, newArr, 1, source.length);
        newArr[0] = element;

        return newArr;
    }

    /**
     * Adds all `elements` to the end of `source`.
     *
     * @param source  The array to add to
     * @param elements The elements to add
     * @return A new array, the original array with all `elements` at the end
     * @author Nikolay Voynov
     */
    public static int[] addAll(int[] source, int... elements) {
        int increasedLength = source.length + elements.length;

        int[] newArr = new int[increasedLength];

        copy(source, newArr, source.length);
        int index = 0;
        for (int i = source.length; i < newArr.length; i++) {
            newArr[i] = elements[index];
            index++;
        }

        return newArr;
    }

    /**
     * Checks if `source` contains `element`.
     *
     * @param source  The array to check in
     * @param element The elements to check for
     * @return `true` if `source` contains `element`, otherwise, `false`
     * @author Nikolay Voynov
     */
    public static boolean contains(int[] source, int element) {
        for (int i = 0; i < source.length; i++) {
            if (source[i] == element) {
                return true;
            }
        }

        return false;
    }

    /**
     * Copies `count` elements from `sourceArray` into `destinationArray`
     *
     * @param sourceArray      The array to copy from
     * @param destinationArray The array to copy to
     * @param count            The number of elements to copy
     * @return `void`
     * @author Nikolay Voynov
     */
    public static void copy(int[] sourceArray, int[] destinationArray, int count) {
        int minLengthCopyArr = Math.min(sourceArray.length, count);

        for (int i = 0; i < minLengthCopyArr; i++) {
            destinationArray[i] = sourceArray[i];
        }
    }

    /**
     * Copies elements from `sourceArray`, starting from `sourceStartIndex` into `destinationArray`,
     * starting from `destStartIndex`, taking `count` elements.
     *
     * @param sourceArray      The array to copy from
     * @param sourceStartIndex The starting index in sourceArray
     * @param destinationArray The array to copy to
     * @param destStartIndex   The starting index in destinationArray
     * @param count            The number of elements to copy
     * @return `void`
     * @author Nikolay Voynov
     */
    public static void copyFrom(int[] sourceArray, int sourceStartIndex,
                                int[] destinationArray, int destStartIndex, int count) {
        int length = Math.min(sourceArray.length, count) + destStartIndex;

        for (int i = destStartIndex; i < length; i++) {
            destinationArray[i] = sourceArray[sourceStartIndex];
            sourceStartIndex++;
        }
    }

    /**
     * Fills `source` with `element`.
     *
     * @param source  The array to fill
     * @param element The element to fill with
     * @return `void`
     * @author Nikolay Voynov
     */
    public static void fill(int[] source, int element) {
        for (int i = 0; i < source.length; i++) {
            source[i] = element;
        }
    }

    /**
     * Finds the first index of `target` within `source`.
     *
     * @param source  The array to check in
     * @param target The element to check for
     * @return The first index of `target` within `source`, otherwise, -1
     * @author Nikolay Voynov
     */
    public static int firstIndexOf(int[] source, int target) {
        for (int i = 0; i < source.length; i++) {
            if (source[i] == target) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Inserts `element` at index `index` in `source`.
     *
     * @param source The array to insert in
     * @param index The index to insert at
     * @param element The element to insert
     * @return A new array with `element` in it
     * @author Nikolay Voynov
     */
    public static int[] insert(int[] source, int index, int element) {
        if (index == 0) {
            return addFirst(source, element);
        }

        if (index == source.length) {
            return add(source, element);
        }

        int[] newArr = new int[source.length + 1];
        copyFrom(source, 0, newArr, 0, index);

        newArr[index] = element;
        int newStartPosition = source.length - index - 1;
        copyFrom(source, newStartPosition, newArr, index + 1, source.length - 1);

        return newArr;
    }

    /**
     * Checks if `index` is a valid index in `source`.
     *
     * @param source The array to check against
     * @param index The index to check for
     * @return `true` if `index` is valid, otherwise, `false`
     * @author Nikolay Voynov
     */
    public static boolean isValidIndex(int[] source, int index) {
        return index >= 0 && index < source.length;
    }

    /**
     * Finds the last index of `target` within `source`
     *
     * @param source The array to check in
     * @param target The element to check for
     * @return The last index of `target` within `source`, otherwise, -1
     * @author Nikolay Voynov
     */
    public static int lastIndexOf(int[] source, int target) {
        int lastIndex = -1;

        for (int i = 0; i < source.length; i++) {
            if (source[i] == target) {
                lastIndex = i;
            }
        }

        return lastIndex;
    }

    /**
     * Removes all occurrences of `element` within `source`
     *
     * @param source The array to remove from
     * @param target The element to check for
     * @return A new array with all occurences of `element` removed
     * @author Nikolay Voynov
     */
    public static int[] removeAllOccurrences(int[] source, int element) {
        int countOccurrences = 0;

        for (int i = 0; i < source.length; i++) {
            if (source[i] == element) {
                countOccurrences++;
            }
        }

        int[] newArr = new int[countOccurrences];
        int index = 0;

        for (int i = 0; i < source.length; i++) {
            if (source[i] == element) {
                newArr[index] = element;
            }
        }
        return newArr;
    }

    /**
     * Reverses `arrayToReverse`
     *
     * @param arrayToReverse The array to reverse
     * @return `void`
     * @author Nikolay Voynov
     */
    public static void reverse(int[] arrayToReverse) {
        for (int i = 0; i < arrayToReverse.length / 2; i++) {
            int tempElem = arrayToReverse[i];
            arrayToReverse[i] = arrayToReverse[arrayToReverse.length - i - 1];
            arrayToReverse[arrayToReverse.length - i - 1] = tempElem;
        }
    }

    /**
     * Reverses `arrayToReverse`
     *
     * @param source The array to create the new array from
     * @param startIndex The starting index
     * @param endIndex The end index
     * @return A new array starting from `startIndex` (inclusive) and until `endIndex` (inclusive).
     * @author Nikolay Voynov
     */
    public static int[] section(int[] source, int startIndex, int endIndex) {

        int newArrLength = endIndex - startIndex + 1;
        int[] newArr = new int[newArrLength];

        copyFrom(source, startIndex, newArr, 0, newArrLength);
        return newArr;
    }
}
