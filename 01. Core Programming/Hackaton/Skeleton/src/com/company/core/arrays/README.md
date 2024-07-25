# Array methods

### Table of Contents
- [`add(int[], int)`](#addint-int)
- [`addFirst(int[], int)`](#addfirstint-int)
- [`addAll(int[], int)`](#addallint-int)
- [`contains(int[], int)`](#containsint-int)
- [`copy(int[], int[] int)`](#copyint-int-int)
- [`copyFrom(int[], int, int[], int, int)`](#copyfromint-int-int-int-int)
- [`fill(int[], int)`](#fillint-int)
- [`firstIndexOf(int[], int)`](#firstindexofint-int)
- [`insert(int[], int, int)`](#insertint-int-int)
- [`isValidIndex(int[], int)`](#isvalidindexint-int)
- [`lastIndexOf(int[], int)`](#lastindexofint-int)
- [`removeAllOccurrences(int[], int)`](#removealloccurrencesint-int)
- [`reverse(int[])`](#reverseint)
- [`section(int[], int, int)`](#sectionint-int-int)

***

## `add(int[], int)`

```java
add(int[] source, int element);
```

### Description

*Adds `element` to the end of `source`.*

### Parameters

**`source`** int[] - *The array to add to*

**`element`** int - *The element to add*

### Returns

`int[]` - *A new array, the original array with `element` at the end*

#### Example

```java
add(new int[]{1, 2, 3}, 4);

//returns: {1, 2, 3, 4}
```

***

## `addFirst(int[], int)`

```java
addFirst(int[] source, int element);
```

### Description

*Adds `element` at the start of `source`*

### Parameters

**`source`** int[] - *The array to add to*

**`element`** int - *The element to add*

### Returns

`int[]` - *A new array, the original array with `element` at head position*

#### Example

```java
add(new int[]{1, 2, 3}, 4);

//returns: {4, 1, 2, 3}
```

***

## `addAll(int[], int)`

```java
addAll(int[] source, int element);
```

### Description

*Adds all `elements` to the end of `source`.*

### Parameters

**`source`** int[] - *The array to add to*

**`element`** int - *The elements to add*

### Returns

`int[]` - *A new array, the original array with all `elements` at the end*

#### Example

```java
addAll(new int[]{1, 2, 3}, 4, 5, 6);

//returns: {1, 2, 3, 4, 5, 6}
```

***

## `contains(int[], int)`

```java
contains(int[] source, int element);
```

### Description

*Checks if `source` contains `element`*

### Parameters

**`source`** int[] - *The array to check in*

**`element`** int - *The elements to check for*

### Returns

`boolean` - *`true` if `source` contains `element`, otherwise, `false`*

#### Example

```java
contains(new int[]{1, 2, 3}, 2);

//returns: true
```

***

## `copy(int[], int[] int)`

```java
copy(int[] source, int[] destinationArray, int element);
```

### Description

*Copies `count` elements from `sourceArray` into `destinationArray`*

### Parameters

**`source`** int[] - *The array to copy from*

**`destinationArray`** int[] - *The array to copy to*

**`count`** int - *The number of elements to copy*

### Returns

`void`

#### Example

```java
copy(new int[]{1, 2, 3}, new int[2], 2);

//destinationArray == {1, 2}
```

***

## `copyFrom(int[], int, int[], int, int)`

```java
copyFrom(int[] sourceArray, int sourceStartIndex, int[] destinationArray, int destStartIndex, int count);
```

### Description

*Copies elements from `sourceArray`, starting from `sourceStartIndex` into `destinationArray`, starting from `destStartIndex`, taking `count` elements.*

### Parameters

**`sourceArray`** int[] - *The array to copy from*

**`sourceStartIndex`** int[] - *The starting index in sourceArray*

**`destinationArray`** int[] - *The array to copy to*

**`destStartIndex`** int[] - *The starting index in destinationArray*

**`count`** int - *The number of elements to copy*

### Returns

`void`

#### Example

```java
int[] array = {1, 2, 3, 4, 5};
sourceStartIndex = 0;
int[] destinationArray = new int[4];
destStartIndex = 1;
count = 2;
copyFrom(array, sourceStartIndex, destinationArray, destStartIndex, count);

//destinationArray == {0, 1, 2, 0}
```

***

## `fill(int[], int)`

```java
fill(int[] source, int element);
```

### Description

*Fills `source` with `element`.*

### Parameters

**`source`** int[] - *The array to fill*

**`element`** int - *The element to fill with*

### Returns

`void`

#### Example

```java
fill(new int[3], int 1);

//source == {1, 1, 1}
```

***

## `firstIndexOf(int[], int)`

```java
firstIndexOf(int[] source, int target);
```

### Description

*Finds the first index of `target` within `source`.*

### Parameters

**`source`** int[] - *The array to check in*

**`target`** int - *The element to check for*

### Returns

`int` - The first index of `target` within `source`, otherwise, -1

#### Example

```java
firstIndexOf(new int[]{1, 2, 2, 3}, 2);

//returns 1
```

***

## `insert(int[], int, int)`

```java
insert(int[] source, int index, int element);
```

### Description

*Inserts `element` at index `index` in `source`.*

### Parameters

**`source`** int[] - *The array to insert in*

**`index`** int - *The index to insert at*

**`element`** int - *The element to insert*

### Returns

`int[]` - *A new array with `element` in it*

#### Example

```java
insert(new int[]{1, 2, 4, 5}, 2, 3);

//returns {1, 2, 3, 4, 5}
```

***

## `isValidIndex(int[], int)`

```java
isValidIndex(int[] source, int index);
```

### Description

*Checks if `index` is a valid index in `source`.*

### Parameters

**`source`** int[] - *The array to check against*

**`index`** int - *The index to check for*

### Returns

`boolean` - *`true` if `index` is valid, otherwise, `false`*

#### Example

```java
isValidIndex(new int[]{1, 2}, 2)

//returns false
```

***

## `lastIndexOf(int[], int)`

```java
lastIndexOf(int[] source, int target);
```

### Description

*Finds the last index of `target` within `source`*

### Parameters

**`source`** int[] - *The array to check in*

**`target`** int - *The element to check for*

### Returns

`int` - *The last index of `target` within `source`, otherwise, -1*

#### Example

```java
lastIndexOf(new int[]{1, 2, 3, 4, 2}, 2);

//returns 4
```

***

## `removeAllOccurrences(int[], int)`

```java
removeAllOccurrences(int[] source, int element);
```

### Description

*Removes all occurrences of `element` within `source`*

### Parameters

**`source`** int[] - *The array to remove from*

**`target`** int - *The element to check for*

### Returns

`int[]` - *A new array with all occurences of `element` removed*

#### Example

```java
removeAllOccurrences(new int[]{1, 2, 3, 4, 2}, 2);

//returns {1, 3, 4}
```

***

## `reverse(int[])`

```java
reverse(int[] arrayToReverse);
```

### Description

*Reverses `arrayToReverse`*

### Parameters

**`arrayToReverse`** int[] - *The array to reverse*

### Returns

`void`

#### Example

```java
reverse(new int[]{1, 2, 3, 4, 2})

//arrayToReverse == {2, 4, 3, 2, 1}
```

***

## `section(int[], int, int)`

```java
section(int[] source, int startIndex, int endIndex);
```

### Description

*Returns a new array, from `source`, starting from `startIndex` and until `endIndex`*

### Parameters

**`source`** int[] - *The array to create the new array from*

**`startIndex`** int - *The starting index*

**`endIndex`** int - *The end index*

### Returns

`int[]` - *A new array starting from `startIndex` (inclusive) and until `endIndex` (inclusive).*

#### Example

```java
section(new int[]{1, 2, 3, 4, 2}, 0, 3)

//returns {1, 2, 3, 4}
```

***
