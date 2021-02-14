# Course notes for Java complete masterclass on udemy

## 3. First steps

### 8 primitive types:

- boolean (Boolean)
- byte (Byte)
  - occupies 8 bits ("byte has a **width** of 8")
  - -128 - 127
  - doesn't roll over but gets converted to int when adding number and it gets out of range
  - use to document that expected value is small / in that range
- char
- short
  - width: 16 bit
  - -32768 - 32767
- int (Integer)
  - width: 32 bit
  - used by default by java for numbers
  - when adding 1 to Integer.MAX_VALUE, it overflows (**rolls over**) (to Integer.MIN_VALUE) and reverse
  - when assigning a value hiher than MAX_VALUE, a compiler error occurs
  - for better readability, underscores can be used in numbers (2_010_232 = 2010232), java ^7
- long
  - 64 bit
  - must be assigned with an "l" or "L" if its over int range: long myLongValue = 100L;
  - long Min: -9223372036854775808
  - long Max: 9223372036854775807
  - **rolls over**
  - works well with int
- float
  - 32 bit (single precission)
- double
  - 64 bit (double precission)
  

### Packages

Way to organize Java projects; companies use domain names reversed

### Casting
- Integers don't need to be explicitely cast to long
- Syntax:

      (type) value or variable;
      byte x = (byte) (6/2); // default data type is integer so 6/2 gets interpreted as an int by the compiler; as byte range is smaller than int, it needs to be explicitely cast

