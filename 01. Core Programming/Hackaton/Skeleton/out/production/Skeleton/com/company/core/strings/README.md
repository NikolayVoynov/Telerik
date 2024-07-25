# String methods

### Table of Contents

- [`abbreviate(String, int)`](#abbreviatestring-int)
- [`capitalize(String)`](#capitalizestring)
- [`concat(String, String)`](#concatstring-string)
- [`contains(String, char)`](#containsstring-char)
- [`endsWith(String, char)`](#endswithstring-char)
- [`firstIndexOf(String, char)`](#firstindexofstring-char)
- [`lastIndexOf(String, char)`](#lastindexofstring-char)
- [`pad(String, int, char)`](#padstring-int-char)
- [`padEnd(String, int, char)`](#padendstring-int-char)
- [`padStart(String, int, char)`](#padstartstring-int-char)
- [`repeat(String, int)`](#repeatstring-int)
- [`reverse(String)`](#reversestring)
- [`section(String, int, int)`](#sectionstring-int-int)
- [`startsWith(String, char)`](#startswithstring-char)

***

## `abbreviate(String, int)`

```java
abbreviate(String source, int maxLength);
```

### Description

*Abbreviates a string using ellipses.*

### Parameters

**`source`** String - *The string to modify*

**`maxLength`** int - *Maximum length of the resulting string*

### Returns

`String` - *The abbreviated String*

#### Example

```java
abbreviate("New Company", 9)

//returns: New Compa...
```

***

## `capitalize(String)`

```java
capitalize(String source);
```

### Description

*Capitalizes a string changing the first character to title case. No other characters are changed. If source is empty returns empty string.*

### Parameters

**`source`** String - *The string to modify*

### Returns

`String` - *The capitalized string or empty string if `source` is empty*

#### Example

```java
capitalize("java")

//returns: Java
```
***

## `concat(String, String)`

```java
concat(String string1, String string2);
```

### Description

*Concatenates `string1` to the end of `string2`.*

### Parameters

**`string1`** String - *The left part of the new string*

**`string2`** String - *The right part of the new string*

### Returns

`String` - *A string that represents the concatenation of string1 followed by string2's characters*

#### Example

```java
concat("New ", "Company")

//returns: New Company
```

***

## `contains(String, char)`

```java
contains(String source, char symbol);
```

### Description

*Checks if `source` contains a `symbol`.*

### Parameters

**`source`** String - *The string to check*

**`symbol`** char - *The character to check for*

### Returns

`boolean` - *`true` if `symbol` is within `source` or `false` if not*

#### Example

```java
contains("Java", 'v')

//returns: true
```

***

## `endsWith(String, char)`

```java
endsWith(String source, char target);
```

### Description

*Checks if the string `source` ends with the given character.*

### Parameters

**`source`** String - *The string to check*

**`target`** char - *The character to check for*

### Returns

`boolean` - *`true` if the string ends with `target`, else `false`*

#### Example

```java
endsWith("Java", 'a')

//returns: true
```

***

## `firstIndexOf(String, char)`

```java
firstIndexOf(String source, char target);
```

### Description

*Finds the first index of `target` within `source`.*

### Parameters

**`source`** String - *The string to check*

**`target`** char - *The character to check for*

### Returns

`int` - *The first index of `target` within `source`, otherwise, -1*

#### Example

```java
firstIndexOf("Java", 'a')

//returns: 1
```

***

## `lastIndexOf(String, char)`

```java
lastIndexOf(String source, char target);
```

### Description

*Finds the last index of `target` within `source`.*

### Parameters

**`source`** String - *The string to check*

**`target`** char - *The character to check for*

### Returns

`int` - *The last index `symbol` within `source` or -1 if no match*

#### Example

```java
lastIndexOf("Java", 'a')

//returns: 3
```

***

## `pad(String, int, char)`

```java
pad(String source, int length, char paddingSymbol);
```

### Description

*Pads string on the left and right sides if it's shorter than length.*

### Parameters

**`source`** String - *The string to pad*

**`length`** int - *The length of the string to achieve*

**`target`** char - *The character used as padding*

### Returns

`String`
*The padded string*

#### Example

```java
pad("Java", 8, '*');

//returns: **Java**
```

***

## `padEnd(String, int, char)`

```java
padEnd(String source, int length, char paddingSymbol);
```

### Description

*Pads `source` on the right side with `paddingSymbol` enough times to reach length `length`.*

### Parameters

**`source`** String - *The string to pad*

**`length`** int - *The length of the string to achieve*

**`target`** char - *The character used as padding*

### Returns

`String` - *The padded string*

#### Example

```java
padEnd("Java", 6, '*');

//returns: Java**
```

***

## `padStart(String, int, char)`

```java
padStart(String source, int length, char paddingSymbol);
```

### Description

*Pads `source` on the left side with `paddingSymbol` enough times to reach length `length`.*

### Parameters

**`source`** String - *The string to pad*

**`length`** int - *The length of the string to achieve*

**`target`** char - *The character used as padding*

### Returns

`String` - *The padded string*

#### Example

```java
padStart("Java", 6, '*');

//returns: **Java
```

***

## `repeat(String, int)`

```java
repeat(String source, int times);
```

### Description

*Repeats the given string `times` times.*

### Parameters

**`source`** String - *The string to repeat*

**`times`** int - *The number of times to repeat the string*

### Returns

`String` - *The repeated string*

#### Example

```java
repeat("Java", 3);

//returns: JavaJavaJava
```

***

## `reverse(String)`

```java
reverse(String source);
```

### Description

*Reverses `source` so that the first element becomes the last, the second element becomes the second to last, and so on.*

### Parameters

**`source`** String - *The string to reverse*

### Returns

`String` - *The reversed string*

#### Example

```java
reverse("Java");

//returns: avaJ
```

***

## `section(String, int, int)`

```java
section(String source, int start, int end);
```

### Description

*Returns a new string, starting from `start` and ending at `end`.*

### Parameters

**`source`** String - *The string to reverse*

**`start`** int - *The starting position in `source` (inclusive)*

**`end`** int - *The end position in `source` (inclusive)*

### Returns

`String` - *A new string, formed by the characters in `source`, starting from `start` to `end`*

#### Example

```java
section("**New Company**", 2, 12);

//returns: New Company
```

***

## `startsWith(String, char)`

```java
startsWith(String source, char target);
```

### Description

*Checks if the string `source` starts with the given character.*

### Parameters

**`source`** String - *The string to inspect*

**`target`** char - *The character to search for*

### Returns

`boolean` - *`true` if string starts with target, otherwise `false`*

#### Example

```java
reverse("Java", 'J');
//returns: true

reverse("Java", 'j');
//returns: false
```

***
