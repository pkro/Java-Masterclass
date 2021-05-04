# Course notes for Java complete masterclass on udemy

## 3. First steps

- basic command line compilation (from src directory): `javac -cp . de/pkro/Main.java`
  - cp: classpath, current directory so packages used in main can be found
  
- **Primitive types are passed by value, Objects by reference; the original object can be changed (if it's not immutable) when passed as a parameter to a method**

### 8 primitive types

- **boolean** (Boolean)
- byte
  - occupies 8 bits ("byte has a **width** of 8")
  - -128 - 127
  - doesn't roll over but gets converted to int when adding number and it gets out of range
  - use to document that expected value is small / in that range
- char
  - must use single quotes: `char myChar = 'D';`
  - width: 16 bit (unicode)
  - unicode assignments are valid: `char arrow = '\U+2190';`
  - is an int internally and can be used in calculations and loops (`for(char c = 'A'; c <= 'Z'; c++)`)
- short
  - width: 16 bit
  - -32768 - 32767
- **int**
  - width: 32 bit
  - used by default by java for numbers
  - when adding 1 to Integer.MAX_VALUE, it overflows (**rolls over**) (to Integer.MIN_VALUE) and reverse
  - when assigning a value hiher than MAX_VALUE, a compiler error occurs
  - for better readability, underscores can be used in numbers (2_010_232 = 2010232), java ^7
- long
  - 64 bit (integer)
  - must be assigned with an "l" or "L" if its over int range: long myLongValue = 100L;
  - long Min: -9223372036854775808
  - long Max: 9223372036854775807
  - **rolls over**
  - works well with int
- float
  - must be defined as literal with f suffix if number literal is a floating point (like *long*): `float x = 1.33f;` or cast directly from double `float x = (float) 1.33;`
  - 32 bit (single precission)
  - float min: 1.4E-45
  - float max: 3.4028235E38
- **double**
  - 64 bit (double precision)
  - default for floating-point numbers
  - Faster to process than double in almost all cases
  - can be defined with d suffix for literal numbers (double d = 5d;)
  - double min: 4.9E-324
  - double max: 1.7976931348623157E308

### Strings

- Class (not a primitive)
- concatenation with + works without explicit casting (`"dfsdfds" + 5;` is fine);
- "+" concatenation is inefficient, use StringBuffer
- immutable

### Sidenotes

- constants in Java are defines as **static** (can be used without creating class instance) **final** (can't change): `private static final double KG_FACTOR = 0.45359237d;`; Must be defined in *class*, not in method
- for precise fp calculations, **BigDecimal** should be used
- Packages: way to organize Java projects; companies use domain names reversed
- declarations can be separated by comma: `int x, y;` or `int x=0, y=0;`

### Casting

- Integers don't need to be explicitly cast to long
- Syntax:

      (type) value or variable;
      byte x = (byte) (6/2); // default data type is integer so 6/2 gets interpreted as an int by the compiler; as byte range is smaller than int, it needs to be explicitely cast

## Section 4 OperatorsExpressions, Statements, Code blocks

- use camel case, even for abreviations (correct: XmlHttpRequest, incorrect: XMLHTTPRequest)
- methods and variables start with lowercase, classes with uppercase

### Operators

- left / right associative as usual; "=" -> right associative, '+' or '-' left associative
- type comparison: **instanceof**
- precedence: as usual, <https://introcs.cs.princeton.edu/java/11precedence/>
- ++ / -- prefix / postfix as usual

### Statements

- syntactic entity in a programming language that may be evaluated to determine its value
- A statement is a complete unit of execution (`int x = 5;`)

3 types:

- Declaration statement (`int x = 5;`)
- Expression statement (expressions can become expression statements by adding a semicolon at the end)
  - Assignment expression (`x=5;`)
  - increment / decrement (`x++;`)
  - Method calls (`System.out.println("Test")`)
  - Object creation expressions (`Car ownersCar = new Car();`)
- Control flow statements

### Whitespace / indentation

- Nothing new

### Code blocks

- nothing new, new codeblock = new namespace with visibility of enclosing namespace

### Methods (basic)

- non-static methods can't be called on a non-static context, i.e. without object instantiation
- nothing noteworthy otherwise at this point

### DiffMerge Tool

- what it says; use something better.

### Methods overloading

- overloaded methods must have a differen parameter signature, a different return type doesn't suffice

### Sidenotes

- `return;` is valid for a void method (NOT `return void;`)

## Section 5 Control Flow

- switch, for, while, do while: nothing unexpected; String type switch added Java 7+

### String / number conversions

- Integer.parseInt() / Double.parseDouble etc.; throws exception if not a valid (integer|double|...) format
- concatenating int to string with "+" automatically converts the int

### User Input (command line)

    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    scanner.close();

- also nextInt, nextShort, hasNextInt etc. among others
- for multiple inputs that are terminated by enter (enter gets interpreted as the next input), use `scanner.nextLine();` before reading another input:

      System.out.println("Enter your birthyear: ");
      int birthYear = scanner.nextInt();
      System.out.println("Enter your name: ");
      scanner.nextLine();
      String name = scanner.nextLine();

- user input type check pattern:

      System.out.println("Enter your choice: ");
      int choice = -1;
      while(choice == -1) {
        if(scanner.hasNextInt()) {
          choice = scanner.nextInt();
        } else {
          System.out.println("Please enter only numbers.");
        }
        scanner.nextLine();
      }

### Sidenotes

- Example date / formatting / conversion: Condensed getting current year:
  
  `int currentYear = Integer.parseInt(new SimpleDateFormat("YYYY").format(new Date(System.currentTimeMillis())));`

## Section 6 OOP part 1

### Classes

- class files are created in namespace folder, e.g. /de/pkro

      [optional access modifier] class ClassName
      [public|private|protected] class ClassName

- instance variables (=fields) -> state of class (or rather it's derived object)
- refered to inside function with this.varName (if parameter naming conflict, otherwise just varName)

- Object instantiation:

  [Classname] varName = new [Classname]([params])

### Constructor

- method with only access modifier (always public), *no return type* and method name = class name
  - can actually be set to private to make it impossible to instantiate the class, example: Math
- to overload a constructor with a constructor that assigns default values, call the constructor that accepts values with "**this**":

      // all parameters
      public BankAccount(long accountNumber, double balance, String customerName, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        [...]
      }

      // no parameters
      public BankAccount() {
          this(12345, 0, "Default name", "default email", "default phone");
      }

      // some parameters
      public BankAccount(String customerName, String email) {
          this(12345, 0, customerName, email, "default phone");
      }
  
- generally it's regarded as better to assign the values directly (`this.name = name;`) in the constructor as opposed to using the setters (`setName(name);`) in the constructor; reasons:
  - initial values are guaranteed to be initialized
  - not all variables that are necessary for validation of another value might have been initialized

### Inheritance

- a class can have only one superclass (but can implement multiple interfaces, see section 9 below)
- all classes inherit from `Object`
- `public class XXX extends YYY {[...]}`
- constructor call with super

      public class Dog extends Animal {
          public Dog(String name, int brain, int body, int size, int weight) {
              super(name, brain, body, size, weight);
          }
      }

- override methods with **@Overri (optional**, hint for compiler / IDE to catch errors when efining overridden methods):

      @Override
      public void eat() {
          super.eat();
          chew();
      }

- if a method has a different signature (but the same name) as the one in the parent class, it's **overloaded** and **not overridden**
- to call methods from the superclass, it's better practice to call them without super in case they are later overrridden (unless the superclass method version should explicitely be called)
- **this** is used to call current class members; usually used in constructors and setters, sometimes in getters
- **super** to call parent class; commonly used in method overriding
- both can't be used in a static context
- **super()**: Java puts a default call to super() in the constructor to call the argument-less constructor of the parent class if we don't add it ourselves
- the **super()** call must be the first statement in the constructor if it's done explicitely
- Even abstract classes have a constructor
- Don't put @Override over constructor
- A constructor can have a call to super() or this() but not both

### Overloading / Overriding

#### Overloading

- Overloading is sometimes refered to as compile time polymorphism (though it isn't polymorphism in the inheritance sense)
- both static and instance methods can be overloaded
- Overloaded = same method name but different parameters, **may or may not** have different return types / access modifiers / exceptions
- usually methods get overloaded in a single class, but a class overloads the parent class' method (no @Override necessary then)

#### Overriding

- aka "runtime polymorphism"
- means defining a method in the child class with the same name and signature as one in the parent class
- recommended to put @Override above method definition to let IDE / compiler catch errors
- only instance methods can be overridden
- return type can (only) be a subclass of the return type in the parent class
- can't have a lower access modifier (e.g. if parent method: protected, child method can be public but not private)
- Constructors and private methods can't be overridden
- Final methods can't be overridden
- subclass can still call overridden method version from parent class by using super.methodName

![Same thing as infographic](images/overload_override.png "Same thing as infographic")

### Static vs Instance methods

#### Static methods

- can't use this in static methods
- methods that don't use instance variables should be declared static

#### Instance methods

- can only be used in class instance (method)
- can access instance as well as static methods/variables directly

### Static vs instance variables

#### static variables ("static member variables")

- declared with `static`
- shared with all class instances
- **if changed, value in all other instances will change too**
  
      public class StaticVarTest {
        public static int maxSpeed = 100;
      }
      // in main
      StaticVarTest a = new StaticVarTest();
      StaticVarTest b = new StaticVarTest();
      a.maxSpeed = 5;
      System.out.println(b.maxSpeed); // 5

- used for example to instantiate a Scanner that can be used in all methods

#### Instance variables

- every instance has its own copy / value

### Sidenotes

- String comparison with .equals method (`firstName.equals("pkro")`)
- Check if String is empty / uninitialized .isEmpty()
- default toString method outputs classname and object id
- Reference assignments as usual

## Section 7 OOP part 2

### Composition

- Inheritance: "is-a" relationship between subclass and parent class
- Composition: "has-a" relationship: Monitor class has Resolution class
- basically using other classes / types in a class and construction a parent class from other class components

      Dimensions dimensions = new Dimensions(20, 20, 5);
      Case theCase = new Case("22b", "Dell", "240", dimensions);
      Monitor theMonitor = new Monitor("27incher", "Iiyama", 27, new Resolution(2540,1440));
      Motherboard motherboard = new Motherboard("apc200","apc", 4, 2, "1.5");
      PC pc = new PC(theCase,theMonitor,motherboard);

#### how to access methods of components?

- example: pressPowerButton is defined in Case, but we want to access it from the pc object.
- solution 1: using the getters (duh!):

      pc.getCase().pushPowerButton();
      pc.getMonitor().drawPixelAt(20,30,"green");

- solution 2: making getters in the class that uses the components private (or removing them alltogether) and wrapping the component's methods:

In PC class:

      public void powerUp() {
        theCase.pressPowerButton();
        drawLogo();
      }

      public void drawLogo() {
          monitor.drawPixelAt(40, 30, "purple");
          monitor.drawPixelAt(40, 30, "green");
      }

- solution 3: both of the above

#### Where to use inheritance and where composition?

- rule of thumb: composition first as it doesn't create unwieldy class hierarchies
- works well with the functional paradigm
- easier to test / write tests for individual classes

### encapsulation

- encapsulate member variable access in methods
- protects from variable name changes affecting classes using it
- enables checks before accessing members

### Polymorphism

- basically method overloading. Subclass IS-A Parentclass, but Parentclass IS_NOT_A Subclass
- Might want to add more explanations here but this seems intuitive and already covered during method overriding section
- [There obviously is more to it](https://www.infoworld.com/article/3033445/java-101-polymorphism-in-java.html)

### Sidenotes

- Strg-Shift-Space shows method parameters when typing (can be enabled to be always shown)
- "sout"+tab = System.out.println
- Strg-F12 shows method / class implementation (where cursor is)
- classes can be declared inline in the file of another class (e.g. class Main), but then "public" keyword isn't allowed as the class can't be accessed from outside the file.

## Section 8

### Reference Types vs Value Types

- nothing surprising, primitives are passed / assigned by value, Objects (such as Arrays) by reference
- methods can dereference (=re-initialize) array references passed in parameters:
- parameters are just copies of the pointers to the object

      public static void doSomething(int[] array) {
        array[2] = 2; // modifies array referenced by "array"
        // overwrite / dereference reference LOCALLY
        array = new int[] {1,2,3}; // creates new array and references it by "array"
      }

### Arrays

- nothing surprising index-wise or standard properties like .length
- can hold all types
- typed (no mixed content)
- Array initialization

      // with sizing (all array elements are initialized to zero (0)):
      int[] myArray = new int[10];
      System.out.println(Arrays.toString((new int[5])));
      // -> [0, 0, 0, 0, 0]
      
      // without values
      String[] days;
      
      // with values:
      String[] a = new String[] {"a","b","c"};
      
      // with values in an anonymous array:
      String[] days = {"Monday","Tuesday"};
      
      // gets compiled but flagged by IDE (local c style array), why?
      String days[];
      String days[] = {"Monday", "Tuesday"};

- Resizing arrays:
  - By hand: create new reference of original, dereference original variable and copy content of newly created reference in the dereferenced one
  - Use array list (see next point)

### List / ArrayList

- can only be used with classes, not primitives (use wrapper Integer etc. for that, see autoboxing below)
- ArrayList inherits from list (or rather AbstractList interface)
- when adding values to ArrayList, capacity grows automatically
- Ordered collection
- Instantiate with Type in pointy brackets and constructor call with brackets
- append with .add (single item) or .addAll (from collection / list)
  - java needs to move all elements after the inserted one up an index, this is slower than using a LinkedList where only 2 addresses need to be amended - see LinkedList section below
- insert with add(index, item)
- instead of .length property as in arrays, use .size() method
- access items by index with .get(index)
- update with .set(index, newItem)
- remove items with remove (overloaded to accept object to be removed or index (and probably more).
- more methods such as copying just see example code below
- also a lot of useful .stream() methods  
- making a copy of a list using a list constructor returns a *shallow* copy: `List<Theater.Seat> seatsCopy = new ArrayList<Theater.Seat>(theater.seats);`
  
      ArrayList<String> groceryList = new ArrayList<String>();
      // explicit type specification in constructor can be left out:
      ArrayList<String> anotherList = new ArrayList<>();
      groceryList.add("Wurst");
      groceryList.add("Bier");
      groceryList.add("Coke");
      System.out.println(groceryList.toString()); // [Wurst, Bier, Coke]
      groceryList.set(2, "Whisky"); // [Wurst, Bier, Whisky]
      groceryList.remove(1); // [Wurst, Whisky]
      groceryList.remove("Wurst"); // [Whisky]
      groceryList.contains("Whisky"); // true; "contains" returns just indexOf(obj) >= 0
      groceryList.add(0, "Milk"); // [Milk, Whisky]
      //ways to copy with addAll or ArrayList constructor:
      ArrayList<String> newArrayList = new ArrayList<String>();
      newArrayList.addAll(groceryList);
      ArrayList<String> nextArray = newArrayList<String>(groceryList);
      
      // copy to array
      String[] myArray = new String[groceryList.size()];
      myArray = groceryList.getGroceryList().toArray(myArray); // needs array as param again

      // foreach iteration also possible as in arrays:
      for(String item: groceryList) {
        System.out.println(item);
      }

### Autoboxing / Unboxing

- primitive type ArrayLists must use wrapper class (`<Integer>, <Float> etc`)
- explicit not necessary when adding values (autoboxing)
- automatically converted back on access (unboxing)
- both at compile time

### LinkedList

- works like every linked list (val, next) + tons of methods
- is implemented in java as doubly linked list to allow for moving back and forward
- performant for inserting items
- instantiation like ArrayList, *boxing applies
- Storage in memory:
  - Primitives: Java calculates memory position by max amount of bytes the given *primitive* type requires and stores them directly:
    ![Linked list with primitives](images/linkedlistprimititves.png "Linked list with primitives")
    - this makes it possible to quickly look up items by index as a formula to calculate the memory address can be used: base address + (primitive type byte size) * index
  - For objects (such as strings), the *address* of the object is saved in the linkedlist:
    ![Linked list with objects](images/linkedlistobjects.png "Linked list with objects")
    - same easy index calculation possible despite variable length
  - how is that something worth mentioning specifically for linked lists? Isn't this the basic distinction of how primitives and objects are stored / passed?
- Iterate using enhanced for (recommended) or explicitely with iterator

      LinkedList<String> placesToVisit = new LinkedList<>();
      placesToVisit.add("Sydney");
      placesToVisit.add("Brisbane");
      placesToVisit.add(1, "Perth"); // insert at 1 and move other entries "up"
      placesToVisit.remove(1);
      // iterator
      Iterator<String> i = placesToVisit.iterator();
      while (i.hasNext()) {
        System.out.println(i.next());
      }

      // enhanced for
      for (String s : placesToVisit) {
        System.out.println(s);
      }

- use ListIterator to be able to move back with iterator and added functionality

      // this method should rather work on a copy of the list to avoid side effects,
      // but for brevity's sake this alters the passed LinkedList itself
      private static boolean addInOrder(LinkedList<String> linkedList, String city) {
        ListIterator<String> stringListIterator = linkedList.listIterator();
  
        while(stringListIterator.hasNext()) {
            int comparison = stringListIterator.next().compareTo(city);
            if(comparison==0) {
                // equal, don't add
                System.out.println(city + " is already included");
                return false;
            } else if(comparison > 0) {
                // new city should appear before the current one
                stringListIterator.previous(); // go back
                stringListIterator.add(city);
                return true;
            }
        }
        stringListIterator.add(city); // the end
        return true;
      }

- iterator.getFirst() gets first entry from iterator without moving it like news()
- java ListIterator is implemented in a way to avoid recursive loops in structures, so the iterator is acutally "in between" two list nodes and not "On" one; (one) solution is to keep track of the direction the iterator is moving and doing an additional "next()" or "previous()" to move back and forth with the iterator
- Arrays are collection based and not a valid argument for .addAll or in the List constructor; use Array.asList as a bridge:
  
      // with explicit array creation
      list.addAll(Arrays.asList(new String[]{name, Integer.toString(hitPoints), Integer.toString(strength), weapon}));
      // without (asList is overloaded):
      list.addAll(Arrays.asList(name, Integer.toString(hitPoints), Integer.toString(strength), weapon));

### Sidenotes

- "enhanced for loop" (basically foreach?):
  
      for(int i=0; i<arr.length; i++) { System.out.println(arr[i]); }
      // is the same as (ACCESS only, changing "value" doesn't change the array)
      // no index access possible like in most other languages foreach construct
      for (int value : arr) {
            System.out.println(value);
      }

- Copying array
  - Arrays.copyOf(array, array.length)
  - System.arraycopy
  - arrayObj.clone()
  - may more
  - by hand
- Array**s**.stream() has A LOT of useful / functional functions such as map, skip etc.
- skipping first value: `String[] days = Arrays.stream(javaDays).skip(1).toArray(String[]::new);`
- Intellij:
  - alt-j for multi selection (ctrl-d in vscode)
  - ctrl-shift-enter completes statement, adds ; at end and goes to next line
  - shift-enter to insert and go to next line without breaking current line
  - Strg-Alt-M to extract selected code to method
  - select, copy, select other, right click + "compare with clipboard" = easy partial diff
  - pasting code in markdown loses formatting, use "paste as plain text" from context menu
  
- reminder iterator protocol: the first call to iterator.next() actually goes to the first entry, NOT to the second

## Section 9

### Interfaces

- define behavior of class without implementations, just define the methods
- don't have / define constructors
- commitment that the class interfaces (method return types, signatures etc) will not change
- public is redundant for interface methods and can/should be left out
- use good interface names, e.g.:
  - ISaveable (don't do the prefix anymore as IDEs show if a file is an interface these days)
  - Saveable
  - CanSave
  
**I**Telephone.java:

      public interface ITelephone {
        // these methods must be implemented by classes implementing it
        public void powerOn();
        public void dial(int phoneNumber);
        public void answer();
        public boolean callPhone(int phoneNumber);
        public boolean isRinging();
      }

DeskPhone.java:

      public class DeskPhone implements ITelephone {
        int myNumber;
        //...
    
    
        public DeskPhone(int myNumber) {
            this.myNumber = myNumber;
            // ...
        }
    
        @Override
        public void powerOn() {
            isPowered = true;
        }
        @Override
        public void dial(int phoneNumber) {
            System.out.println("Dialing " + phoneNumber);
        }
    
        @Override
        public void answer() {
              //...
        }
    
        @Override
        public boolean callPhone(int phoneNumber) {
          //...
        }
    
        @Override
        public boolean isRinging() {
            return isRinging;
        }
      }

- Interface *can* be used as type:

      ITelephone timsPhone = new DeskPhone(123456);
      DeskPhone anotherPhone = new DeskPhone(34455);

- NOT valid (unless implementing an inner / inline class):

      ITelephone timsPhone = new ITelephone(123456);

- example java library: LinkedList, ArrayList, Vector and other list types use List interface, so by declaring List for object and parameter types the actual list implementation can be changed easily
- Lists should generally be declared with the generic interface List to make implementations more flexible (can use any subclass of List), even omitting type declaration:

      // probably too narrowly defined types:
      public interface ISaveable {
        ArrayList<String> write();
        void read(ArrayList<String> savedValues);
      }

      // probably better to allow flexible use and implementation:
      public interface ISaveable {
        List write();
        void read(List savedValues);
      }

- If the class has a method not defined in the interface, and the object was declared using the Interface type, the object must be cast to the class type to call the method:

      ISaveable werewolf = new Werewolf();
      werewolf.save(); // ok as save() is defined in interface
      werewolf.changeToSpookyForm(); // NOT ok as changeToSpookyForm is werewolf class specific
      ((Werewolf) werewolf).changeToSpookyForm(); // OK
  
- Interface or inheritance from superclass?
  - Depends: mobile phone is a computer that can also be used as a phone, so it *can* do the things a telephone does but much more, so an interface is the best choice here
  - In java a class can have only **one** superclass, but multiple interfaces, so, again, a mobile phone could inherit from ITelephone and IComputer
  - "Animal" example:
    - Bird extends Animal implements IFly implements IWalk
    - Dog extends Animal implements IWalk

### Inner classes

#### non static nested class

      public class Gearbox {
      private ArrayList<Gear> gears;
      private int gearNumber = 0;
      // ...
          public Gearbox(int maxGears) {
              this.gears = new ArrayList<>();
              
              // ...
          }
  
          private class Gear {
            private int gearNumber = 5; // shadows parent classes gearNumber variable
            gearNumber = Gearbox.gearNumber; // this.gearNumber is now 0
              // ...
          }
      }

- use when class doesn't make sense without the context of the outer class (in the example, Gear will only ever be instantiated by Gearbox)
- can improve encapsulation  
- outer class variables can be accessed directly if no naming conflict exists
  - *best to use unique names to avoid confusion and hard to find bugs caused by variable shadowing*
- explicit instantiation of inner class (only possible with instance of enclosing class) with .new:

      Gearbox mcLaren = new Gearbox(6);
      Gearbox.Gear first = mcLaren.new Gear(1, 12.3);
      // new Gearbox.Gear(...) is not possible, neither is new mcLaren.Gear(...)

  - this is normally not necessary as it contradicts making it an inner class in the first place
  - best practice would be to make inner class private to avoid external instantiation
  
#### inner class implementing an inner interface from another class

- an inner interface gives a template to create a class that is only of concern in the context of the outer class
- example Section 9 inner classes button:

      // Button.java
      public class Button {
        private String title;
        private OnClickListener onClickListener;
        // ...
        public interface OnClickListener {
            public void onClick(String title);
        }
      }
  
      // Main.java
      public class Main {
        private static Button btnPrint = new Button("yay button print");
        // ...
        public static void main(String[] args) {
            // ...
            // local class implements Buttons inner OnClickListener interface
            class ClickListener implements Button.OnClickListener {
              public ClickListener() {
                  System.out.println("I've been attached");
              }
    
              @Override
              public void onClick(String title) {
                  System.out.println(title + " was clicked");
              }
            }
          }
        }
        
        btnPrint.setOnClickListener(new ClickListener());;
      }

#### anonymous classes

- have to be declared and instantiated at the same time, similar to javascript anonymous classes
- very common for attaching event handlers

      anotherButton.setOnClickListener(new Button.OnClickListener() {
        @Override
        public void onClick(String title) {
        System.out.println("Anonymous event listener class onClick event");
        }
      });

#### abstract classes

- keyword abstract
- class that defines method definitions like interfaces but implements *some* methods
- can't be used on its own, needs to be inherited from and forces ancestor class to implement the missing methods
- private vars / methods not accessible in child class (like always), use protected if needed

      public abstract class Animal {
      private String name;
      
          public Animal(String name) {
              this.name = name;
          }
      
          public abstract void eat(); // needs implementation
          public abstract void breathe(); // needs implementation
      
          public String getName() {
              return name;
          }
      }

      public class Dog extends Animal {
        // constructor needs to be implemented
        public Dog(String name) {
          super(name);
        }
        @Override
        public void eat() {
          System.out.println("Dog "+getName()+" eating");
        }
      
        @Override
        public void breathe() {
          System.out.println("Dog "+getName()+" breathing");
        }
      }

- Abstract classes can inherit from abstract classes:

      // Bird.java 
      public abstract class Bird extends Animal {
          public Bird(String name) {
            super(name);
          }
      
          @Override
          public void eat() {
              System.out.println("Bird "+getName()+" eating");
          }
      
          @Override
          public void breathe() {
              System.out.println("Bird "+getName()+" breathing");
          }
          
          // for examples sake - this should actually better be in an interface as bats and flies can fly too
          public abstract void fly();
      }

      // Parrot.java
      public class Parrot extends Bird {
        public Parrot(String name) {
          super(name);
        }
      
        @Override
        public void fly() {
            System.out.println("Parrot can't fly");
        }
      }

- As with interfaces, the parent abstract class can be used as a type:

      Bird parrot = new Parrot("sparrow");

#### Interface vs abstract class

- Interface:
  - has-a relationship (player has a save method)
  - just declaration, no implementation **since Java 8 also `default` methods with implementations**
  - can only have public static final variables
  - all methods in interfaces are automatically public and abstract **since Java 9 also private methods (commonly used when 2 default methods in an interface share common code)**
  - can only have abstract methods
  - A class can implement multiple interfaces
  - decouples "what" from "how"
  - Use when:
    - designing the program (rather than the implementation)
    - expect unrelated classes to implement the interface (many things can be "saveable")
    - good example: Collections API, JDBC API
  
- Abstract class:
  - is-a relationship (player is a GameActor)
  - can have member variables, constructors
  - can have any visibility (public, private, protected)
  - can have both abstract and implemented methods
  - A class can inherit only from one abstract class
  - if the sublcass doesn't implement all abstract methods, it must be an abstract class itself
  - Use when:
    - share code / member variables among several closely related classes
    - subclass is expected to have many common methods / members or requires access modifiers other than public
  
- both:
  - can't be instantiated by themselves
  
### Sidenotes

- Intellij: set up emmet like abreviations under settings->editor->live templates, e.g. scn -> Scanner scanner = new Scanner(System.in);
- Intellij: ctrl-d to duplicate selected text
- Intellij: alt-6 show problems
- Intellij: enable multiple windows: File -> Settings -> Appearance and Behavior -> System Settings

## Section 10 generics (since java 1.5)

- Use to catch errors at compile time as opposed to hard to catch runtime errors
- ArrayList can be defined without type (then the default type is "Object" which can hold anything)
- this can lead to runtime errors if incompatible types are cast:

      public static void main(String[] args) {
        ArrayList items = new ArrayList();
        items.add(1);
        items.add("Jack");
        items.add(3);
        for (Object i: n) {
          System.out.println((Integer) i*2); // runtime error on second item
        }
      }

- intellij warns of using raw types
- use <> to indicate type to catch runtime errors:

      // ArrayList<Integer> items = new ArrayList<Integer>();
      // best way (Java 7+, "diamond operator"): 
      ArrayList<Integer> items = new ArrayList<>();
      items.add(1);
      items.add("Jack"); // compile error, can be caught by IDE

      for (Object i: n) {
        System.out.println(i*2); // casting not necessary anymore
      }

### Adding generics to own class

Before - this class allows to add any subclass of player regardless what sports team it is:

    public class Team {
      private String name;
      ArrayList<Player> members = new ArrayList<>();
  
      public Team(String name) {
          this.name = name;
      }
  
      public boolean addPlayer(Player player) {
          // ...
      }

      public void matchResult(Team opponent, int ourScore, int theirScore) {
        // ...
      }
    }
    
    

    Team adelaide = new Team("Adelaide crows");
    adelaide.addPlayer(new FootballPlayer("Joe"));
    adelaide.addPlayer(new BaseballPlayer("pat"));

After - class must be initialized with the correct player type:

    public class Team<T> {
      private String name;
      ArrayList<T> members = new ArrayList<>();
  
      public Team(String name) {
          this.name = name;
      }
  
      public boolean addPlayer(T player) {
          // ...
      }
      
      public void matchResult(Team<T> opponent, int ourScore, int theirScore) {
        // ...
      }
    }

    Team<FootballPlayer> adelaide = new Team<>("Adelaide crows");
    adelaide.addPlayer(new FootballPlayer("Joe"));
    adelaide.addPlayer(new BaseballPlayer("pat")); // compile error

`<T>` is a placeholder for the concrete type given on initialisation.

Problem: this compiles fine but leads to runtime errors when initialising with a non-compatible type (such as String) and trying to cast a non-player type to Player;

    Team<String> adelaide = new Team<>("Works fine...");
    adelaide.addPlayer(new String("Throws error at runtime if cast to player"));

Solution: restrict type of class by using **bounded type parameters** using `extends`:

    public class Team<T extends Player> { //... }
    Team<String> noworky = new Team<>("compile error");

Now the upper bound of Team is the player class.

- type given on initialisation can be class or interface
- Interfaces can specify type parameters as well
- allows multiple types (bounds) (1 class and multiple interfaces):

      // Player = class, Coach, Manager = Interfaces
      public class Team<T extends Player & Coach & Manager>

- implementing an interface with type:

      public class Team<T extends Player> implements Comparable<Team<T>>

- multiple type generic Types are possible in class definition to enforce initialisation with specific combinations:

      public class Team<U extends Player, T extends Team> {...}
      League<FootballPlayer, Team<FootballPlayer>> league = new League<>("only footballers");

      // or just (easier):
      public class Team<T extends Team> {...}
      League<Team<BaseballPlayer>> baseballLeague = new League<>("the baseballers");

### Sidenotes

- Collections.sort uses the compareTo method implemented from the Comparable interface
  - sorts in-place
  - descending: `Collections.sort(teams, Collections.reverseOrder());`
- String repeat method (Java 11): `System.out.println(name + "\n" + "=".repeat(name.length()));`
- Pre- Java 11: `repeated = new String(new char[n]).replace("\0", s);`
- Apache commons for a lot of String, array and more utilities: [**https://commons.apache.org/**](https://commons.apache.org/)

## Section 11

### Naming conventions

- **Packages**: always lower case, unique, internet domain name reversed as prefix (de.pkro.mypackage), no dashes / starting numbers (replace / prepend with _), start with_ if it clashes with java keyword ,e.g. 1-world.com -> com._1_world
- **Class names**: CamelCase, UcFirst, should be nouns
- **Interfaces**: UcFirst
  - Consider what object implementing the interface will become / be able to do:
    - List
    - Comparable
    - Serializable
    - etc.
- **Method names**: mixedCase, often verbs
- **Constants (static final)**: all uppercase, _to separate (e.g. MAX_CASE)
- **variable names**: mixedCase, no underscores
- **Type parameters**: Single character, capital letters e.g.
  - E - Element (used extensively by Java Collections Framework)
  - K - key
  - T - type
  - V - value
  - S, U, V etc. -2nd, 3rd etc. types
  
### Packages

- bundles related packages
- stored physically on folder structure: /src/com/example/mypackage
- creates new namespace, avoids naming conflicts
- classes within package have unrestricted access to one another (but still restrict access from classes outside the package)
- Types / classes don't *have* to be imported on top but can be used with the fully qualified name in code: `javafx.scene.Node node = null;`
- this has to be done if multiple packages with the same Class have to be used (e.g. javafx.scene.Node AND org.w3c.dom.Node)
- namespace TLD.Domain.[...more subdomains].packagename
- `.*` doesn't add "sub-packages" automatically as they are *different* packages:

      import java.awt.*;
      import java.awt.event.WindowAdapter; // still necessary
      import java.awt.event.WindowEvent; // (or use import java.awt.event.*)

- when importing with `.*` from multiple packages, name clashes can occur if they contain classes of the same name
- com.example can be used for packages that aren't distributed
- Intellij: create folder structure automaticaly by naming class with package: `com.example.game.Player`

#### creating a jar file from a package

- File -> project structure -> artefacts -> + button -> JAR -> From module with dependencies
- Indicate main class to make executable if desired
- extract to target jar
- ok
- build -> build artefacts -> build
- jar is created in /out/artifacts/

#### Use the created package (new project or existing)

- File -> projects structure -> libraries -> + button (java) -> go to folder and select .jar file
- library is accessible in project and visible under External Libraries in project pane

### Scope

- pretty much like javascript (in case of let / const, NOT var)
- block scope
- variables declared in local block of same name shadow variables in higher scope
- member variables (class level) of same name can still be accessed with `this.` prefix
- with inner classes, enclosing class member variables can be accessed `ParentClassName.this.variableName`, e.g. `ScopeCheck.this.privateVar`
  - same goes for calling enclosing class methods
- for loop variable is in loop scope (though not technically in {} )
- enclosing class can access private variables of inner class (with instantiation):

      public class ScopeCheck {
        public void useInner() {
          InnerClass inner = new InnerClass();
          System.out.println("InnerClass varThree=" + inner.varThree);
        }
        
        public class InnerClass {
          private int varThree = 3;
        }
      }
  
### Access modifiers

#### Top Level

- only classes, interfaces and enums can exist at top level
- public: visible to all classes in- and outside package
- package-private: only available within its own package; default if not explicitely declared with "public" (no "package-private" keyword exists)

#### Member level

- public: same as at top level (access anywhere)
- private: only accessible within the class; not possible for members in interfaces
- protected: accessible within the class and subclasses

### static

- static field is associated with class and not instance (but are also accessible and changeable by non-static methods of the class)
- static methods can only be called with classname.method(), not instanceName.method()
- main() as the application's entry point has to be static because there are no class instances at this point yet (by convention defined in class Main)
- non-static member variables and methods can't be accessed from static methods (main) because **when main is run, no instance exists yet**
- the reverse is not true (static methods and members can be accessed fine by non-static methods)

### final

- can be set / initialized only in declaration or in constructor (but NOT both); unchangable after constructor finishes
- basically a (possibly computed, so not at compile time) constant / immutable member variable
- for example giving "unique id" to an instance, see 016_section_11_access_modifiers, SomeClass.java
- for constants, use `static final` as there will be no need for a copy of a constant in an instance; they follow the same rules as other final variables in terms of declaration, meaning they can be computed as well.
- class can be final; prevents from being subclassed. Example: Math is declared final (can't be inherited from) AND the constructor is private (can't be instantiated), so it can be ONLY used statically
- make methods final that should not be overridden

### static initialization blocks

- rarely used; best explained by code:

      public class StaticInitializationBlockTest {
        public static final String owner;
        static {
            owner = "Peer";
            System.out.println("Static initialization block called");
        }
        public StaticInitializationBlockTest() { // constructor
            System.out.println("Constructor called");
        }
        static {
            System.out.println("Second Static initialization block called");
        }
        // ....
      }
      
      StaticInitializationBlockTest sib = new StaticInitializationBlockTest();
      // Static initialization block called
      // Second Static initialization block called
      // Constructor called

### Sidenotes

- Intellij: ctrl-click on method (or ctrl-b when cursor is on method) goes to definition

## Section 12 - Collections

### Lists

- *"unified architecture for representing and manipulating collections."* - (<https://docs.oracle.com/javase/tutorial/collections/>)
- Core collection Interfaces:
![Core collection interfaces](images/core_collection_interfaces.png "Core collection interfaces")
- designed for interoperability / compatibility with each other and other types (ArrayList, LinkedList); valid: `private Collection<Seat> seats = new LinkedList<>();`
- implement comparable to be able to use efficient static search / sort / reverse etc. methods from Collections framework:

      // Seat class must implement Comparable, seats is an ArrayList of Seats
      int foundSeat = Collections.binarySearch(seats, requestSeat, null);

- static methods covered: Collections.shuffle, min, max, sort, reverse, binarySearch, swap
- to make a method accept a List of a certain class AND its subclasses, a wildcard can be used:

      public static void sortList(List<? extends Theater.Seat> list)
- most collection types can be instantiated with arrays using Arrays.asList(ar) passed to the constructor

### Exkurs: Comarable / Comparator

If a class doesn't implement comparator, a comparator can be passed to the Collections.sort (revere, etc.) method:

      // this one is defined in the parent class 
      // Theater in this case as a static (and final) method
      // because the inner class is usually private 
      static final Comparator<Seat> PRICE_ORDER = new Comparator<Seat>() {
        @Override
        public int compare(Seat seat1, Seat seat2) {
          if(seat1.getPrice() < seat2.getPrice()) {
            return -1;
          }
          else if(seat1.getPrice() > seat2.getPrice()) {
            return 1;
          }
        return 0;
        }
      };

      // usage:
      Collections.sort(seatsCopy, Theater.PRICE_ORDER);

Also check what intellij does if you click on the "split into declaration and initialisation" suggestion for an example of a static initialisation block as shown in the section above

"ordering being consistent with equal" in the Java Collections documentation means the comparator should only return 0 if the objects are actually equal (so a comparator that only compares one of many members doesn't compare for actual equality)

### Maps

- maps keys to values, (can) use generics, replaces dictionaries
- keys should be immutable (intellij / java will complain if not), e.g. objects
- not ordered
- myMap.put(key, value) returns the previous value associated with the key
- self explaining: .containsKey, containsValue,
- replace(key, newVal) or replace(key, oldVal, newVal) replaces only if key (or key value pair) exists, returns previous value
- .remove(key) or .remove(key, value) for specific key-val combinations (returns boolean)
- getOrDefault(searchKey, defaultItem)
- specify key and value types in declaration:

      // String key, String value:
      Map<String, String> languages = new HashMap<>();
      languages.put("Java", "a compiled high level oo language");

      // don't forget to add it in the parameter signature for methods too:    
      public static void printMap(Map<String, String> m) {
        for(String key: m.keySet()) {
            System.out.printf("%10s: %-15s\n", key, m.get(key));
            //System.out.println(key + ": " + languages.get(key));
        }
      }

### Immutable classes

Protects classes / members from changes (e.g. by plugins or user-side code like in excel)

Some techniques:

- returning copies of class members:
  
      public Map<String, Integer> getExits() {
        // return copy so outside code can't change HashMap
        // (though object references could still be changed)
        return new HashMap<String, Integer>(exits);
      }
- making fields `private final`; makes it clear that it shouldn't be changed and ensures no accidental modification
- only provide setters where necessary
- make fields that should be final after instantiation as an argument to the constructor rather than setters:

      // instead of
      locations.put(2, new Location(2, "You are at the top of a hill"));
      locations.get(2).addExit("N", 2);
  
      // prefer this where aplicable:
      tmpExit = new HashMap<>();
      tmpExit.put("N", 5);
      locations.put(2, new Location(2, "You are at the top of a hill", tmpExit));
      
      // in Location constructor, make sure to instantiate a new list 
      // from the passed in reference, otherwise it could still be changed after
      // the fact.
      // In public Location(..., exits):
      this.exits = new HashMap<String, Integer>(exits);

- Don't allow the class to be subclasses, see access modifiers

See also [A Strategy for Defining Immutable Objects](https://docs.oracle.com/javase/tutorial/essential/concurrency/imstrat.html)

### Set / HashSet

- usual set properties (unordered, no duplicates)
- iteration and contains operations possible, no direct access like .get(idx)
- best performing implementation is `HashSet<>`, basically a HashMap without values (values are the keys)
- fast
- union operation with addAll (as duplicates are not added)
- when using own classes as values (keys) in HashSets, Objects **equals** and **hashCode** should be **overridden**; otherwise, just referential equality (is it the same object) is used to determine uniqueness in set

      // from Object.java
      public boolean equals(Object obj) {
        return (this == obj);
      }

- 2 objects that compare equal must have the same hashcode
- the hashcode is NOT used for comparison in a hashSet but to put them into buckets to facilitate search

>If two objects have the same hashcode then they are NOT necessarily equal. Otherwise you will have discovered the perfect hash function.
But the opposite is true: if the objects are equal, then they must have the same hashcode.

[StackOverflow answer](https://stackoverflow.com/questions/5443136/are-two-java-objects-with-same-hashcodes-not-necessarily-equal/5443140)

This does still work to remove duplicates and meets the requirement that two objects that are the same have the same hashcode:

    @Override
    public int hashCode() {
        return 0; // all fall in bucket 0
    }

Problem: This puts all items in the same bucket in the HashMap, reducing performance benefits of hash collection class.

When overriding equals, make sure to use the correct signature so it gets actually used, otherwise it's just overloaded:

    // WRONG (and intellij will complain that nothing is actually overridden):
    @Override
    public boolean equals(HeavenlyBody obj) { ...

    // Correct (best let these be generated by intellij anyway):          
    @Override
    public boolean equals(Object obj) {...
      // don't forget to cast obj to the object needed, e.g.
      String objName = ((HeavenlyBody) obj).getName();

[Documentation and rules for hashCode and equals](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#hashCode--)

Set operations from [Java tutorial](https://docs.oracle.com/javase/tutorial/collections/interfaces/set.html):

- s1.containsAll(s2) — returns true if s2 **is a subset** of s1. (s2 is a subset of s1 if set s1 contains all of the elements in s2.)
- s1.addAll(s2) — transforms s1 into the **union** of s1 and s2. (The union of two sets is the set containing all of the elements contained in either set.)
- s1.retainAll(s2) — transforms s1 into the **intersection** of s1 and s2. (The intersection of two sets is the set containing only the elements common to both sets.)
- s1.removeAll(s2) — transforms s1 into the (asymmetric) **set difference** of s1 and s2. (For example, the set difference of s1 minus s2 is the set containing all of the elements found in s1 but not in s2.)

No symetric difference method (items that appear in one OR the other, but NOT BOTH); can be obtained by removing intersection from the union:

### Potential issues with equals() and subclassing

When allowing a class to be subclass, take care deciding if equals / hashCode should be allowed to be overridden as we,, otherwise declare them as final.

### Sorted Collections

`Collections.unmodifiableMap(Map map)` Returns an unmodifiable **view** of the specified map; good to return immutable objects that don't allow modifications of the referenced objects; fast, as it doesn't create a copy but a view. **Objects in the collection can *still* be modified.**

Another way to iterate over a Map besides `for(ItemType item: list.keySet()) {...}`:

    // or just Entry<StockItem, Integer> item: ... 
    for(Map.Entry<StockItem, Integer> item: list.entrySet()) {
      // item is of type Map.Entry<StockItem, Integer> and provides
      // methods like getKey() and getValue()
      totalCost += item.getKey().getPrice() * item.getValue();
      s += item.getKey() + ", " + item.getValue();
    }

`LinkedHashMap` works like hashmap but keeps insertion order. `unmodiefiableMap` keeps that order as it's just a view of the underlying Map, whatever specific subtype.

`TreeMap`
> The map is sorted according to the natural ordering of its keys, or by a Comparator provided at map creation time, depending on which constructor is used.

- Treemap calls CompareTo when inserting items, resulting in the desired ordering but also doing more work when inserting items.

### Sidenotes

- Intellij: psvm => public static void main(....)
- Intellij: if more than one class has a main method, it can be run by right clicking in the code and selecting "run Blah.main" or ctrl-shift-f10
- convert char to uppercase (in this example while reading it in): `Character.toUpperCase((char) scanner.nextInt());`
- Math.cbrt = cube root ( `x = Math.cbrt(x*x*x)`)
- nested enums are a useful thing for constants to get errors at compile time when assigning wrong values:
- enums have also a default toString() method that returns the enum "name"

      public class HeavenlyBody {
        // ...
        protected enum BodyTypes {
          MOON, PLANET, DWARF_PLANET
        }
      }

## Section 13 - Javafx (incl. many other Java concepts)

### Installation

- not bundled with Java > 8
- Download sdk for OS at <https://gluonhq.com/products/javafx/>
- ctrl-shift-alt-s -> global libraries -> navigate to lib, select all .jar files, add

### Creating a hello world project (>java8)

- create javafx project
- file -> project structure -> global libraries -> right click javafx and add
- right click src folder, new, module-info.java -> add code (package name here is "sample")

      module HelloWorldFX{
        requires javafx.fxml;
        requires javafx.controls;
        opens sample;
      }
- when refactoring the project name and an compile error occurs, change configuration in the pulldown in the upper right corner in intellij

### Overview

- JavaFX uses a theater metaphor (stage, scene).
  - Stage: top level UI container
  - Scene: backed by scene graph; can be build by hand or read in from fxml:
- designed with MVC pattern in mind (separation of data and UI code)
  - model:
  - view: fxml (basically a ui description in a xml);

- Applications Main must extend `javafx.application.Application` (already configured when creating a javafx project)

sample.fxml:

    (...)
    <GridPane fx:controller="sample.Controller"
    xmlns:fx="http://javafx.com/fxml" alignment="center" hgap="10" vgap="10">
    </GridPane>

Main.java:

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

The same in code:

    @Override
    public void start(Stage primaryStage) throws Exception{
      GridPane root = new GridPane();
      root.setAlignment(Pos.CENTER);
      root.setVgap(10);
      root.setHgap(10);
      primaryStage.setTitle("Hello Javafx!");
      primaryStage.setScene(new Scene(root, 300, 275));
      primaryStage.show();
    }

- to change ui, load new graph (fxml) into scene and load it into new stage

### Layouts

Overview:

[builtin layouts from oracle documentation](https://docs.oracle.com/javafx/2/layout/builtin_layouts.htm)

#### GridPane

- define spacing and alignment in GridPane wrapper: `alignment="center" hgap="10" vgap="10"`
- Position with GridPane.rowIndex and .columnIndex:

![GridPane positioning](images/gridpane.png)

- use gridLinesVisible="true" as wrapper property to debug positoning:

![gridlines](images/gridlines.png)

- Prefered size: determine how much space control gets (default: as much as it needs to display the content)
- defince column widths with columnConstraint:

      <columnConstraints>
          <ColumnConstraints percentWidth="70.0"/>
          <ColumnConstraints percentWidth="30.0"/>
      </columnConstraints>
- define pane conten with alignment, e.g. `alignment="top_center"`
- padding:

      <padding>
          <Insets top="30"/>
      </padding>

- Span multiple columns with `<Button text="Hey!"  GridPane.rowIndex="3" GridPane.columnIndex="0" GridPane.columnSpan="2"/>`
- set h/v alignment with `GridPane.halignment="RIGHT"`

#### HBox/VBox

- usually used for dialogs / as child
- lays elements out vertically (VBox: vertically) by default
- Limited css with -fx- prefix available (also in all other layout types?):

`<HBox xmlns:fx="http://javafx.com/fxml" fx:controller="sample.Controller" alignment="top_center"
style="-fx-border-color: red; -fx-border-width: 3; -fx-border-style: dashed;">`

- spacing: `spacing="10"`
- change spacing of elements (inside the element tag): `prefWidth="90"`

#### BorderPane

- most commonly used for top level windows
- places controls in one of 5 positions: top, bottom, left, right, center
- center will take whatever space is left over and fill entire space (as a rectangular block)

Simple example:

    <BorderPane xmlns:fx="http://javafx.com/fxml" fx:controller="sample.Controller">
        <center>
            <Label>
            Main application (center)
            </Label>
        </center>
        <bottom>
            <HBox spacing="10" alignment="bottom_right">
                <padding>
                    <Insets bottom="10" right="10" />
                </padding>- Stage: top level UI container
                <Button text="OK" prefWidth="90"/>
                <Button text="Cancel" prefWidth="90"/>
                <Button text="Help" prefWidth="90"/>
            </HBox>
        </bottom>
    </BorderPane>

![border pane example](images/borderpane.png)

#### AnchorPane

- popular top level layout
- Anchors children to edges

#### FlowPane / TilePane

- very much like css flex, automatically wraps children (horizontally or vertically, depending on the FlowPane direction) if FlowPane doesn't have enough space in the given dimension; reacts to resizing.
- useful if amount of elements isn't clear on compiletime (e.g. reading elements from a database)

      <?import javafx.scene.layout.FlowPane?>
      <?import javafx.scene.control.Button?>
      <FlowPane fx:controller="sample.Controller" xmlns:fx="http://javafx.com/fxml" orientation="HORIZONTAL">
          <Button text="Button 1" />
          <Button text="Button 2" />
          <Button text="Button 3" />- Stage: top level UI container
- Scene: backed by scene graph; can be build by hand or read in from fxml:

          <Button text="Button 4" />
          <Button text="Button 5" />
          <Button text="Button 6" />
          <Button text="Button 7" />
          <Button text="Button 8" />
          <Button text="Button 9" />018e.eventhandlers
          <Button text="Button 10" />
      </FlowPane>

![Flowpane example](images/flowpane.png)

- TilePane is like FlowPane but puts elements in a grid

#### StackPane

- stacks children on top (z) of each other in order added
- useful for adding a background image for example

### Controls (control nodes)

[Example Button (and other) documentation](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Button.html)

- all controls implement Skinnable, so extending or creating new controls is possible
- inherit form Labeled

- Old and ugly javafx graphics library: <https://repo1.maven.org/maven2/net/java/linoleum/jlfgr/1_0/> (add with module settings)
  - rename FIRST to jlfgr.jar to avoid idea error message
  - add "requires jlfgr;" to module-info.java
  - better yet, never use it except for this course
  - add image to e.g. button like this (image will be highlighted as not found but will still work / compile):
  - tooltips must be added as "inner tags"

        <Button onAction="#showNewItemDialog">
            <tooltip>
                <Tooltip text="Add a new ToDo Item"/>
            </tooltip>
            <graphic>
                <ImageView>
                    <Image url="@/toolbarButtonGraphics/general/New24.gif"/>
                </ImageView>
            </graphic>
        </Button>

#### Radiobuttons

- Single select radio button groups need to have a ToggleGroup defined (note the $ in the RadioButton tags):
- ToggleGroup doesn not inherit from node and needs to be added directly to the scene graph
- use `selected="true"` to select one by default

      <fx:define>
        <ToggleGroup fx:id="colorToggleGroup" />
      </fx:define>
      <RadioButton toggleGroup="$colorToggleGroup" GridPane.rowIndex="0" GridPane.columnIndex="2" text="Red" />
      <RadioButton toggleGroup="$colorToggleGroup" GridPane.rowIndex="0" GridPane.columnIndex="3" text="Blue"  selected="true"/>
      <RadioButton toggleGroup="$colorToggleGroup" GridPane.rowIndex="0" GridPane.columnIndex="4" text="Green" />
  
#### Checkbox

- supports a third indeterminate state (the infamous java three state checkbox, best forget it immediately)
- don't support togglegroups
- Group (visually) with e.g. VBox

      <VBox GridPane.columnIndex="0" GridPane.rowIndex="2">
          <CheckBox GridPane.rowIndex="1" GridPane.columnIndex="0" text="Dog"/>
          <CheckBox GridPane.rowIndex="1" GridPane.columnIndex="0" text="Cat"/>
          <CheckBox GridPane.rowIndex="1" GridPane.columnIndex="0" text="Bird"/>
      </VBox>

#### ToggleButton

- like a button but with states (toggled / untoggled); can be put in a ToggleGroup like radio buttons

#### TextField / PasswordField / Combox / ChoiceBox

- TextField / PasswordField: nothing much to say; use wrapText="true" for wrapping in TextArea
- ComboBox (also take note of GridPane.columnSpan and setting the default value):

      <ComboBox GridPane.rowIndex="1" GridPane.columnIndex="2"  GridPane.columnSpan="3">
        <items>
            <FXCollections fx:factory="observableArrayList">
                <String fx:value="Option 1 is a really long (and default) option"/>
                <String fx:value="Option2"/>
                <String fx:value="Option3"/>
                <String fx:value="Option4"/>
            </FXCollections>
        </items>
        <value>
            <String fx:value="Option 1 is a really long (and default) option"/>
        </value>
      </ComboBox>
  
  - ComboBox can be made editable. Note and forget.
- ChoiceBox: same as ComboBox, just adds a checkmark in front of the selected item; can be used with cellfactory;
  - stick to ComboBox and forget ChoiceBox
  
#### Slider, Spinner, ColorPicker, DatePicker, TitledPane, Accordion

- See 018d_controls > sample.fxml for examples

Slider and Spinner examples (just in same screenshot to save space):
![Slider and spinner](images/slider_spinner.png)

- Spinner can be made editable (and crash the program if numeric input is too large and the arrows are used)
- TitledPane (control, not a layout), can be used as a child of Accordion

#### Event Handlers (=Event listeners)

Lifecycle of UI application:

- initialization
- UI thread waits for user input and handle input event
- teardown / cleanup on exit

- Lifecycle methods in **Main.java**: start(Stage primaryStage), stop(), init()
- Lifecycle methods in **Controller.java**: initialize()

Event handlers are defined in Controller.java

Basic event handler for a button:

Controller.java:

    public class Controller {
      public void onButtonClicked() {
        System.out.println("Well hello!");
      }
    }

sample.fxml:

    <Button text="Say hello" onAction="#onButtonClicked"/>

To assign a handle on a control (or any other item), assing a fx:id:

    <TextField fx:id="nameField" />

...and assign it in Controller.java using @FXML annotation to match the variable name to the fxml control id:

    @FXML
    private TextField nameField; // var name has to be exactly the fx:id

    @FXML // not really necessary, why put it here?
    public void onButtonClicked() {
        System.out.println("Well hello, " + nameField.getText());
    }

*DON'T initialize again as the initiliasation is done by the injections done using the @FXML annotation!*

Every variable referencing a UI element must be prefixed individually:

    @FXML
    private TextField nameField;
    @FXML
    private Button helloButton;
    @FXML
    private Button byeButton;

To use an event handler method for different controls, add an ActionEvent parameter as a handle to get it, similar to javascript:

fxml:

    [...]
    <Button text="Say hello" GridPane.columnIndex="1" GridPane.rowIndex="0" onAction="#onButtonClicked"/>
    <Button text="Say hello again!" GridPane.columnIndex="1" GridPane.rowIndex="1" onAction="#onButtonClicked"/>

Controller.java:

    @FXML
    private TextField nameField;
    @FXML
    private Button helloButton;
    @FXML
    private Button byeButton;

    @FXML
    public void onButtonClicked(ActionEvent e) {
        if(e.getSource().equals(helloButton)) {
            System.out.println("Well hello, " + nameField.getText());
        }
        else if(e.getSource().equals(byeButton)) {
            System.out.println("Good bye, " + nameField.getText() + " :(");
        }
    }

The initialize method in Controller.java (`public void initialize()`) gets called when the application starts and can be used to set prefered states of controls or other UI elements.

Handler example for checking if a TextField is empty, + initialize example:

    <TextField onKeyReleased="#handleKeyReleased" [...]

Controller.java:

    @FXML
    public void initialize() {
      helloButton.setDisable(true);
    } 
    
    @FXML
    public void handleKeyReleased() {
      String text = nameField.getText();
      boolean disableButtons = text.isEmpty() || text.trim().isEmpty();
      helloButton.setDisable(disableButtons);
    }

#### Threads and Runnable

Time consuming tasks shouldn't be done on the main UI thread as it will lock up the UI. Rather, a new background thread should be started using Runnable:

    Runnable task = new Runnable() {
      @Override
      public void run() {
        System.out.println("Thread started"); // ok, no fx UI change in console
        Thread.sleep(2000); // do stuff
        System.out.println("Thread is done");
      }
    };
    new Thread(task).start(); // or just task.run()?

UI changes *must* be done in the UI (application) thread to avoid collisions (Java will throw an exception if changes are attempted in a new Runnable thread). The current type of thread can be checked using `Platform.isFxApplicationThread()`:

    Runnable task = new Runnable() {
      @Override
      public void run() {
        try { // sleep needs try/catch
          String s = Platform.isFxApplicationThread() ? "UI thread" : "background thread";
          System.out.println("I'm going to sleep on the " + s); // background thread
          Thread.sleep(2000);
          Platform.runLater(new Runnable() {
            @Override
            public void run() {
              String s = Platform.isFxApplicationThread() ? "UI thread" : "background thread";
              System.out.println("I'm updating on the " + s); // UI Thread
              ourLabel.setText("Thread done");
            }
          });
        } catch(InterruptedException exception) {
          // nada
        }
      }
    };
    ourLabel.setText("Starting thread");
    new Thread(task).start(); 

Another / additional way to handle thread safety is using the javafx concurrency package: [javafx threads](https://docs.oracle.com/javafx/2/threads/jfxpub-threads.htm)

#### TodoList application notes

- Add Items to `<ListView>` and set to single choice (Controller.java):

      public class Controller {
        private List<TodoItem> todoItems;
        @FXML
        private ListView<TodoItem> todoListView;
      
        public void initialize() {
          todoItems = new ArrayList<>();
          todoItems.add(item1);
          todoItems.add(item2);
          [...]
          // DON'T re-initialize the ListView (e.g. todoListView = new ListView<>()
          todoListView.getItems().setAll(todoItems);
          todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        }  
      }

- Get selected item:

      TodoItem selected = todoListView.getSelectionModel().getSelectedItem();

- To add an "onChange" eventhandler (regardless of how the change happened, e.g. mouseclick or programatically), add the event handler in code:

      todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
        @Override
        public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
          if(newValue != null) {
            TodoItem selected = todoListView.getSelectionModel().getSelectedItem();
            itemDetailsTextArea.setText(selected.getDetails());
            deadlineLabel.setText(selected.getDeadline().toString());
          }
        }
      });

- format datetime (getDeadline returns `LocalDate`):

    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
    deadlineLabel.setText(df.format(selected.getDeadline()));
  
- Singleton: Class where only one instance is created over the entire lifecycle of the application
  - contains a static method that returns a (=the) instance of itself
  
Singleton example:

    public class TodoData {
      private static TodoData instance = new TodoData();
      private static String filename = "TodoListItems.txt";
      private List<TodoItem> todoItems;
      private DateTimeFormatter formatter;
    
      // private constructor to prohibit outside instantiation
      private TodoData() {
          formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      }

      public static TodoData getInstance() {
          return instance;
      }
    
      //[...] getters, setters etc
    }

- Dialogs (like confirmation windows, add new item, etc) must be added in their own fxml file and have their own controller (e.g. todoItemDialog.fxml + TodoItemDialog.java, same name just for coding style).
  - Add with new -> fxml file
- Main dialog pane: `DialogPane`

#### Data binding

- binding a control to data instead of manually synching, avoiding desynching of the controls and the data, using an Observable collection
- e.g.: `todoListView.setItems(TodoData.getInstance().getTodoItems());` binds the content of the list permanently to the todoListView  

- FXCollections types reduce the number of notifications for performance reasons:
[https://docs.oracle.com/javase/8/javafx/api/javafx/collections/FXCollections.html](https://docs.oracle.com/javase/8/javafx/api/javafx/collections/FXCollections.html)

#### Cell Factories

- visual style of cells, e.g. from a ListView, can be altered using cell factories

>The implementation of the cell factory is then responsible not just for creating a Cell instance, but also configuring that Cell such that it reacts to changes in its state.
[https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Cell.html](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Cell.html)

#### Context Menu

- see Controller.java + comments

#### KeyEvents and Toolbars

Key events

- current focus determines which event is raised on keypress
- are defined in the fxml of the window and element where the focus will occur (in the ToDo application in the ListView, which is mainwindow.fxml), e.g. `<ListView fx:id="todoListView" onKeyPressed="#handleKeyPressed">`

Toolbars

- just look at the mainwindow.fmxl in 018f_TodoList

#### Sorting

An easy way to implement sorting in a list is to wrap the ObservableList in a SortedList; it is enough to do this in the Controller.java, so no changes in the TodoData class is necessary:

In Controller.java

    SortedList<TodoItem> sortedList = new SortedList<TodoItem>(TodoData.getInstance().getTodoItems(), new Comparator<TodoItem>() {
      @Override
      public int compare(TodoItem o1, TodoItem o2) {
        return o1.getDeadline().compareTo(o2.getDeadline());
      }
    });

    // used before wrapping it in a sorted list:
    // todoListView.setItems(TodoData.getInstance().getTodoItems());

    todoListView.setItems(sortedList);

#### Filtering

To make the sorted / sortable list also filterable, add a FilteredList to the wrapping chain:

    // wrap todoItems in a FilteredList to use later for "show today only" togglebuton
    filteredList = new FilteredList<TodoItem>(TodoData.getInstance().getTodoItems(), new Predicate<TodoItem>() {
      @Override
      public boolean test(TodoItem todoItem) {
        return true; // show everything by default
      }
    });

    // wrap FilteredList in SortedList
    SortedList<TodoItem> sortedList = new SortedList<TodoItem>(filteredList, new Comparator<TodoItem>() {
    [...]

To avoid having to rewrite the predicates if they are used more than once, best initialize them before and then just use the variable name:

    // init as class members to be usable everywhere, assign in initialize(): 
    wantAllItems = new Predicate<TodoItem>() {
      @Override
        public boolean test(TodoItem todoItem) {
        return true;
      }
    };

    wantTodaysItems = new Predicate<TodoItem>() {
    [...]

Then, by setting the fitting predicate, the list can be sorted as desired in the controller:

    @FXML
    public void handleFilterButton() {
      if(filterToggleButton.isSelected()) {
        filteredList.setPredicate(wantTodaysItems);
      } else {
        filteredList.setPredicate(wantAllItems);
      }
    }

#### CSS with JavaFX

- JavaFX uses a default stylesheet for a theme in the background
  - default theme: modena
  - also included: caspian (more rounded / shadowed controls)
  - default stylesheet can be overridden in start() method in Main.java:

`setUserAgentStylesheet(STYLESHEET_CASPIAN);`

or, to add an own stylesheet:

`StyleManager.getInstance().addUserAgentStylesheet(getClass().getResource("/style.css").toString());`

- Styles have sometimes, but not always the same values or property names as in normal css, see example stylesheet below
- css styles (with -fx- prefix) can be set inline or per stylesheet:

Inline: `<Button text="Button One" style="-fx-background-color: green; -fx-text-fill: white;"/>`

Stylesheet (e.g. styles.css):

    Button {
      -fx-background-color: green;
      -fx-text-fill: white;
    }
      Button:hover {
      -fx-cursor: hand;
    }
    .test {
      -fx-background-color: blue;
    }
    #anotherButton {
      -fx-background-color: red;
    }

Associate the stylesheet with application in the fxml file as a property of a pane:

`<GridPane [...] stylesheets="@styles.css">`

Assign classnames and IDs in the fxml using `styleClass` and / or `id` (**NOT** fx-id):

    <Button styleClass="test" [...] />    
    <Button id="anotherButton" [...] />

Positioning (float, width, position etc.) properties are **NOT** supported, while padding / margin are where it makes sense for the scene graph

[Link to JavaFX CSS documentation](https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html)

#### Transforming nodes and choosers

Effects like resizing, rotation etc. can be added programmatically.

Resize element and add DropShadow on hover example:

Controller.java:

    @FXML
    public void zoomOnHover(Event event) {
        double scale = 1.0;
        DropShadow dropShadow = null;
        if(event.getEventType().getName().equals("MOUSE_ENTERED")) {
            scale = 1.2;
            dropShadow = new DropShadow(0.5, Color.BLUE);
        }
        Control control = (Control) event.getSource();
        control.setEffect(dropShadow);
        control.setScaleX(scale);
        control.setScaleY(scale);
    }

fxml:

`<Label onMouseEntered="#zoomOnHover" onMouseExited="#zoomOnHover" text="JafaFX Effects" />`

[List of possible effects for setEffect](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/effect/package-frame.html)

#### FileChooser

Event handlers that creates a modal file chooser (and one for choosing directories):
  
    @FXML
    GridPane gridPane; // add fx:id="gridPane" for getting the main window for the chooser

    @FXML
    public void openFile() {
        FileChooser chooser = new FileChooser();
        // Optionally set allowed extensions, in case of saveFile appends extension automatically
        chooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("Text", "*.txt"),
          new FileChooser.ExtensionFilter("PDF", "*.pdf"),
          new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png", "*.gif"),
          new FileChooser.ExtensionFilter("All files", "*.*") // (optional) catch all
        );

        File file = chooser.showOpenDialog(gridPane.getScene().getWindow());
        if(file != null) { // user canceled out of file chooser
            System.out.println(file.getPath());
        }
    }

    @FXML
    public void openDirectory() {
        DirectoryChooser chooser = new DirectoryChooser();
        File directory = chooser.showDialog(gridPane.getScene().getWindow());
        if(directory != null) { 
            System.out.println(directory);
        }
    }

For saving a file, use

    // ...
    File file = chooser.showSaveDialog(gridPane.getScene().getWindow());
    // ...

For allowing to open multiple files:

    List<File> file = chooser.showOpenMultipleDialog(gridPane.getScene().getWindow());

#### Opening web sites

- **This code isn't working on linux, so a workaround is used using apache commons**

##### Installing apache commons

- [Download commons archive](https://plugins.jetbrains.com/plugin/13251-apache-commons-library) and unzip to the java lib directory of choice
- in intellij, go to project's module settings and add the jar file
- add `requires java.desktop;` and `requires commons.lang3;` to `module-info.java`
- For opening URLs use the following [utility method from stackoverflow](https://stackoverflow.com/questions/27879854/desktop-getdesktop-browse-hangs)

    @FXML
    public void handleLinkClicked() {
      browseURL("http://www.javafx.com");
    }

    public void browseURL(String urlString) {
        try {
            if (SystemUtils.IS_OS_LINUX) {
                // Workaround for Linux because "Desktop.getDesktop().browse()" doesn't work on some Linux implementations
                if (Runtime.getRuntime().exec(new String[] { "which", "xdg-open" }).getInputStream().read() != -1) {
                    Runtime.getRuntime().exec(new String[] { "xdg-open", urlString });
                } else {
                    System.out.println("x-dgopen not supported!");
                }
            } else {
                if (Desktop.isDesktopSupported())
                {
                    Desktop.getDesktop().browse(new URI(urlString));
                } else {
                    System.out.println("Desktop command not supported!");
                }
            }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

- For the web view part of the course, use `requires javafx.web;` in `module-info.java`

Showing a page in a webview code:

    // In FXML
    <WebView fx:id="webView" [...] />
  
    // In controller
    @FXML
    WebView webView;

    @FXML
    public void showPageInWebView() {
      WebEngine engine = webView.getEngine();
      engine.load("http://www.javafx.com");
    }

#### SceneBuilder

- Basically a JavaFX UI builder, download and install [from here](https://gluonhq.com/products/scene-builder/)
- Can be used inside intellij: open fxml file an the bottom there's a SceneBuilder tab
- sometimes when scenebuilder can't be loaded, restart intellij...
- to wrap items in a container, select them on the left side, context menu -> wrap in
  - if this screws up the layout, select the wrapping container and on the right side, under "layout", set row/column index and/or other settings accordingly
  - to unwrap, right click container and select "unwrap"  
- Standalone scenebuilder seems more stable than intellij integrated one and has more options (see menu at the top); also works better with external css (immediate updates on saving the .css); searchable properties etc.

#### ContactApp challenge

- see comments and links in 018i_JavaFX_Challenge

Temp links:

<https://www.tutorialspoint.com/how-to-add-data-to-a-tableview-in-javafx>

- For making updates in an ObservableList visible in the *view (e.g. TableView), these must be returned using [varname]Property(), e.g.

      public SimpleStringProperty lastNameProperty() {
        return lastName;
      }

- this has to be done even though String getFirstName etc. is used on initial load and everything seems to work except updates on an object!
- this is actually done automatically by intellij when adding getters and setters

Unordered notes:

- kill application with Platform.exit(); (for example with a quit button)
- adding a headerText by `dialog.setHeaderText("Create a new todo item");` looks different (more separated) than doing it in the fxml vie `<headerText>Blah</headerText>`
- [creating an executable from a project](https://intellij-support.jetbrains.com/hc/en-us/community/posts/360008885240-How-to-create-an-executable-exe-file-using-Intellij)

## Section 14 Exceptions and IO

### Exceptions

When to use try / catch and when do manual checking of parameters?

- manual checking (object == null etc.) more common
- no fixed rules; for input validation, try/catch might be shorter and more clear what is checked, :

    // with manual input validation ("Look Before you Leave")
    private static int getIntLBYL() {
      Scanner scanner = new Scanner(System.in);
      boolean isValid = true;
      String input = scanner.next();
      for (int i = 0; i < input.length(); i++) {
        if (!Character.isDigit(input.charAt(i))) {
          isValid = false;
        }
      }
  
      if (isValid) {
        return Integer.parseInt(input);
      }
      return 0;
    }

    // with exception (Easier to Ask for Forgiveness than Permission
    private static int getIntEAFP() {
      Scanner scanner = new Scanner(System.in);
      try {
        return scanner.nextInt();
      } catch (InputMismatchException e) {
        return 0;
      }
    }

- Class hierarchy: Throwable -> Exception -> RuntimeException
- No use to catch an exception if the code can't do anything usable with it; this means they should propagate to a point to where something can actually be done about it in the code
  - either let the exception be unhandled and propagate
  - or throw an exception
- try/catch block can be nested (but probably shouldn't)
- there are checked and unchecked exceptions [article](https://www.baeldung.com/java-checked-unchecked-exceptions)
  - checked expeptions: outside of control of the program (e.g. input file doesn't exist); must be declared in the method declaration with `throws` or caught (*catch or specify*)
  - unchecked exceptions: reflect error inside program logic; RuntimeException is
It is recommended to catch multiple exceptions for a block and not have a try/catch block for each statement:

    private static int divide() {
      int x, y;
      try {
        x = getInt();
        y = getInt();
        System.out.println("x is " + x + ", y is " + y);
        return x / y;
      } catch (NoSuchElementException e) {
        throw new ArithmeticException("no suitable input");
      } catch (ArithmeticException e) {
        throw new ArithmeticException("attempt to divide by 0");
      }
    }

Since Java 7, multiple exceptions can be handled in one catch block:

    [...]
    } catch(ArithmeticException | NoSuchElementException e) {
      // do stuff
    }

Cleanup (closing files etc) can be done in finally block.

Callstack:

- each new method is added to the callStack
- to make sense of the stacktrace, start at the bottom

### IO

- IO can be performed by byte (binary) or character (text, xml, spreadsheets etc) data
- sequential: in sequence
- random access: allows to jump in the file

Basic file writing:

      public class Locations implements Map<Integer, Location> {
        private static Map<Integer, Location> locations = new HashMap<>();
      
        public static void main(String[] args) {
          FileWriter locFile = null;
          try {
            locFile = new FileWriter("locations.txt");
            for (Location location : locations.values()) {
              locFile.write(location.getLocationID() + "," + location.getDescription() + "," + location.getExits() + "\n");
            }
          } catch (IOException e) {
            e.printStackTrace();
          } finally {
            try {
              if(locFile != null) { // avoid null pointer exception
                locFile.close();
              }
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }

"Mini-challenge": locFile is declared outside try-catch as  it couldn't be closed in finally if the try block failed as blocks always introduce new scope blocks `{}`

File writing with "throws" instead of catching the exception within the method:

    public static void main(String[] args) throws IOException {
      FileWriter locFile = null;
      try {
        locFile = new FileWriter("locations.txt");
        for (Location location : locations.values()) {
          locFile.write(
              location.getLocationID()
                  + ","
                  + location.getDescription()
                  +"\n");
        }
      } finally {
        if (locFile != null) {
          locFile.close();
        }
      }
    }

Since Java 7, an improved way is available, similar to "with" in python: [try-with-resoureces](https://docs.oracle.com/javase/7/docs/technotes/guides/language/try-with-resources.html)

File is automatically closed, even when an exception is thrown, and the exception thrown by closing the file doesn't mask/hides the exception that happens from opening / writing the file.

    try(FileWriter locFile = new FileWriter("locations.txt")) {
      for (Location location : locations.values()) {
        locFile.write(
                location.getLocationID()
                        + ","
                        + location.getDescription()
                        +"\n");
      }
    }

Multiple ressources can be opened using a semicolon (unintuitive, but what do i know...):

    try(FileWriter locFile = new FileWriter("locations.txt");
        FileWriter exitFile = new FileWriter("exits.txt")) {
          [...]
    }

Scanner can be setup to use a delimiter (which still must be skipped when reading the fields); this can of course also be done with line.split(DELIMITER) and converting the individual fields to their respective type.

      scanner = new Scanner(new FileReader("locations.txt"));
      scanner.useDelimiter(",");
      while(scanner.hasNextLine()) {
        int loc = scanner.nextInt();
        scanner.skip(scanner.delimiter());
        String description = scanner.nextLine();
        // ...

To read a String between the delimiters, use `scanner.next()` (there is no scanner.nextString).

Scanner closes any stream it is using automatically if the scanner instance is closed:

    // from Scanner.java
    public void close() {
        if (closed)
            return;
        if (source instanceof Closeable) {
            try {
                ((Closeable)source).close();
            } catch (IOException ioe) {
                lastException = ioe;
            }
        }
        sourceClosed = true;
        source = null;
        closed = true;
    }

#### BufferedReader

- reads the input stream characters and buffers (=caches) them into a character array, default buffer is 8kb
- must be initializes with a filereader:

    `scanner = new Scanner(new BufferedReader(new FileReader(DIRECTIONS_FILE)));`

- scanner can actually be skipped if no speciffic scanner functionality is used:
- for this to work with the locations file in the 019b_inputoutput project, we will need to specify that the line should be split into 2 chunks

      try (BufferedReader directionsFile = new BufferedReader(new FileReader(DIRECTIONS_FILE))) {
          String input;
          while ((input = locationsFile.readLine()) != null) {
            String[] line = input.split(DELIMITER, 2); // 2 chunks 
           // ...
      } catch(IOException e) {
        e.printStackTrace();
      }

#### BufferedWriter

- same as BufferedReader, writes to file in chunks

#### Byte Streams

- automatically writes binary data of correct length for the type, no delimiter necessary, thus avoiding issues with delimiters:

      public static void main(String[] args) throws IOException {
        try(DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(LOCATIONS_BIN)))) {
          for(Location location: locations.values()) {
            locFile.writeInt(location.getLocationID());
            locFile.writeUTF(location.getDescription());
            // save number of exits to read from stream later
            locFile.writeInt(location.getExits().size()-1);
            for(String direction: location.getExits().keySet()) {
              if(!direction.equalsIgnoreCase("Q")) {
                locFile.writeUTF(direction);
                locFile.writeInt(location.getExits().get(direction));
              }
            }
          }
        }
      }

- same for reading (note the end of file for the while loop is determined by the EOFException);
- if whe just used the IOException (which would work as well), we wouldn't notice other IO exceptions (file doesn't exist etc)

      try (DataInputStream locationsStream = new DataInputStream(new BufferedInputStream(new FileInputStream(LOCATIONS_BIN)))) {
          boolean EOF = false;
          while (!EOF) {
            try {
              int loc = locationsStream.readInt();
              String description = locationsStream.readUTF();
      
              Map<String, Integer> exits = new LinkedHashMap<>();
              int numExits = locationsStream.readInt();
              for(int i=0; i<numExits; i++) {
                exits.put(locationsStream.readUTF(), locationsStream.readInt());
              }
              Location location = new Location(loc, description, exits);
              locations.put(loc, location);
            } catch (EOFException e) {
              EOF = true;
            }
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

#### Serialization

- makes it possible to store the object in its entirety instead of storing the members values and then reassembling it when reading them back 
- to make an object / class serializable, it has to implement the Serializable interface
- when making a class serializable, it is strongly recommended adding a long field serialVersionUID, e.g. `private long serialVersionUID = 1L;`
  - this is basically a version number of the class to ensure it is compatible with the current class version when reading it in
  - otherwise the compiler gives a warning and give a default value; this can lead to problems if another compiler sets another default value
  - is used to ensure that the read in object is compatible with the type
- Serializable doesn't have any methods to implement
- non-primitive types (HashMaps etc.) must also implement the serializable interface in order to be able to be automatically be serialized (most do)
- when using own classes / types as fields, these must be made serializable
- otherwise, this is all there is to it (add `implements Serializable` and `serialVersionUID`) 
- instead of DataOutputStream, ObjectOutputStream is used to write serialized object
  
Example serialization of the adventure game code:
    
    // Location.java
    public final class Location implements Serializable {
      // ...
      private long serialVersionUID = 1L;
      // ...

    // Locations.java main()
    try(ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(LOCATIONS_BIN)))) {
      for(Location location: locations.values()) {
        locFile.writeObject(location);
      }
    }

- ObjectOutputStream (and input) implements DataInputStream methods, so it is not limited to serialized data but can be used to read/write primitives and UTF as well.
- when reading objects back from a stream using `readObject()`, they must be cast to the object type and a ClassNotFoundException must be handled:

      // from Locations.java
      static {
        try (ObjectInputStream locationsStream =
            new ObjectInputStream(new BufferedInputStream(new FileInputStream(LOCATIONS_BIN)))) {
          boolean EOF = false;
          while (!EOF) {
            try {
              Location location = (Location) locationsStream.readObject();
              locations.put(location.getLocationID(), location);
            } catch (EOFException e) {
              EOF = true;
            }
          }
        } catch (IOException | ClassNotFoundException e) {
          e.printStackTrace();
        }
      }

- object instances are unique in files, meaning when serializing 2 references pointing to the same object get serialized, only one instance is written to the file

#### RandomAccessFile

- can read random objects / elements from a file (as opposed to sequentially)
- file pointer: pointer (offset) pointing to the byte that the next file read / write will be done and is advanced by the amount of bytes the read/write operation took
- with objects of variable size (as opposed to primitives or objects containing only fixed length members) can't be looked up by just calculating (objNum - 1) * objSizeInBytes
- for these, and index needs to be included, with each record indicating the objects id, offset in file and length of the record (this is no fixed requirement but typical for an index record), all in all 12 bytes per record entry
- index should be read in memory

Example structure from Locations.java (see 019e_randomaccess)

    // creating a random access / indexed file
    // first 4 bytes: number of locations (bytes 0-4)
    // next 4 bytes: start offset of location section (bytes 4-7)
    // index (1692 bytes, byte 8 - 1699)
    // actual location record from byte 1700 onwards
    public static void main(String[] args) throws IOException {
      try (RandomAccessFile rao = new RandomAccessFile(LOCATIONS_RAND, "rwd")) {
        rao.writeInt(locations.size());
        int numberOfIntsInRecord =
            3; // 3 ints: locationID, file offset of location, size of location record in bytes
        int indexSize = locations.size() * numberOfIntsInRecord * Integer.BYTES;
        // file pointer is now after the first integer from the first writeInt operation
        int locationStart =
            (int) // cast to int because filepointer is long
                (indexSize + rao.getFilePointer() + Integer.BYTES);
        rao.writeInt(locationStart);
        // write locations and simultaneously build index in memory to write later
        long indexStart = rao.getFilePointer(); // save current pointer for writing index later
  
        // only needed for first location, sequential writing afterwards
        int startPointer = locationStart;
        for (Location location : locations.values()) {
          rao.writeInt(location.getLocationID());
          rao.writeUTF(location.getDescription());
          StringBuilder builder = new StringBuilder();
          for (String direction : location.getExits().keySet()) {
            if (!direction.equalsIgnoreCase(("Q"))) {
              builder.append(direction);
              builder.append(",");
              builder.append(location.getExits().get(direction));
              builder.append(",");
              // = direction, locationID, direction, ..., e.g. N,1,U,2...
            }
          }
          rao.writeUTF(builder.toString());
          // IndexRecord = own simple class with 2 fields, startByte and length
          IndexRecord record =
              new IndexRecord(startPointer, (int) (rao.getFilePointer() - startPointer));
          // index = Map<Integer, IndexRecord>
          index.put(location.getLocationID(), record);
          startPointer = (int) rao.getFilePointer();
        }
  
        // write the actual index starting on the offset we saved before
        rao.seek(indexStart);
        for (Integer locationID : index.keySet()) {
          rao.writeInt(locationID);
          rao.writeInt(index.get(locationID).getStartByte());
          rao.writeInt(index.get(locationID).getLength());
        }
      }
    }

Notes on random access file opening modes:

>rws flushes the contents of the file and the modification date of the file.  
>rwd flushs the contents of the file, but the modification date might not change until the file is closed.  
>rw only flushes when you tell it to and doesn't change the modifcation date until you close the file.  
>BTW rwd is much slower for writes than rw, and rws is slower again.

*https://stackoverflow.com/questions/14232539/java-randomaccessfile-mode-rws-vs-rwd*

Reading in the index example file:

    // we just read the index now as the point is to load the actual location on demand
    static {
      try {
        ra = new RandomAccessFile(LOCATIONS_RAND, "rwd");
        int numLocations = ra.readInt(); // not needed but good practice to store num of records
        long locationsStartPoint = ra.readInt();
        while (ra.getFilePointer() < locationsStartPoint) {
          int locationId = ra.readInt();
          int locationStart = ra.readInt();
          int locationLength = ra.readInt();
          IndexRecord record = new IndexRecord(locationStart, locationLength);
          index.put(locationId, record); // the map we also used for writing in the former Main method
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

Reading in an entry using the index map:

    public Location getLocation(int locationId) throws IOException {
      IndexRecord record = index.get(locationId);
      ra.seek(record.getStartByte());
      int id = ra.readInt(); // same as locationId
      String description = ra.readUTF();
      String exits = ra.readUTF();
      String[] exitPart = exits.split(",");
      Location location = new Location(id, description, null);
      if (locationId != 0) { // the "quit" location
        for (int i = 0; i < exitPart.length; i += 2) {
          String direction = exitPart[i];
          int destination = Integer.parseInt(exitPart[i + 1]);
          location.addExit(direction, destination);
        }
      }
      return location;
    }


- the readUTF method knows the amount of data it must read because it creates its own header indicating the string length, followed by the string itself.

#### Java NIO

[good java.nio documentation @ jenkov.com](http://tutorials.jenkov.com/java-nio/index.html)

- introduced in Java 1.4
- improved Java IO: 
  - non-blocking: threads will not block the thread when waiting to read/write; criticisms include that non-blocking IO is slower than blocking IO
- more complex than java IO
- deals with data in blocks instead of characters and binary
- java.nio can be used to create a java.io stream
- two "buckets" of classes:
  - those dealing with file system
  - those dealing with reading and writing data to / from a datasource

- File path is a replacement for file class (should be used as it's more robust wherever possible):  
  
      Path locPath = FileSystems.getDefault().getPath("locations_big_test.txt");
      // or just Paths.get("locations_big.txt");
      BufferedWriter locFile = Files.newBufferedWriter(locPath);
      // rest follows (or can follow?) the same code as normal file access
      // using BufferedWriter

- to accomplish reading blocks, java.NIO uses channels and buffers and sometimes selectors
- channel is the datasource being read / written to (file, socket, ...)
- to use a datasource as a channel, a class that implements the java.NIO.Channel interface is needed
- buffer is a typed container for the data that's being r/w
- selectors manage single thread (?)
- only one datasource needed for read / write as opposed to 2 in java.IO; Exception from the rule: File input stream channel is only opened for reading 

Example read/write utf8

      Path dataPath = Paths.get(DATA_FILE);
      // this opens writes and closes the file
      // so when writing a large amount of lines,
      // a StringBuffer should be used to write them in one chung
      Files.write(dataPath, "\nLine 4".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
      
      // read it back in
      List<String> lines = Files.readAllLines(dataPath);
      for(String line: lines) {
          System.out.println(line);
      }

##### binary files

> A buffer is essentially a block of memory into which you can write data, which you can then later read again. This memory block is wrapped in a NIO Buffer object, which provides a set of methods that makes it easier to work with the memory block.  
>Using a Buffer to read and write data typically follows this little 4-step process:
>- Write data into the Buffer
>- Call buffer.flip()
>- Read data out of the Buffer
>- Call buffer.clear() or buffer.compact()

(http://tutorials.jenkov.com/java-nio/buffers.html)


- terminology:
  - buffer capacity: num of elements it can contain
  - buffer position: index of next element that should be read or written
  - buffers mark: used by buffers reset method; default when creating a buffer is `undefined`

- buffers must be flipped (reset) to the starting position explicitely when writing / reading data:
- alternatively , the index to start on can be passed to the readInt method (absolute read): `intBuffer.getInt(0)`
  - this also doesn't advance the buffers position
- don't mix relative and absolute read in code


      RandomAccessFile ra = new RandomAccessFile(DATA_FILE, "rwd");
      FileChannel channel = ra.getChannel();
      outputBytes[0] = 'a';
      outputBytes[1] = 'b';

      channel.read(buffer);
      System.out.println(new String(outputBytes)); // abllo World!
      buffer.flip(); // resets
      
      // channel data is read INTO buffer, think about it 
      // as buffer = channel.read()
      channel.read(buffer); 
      System.out.println(new String(outputBytes)); // Hello World!

- "If something goes wrong, flip()"
- can be used chained:

`buffer.put(outputBytes).putInt(245).putInt(-98765).put(outputBytes2).putInt(1000);`

6 Methods in the SeekableByteChannel interface:

- `read(ByteBuffer)` - reads bytes beginning at the channel's current position, and after the read,
                               updates the position accordingly.
                               Note that now we're talking about the channel's position, not the byte buffer's position.
                               Of course, the bytes will be placed into the buffer starting at its current position.
- `write(ByteBuffer)` - the same as read, except it writes. There's one exception.
                              If a datasource is opened in APPEND mode, then the first write will take place starting
                              at the end of the datasource, rather than at position 0. After the write, the position
                              will be updated accordingly.
- `position()` - returns the channel's position.
- `position(long)` - sets the channel's position to the passed value.
- `truncate(long)` - truncates the size of the attached datasource to the passed value.
- `size()` - returns the size of the attached datasource
- FileChannel class implements the SeekableByteChannel interface

##### Filechannel to copy files and pipes with threads

- see comments in code from 019h_JavaNio_continued for copy files
- see comments in code from 019i_JavaNio_pipes for thread communication with pipes

##### Filesystem

- package: java.nio.file
- Path interface: used to identify a file in the file system
- Each element of a path is a node, e.g. c\stuff\readme.txt has 3 nodes, a root node (c:), a directory node (stuff) and a file node (readme.txt)
- character to separate paths (e.g. "\" or "/" is called delimiter)
- paths can be created for directories or files
- can be relative
- a file under the default path (e.g. systems user directory) can be built with getDefault:  
  `Path dataPath = FileSystems.getDefault().getPath("data.txt");`
- when working in intellij, the default path (working directory) is the current project directory, so the above would be the same as  
  `Path dataPath = Paths.get("/pk/projects/IdeaProjects/Java_masterclass/019j_Paths/data.txt`
- path is abstract, doesn't know / check if file exists or path is "real"

Examples:

    Path path; // java.nio.file.Path
    path = FileSystems.getDefault().getPath("WorkingDirectory.txt");
    path = FileSystems.getDefault().getPath("files", "subdirectory.txt");
    //java.nio.file.Paths
    path = Paths.get("/home/pk/projects/IdeaProjects/Java_masterclass/OutThere.txt");
    path = Paths.get("/", "home", "pk", "projects", "IdeaProjects", "Java_masterclass", "OutThere.txt");
    path = Paths.get("jfdlkfsd", "doesn't exist");
    System.out.println("File path exists: " + Files.exists(path)); // true
    System.out.println("File path exists: " + Files.notExists(path)); // false

- Why another NIO Path(s) class, as java.IO.file also points to files in the filesystem?
  - many file methods don't throw exceptions or don't provide good error messages
  - File.rename works (I guess he means "has to be called") differently on different platforms
  - has no support for symbolic links
  - no way to get metadata of file (permissions, owner...)
  - bad performance
  
##### Copying / Moving / Renaming / Deleting files

    // Copying files
    Files.copy(sourceFile, copyFile); // exception if file exists
    Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);

    // copying directory
    Path sourceDirectory = Paths.get("Examples", "Dir2", "Dir3");
    Path copyDirectory = Paths.get("Examples", "Dir2", "Dir3_copy");
    Files.copy(sourceDirectory, copyDirectory); // copies directory but not contained files

    // move / rename
    Files.move(copyDirectory, Paths.get("Examples", "Dir2", "Dir4") );

    // delete
    // when deleting directories, they have to be empty
    Files.delete(Paths.get("Examples", "Dir2", "Dir4")); 
    Files.deleteIfExists(Paths.get("Examples", "Dir2", "Dir4"));

    // create file (this should usually be done by channel / stream and not explicitely
    Files.createFile(Paths.get("Examples", "file2.txt"));
    // create directory
    Files.createDirectory(Paths.get("Examples", "new_directory"));

    // creates "nested" and "directories" inside nested
    Files.createDirectories(Paths.get("Examples", "nested", "directories"));
    
    // Attributes
    long size = Files.size(filePath);
    FileTime modified = Files.getLastModifiedTime(filePath);

    // get all attributes in one object
    BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
    System.out.println("Last accessed: " + attrs.lastAccessTime() + ", Is a symbolic link: " + attrs.isSymbolicLink());

More methods for attributes etc on [Java Docs -> Java.NIO.Files](https://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html)

Note: move may not work in all OS with non-empty directories

##### Directory / DirectoryStreams

DirectoryStream

- returns an iterable stream with the direct descendants of the directory
- accepts an optional filter (glob) as a second parameter, similar to regex;
- "real" regex can be used as well
- Examples:
    - * = any string
    - *.dat
    - *.{dat,txt}
    - b???.txt
    - myFile*
    - b?*.txt // starts with b, at least one more character ("?"), zero or more additonal characters


    try (DirectoryStream<Path> contents = 
            Files.newDirectoryStream(directory, 
            "*.{dat,txt}")) {
      for (Path path : contents) {
        System.out.println(path.toString());
      }
    } catch (IOException | DirectoryIteratorException e) {
      //...

- advanced / attribute filters can be done with `DirectoryStream.Filter<Path>`, which has only one method (`accept`) to override:


      DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
          @Override
          public boolean accept(Path entry) throws IOException {
            return Files.isRegularFile(entry);
          }
      };
  
      // Same as Lambda:
      DirectoryStream.Filter<Path> lambdaFilter = p -> Files.isRegularFile(p);
      // Or even shorter with Method Reference:
      DirectoryStream.Filter<Path> lambdaFilterWithRef = Files::isRegularFile;
  
      try (DirectoryStream<Path> contents = Files.newDirectoryStream(directory, filter)) {
      //...

Creating temp files:

    Path tempFile = Files.createTempFile("myapp", ".appext");
    System.out.println(tempFile.toAbsolutePath());
    // -> e.g. /tmp/myapp17573002425864035770.appext

Walking a file tree:

- have to use FileVisitor interface
  - `preVisitDirectory()` - called before entries in directory are visited; this can be useful when copying file trees to copy / create the current top level directory of the traversal
  - `postVisitDirectory()` - called after entries in directory (and all descendants) are visited; usefule e.g. for deleting directories, as the descendants have to be deleted first before the current top level directory can be deleted itself
  - `visitFile()` - this is where you run the code that will operate on the file (only called for files)
  - `visitFileFailed()` - called when a file can't be accessed
- For examples (traversal, directory copy), see 019k_directories
- there is not any order that can be relied on during traversal

#### Mapping IO and NIO methods

- see 019k_directories

### File section Fazit:

Use NIO when working with the filesystem
Use IO when reading and writing files and performance is an issue

## 15 Concurrency
https://stackoverflow.com/questions/37813271/why-different-predicate-interfaces-n-java-8
Key terms:

- **process**: unit of execution that has it's own memory space
- **process** and **application** are mostly used interchangeably. Each application has it's own memory space of *heap* which isn't shared between applications
- **thread** unit of execution *within a process*. A process can have multiple threads. Each application / process has at least one thread, the **main thread** (in javaFX also called the **JavaFX application thread**). Almost all Java processes also have multiple system threads for memory management and IO which aren't explicitely created by the dev.
  - every thread shares the process's memory and files
  - **every thread has it's own thread stack but share the heap**
  - When to use threads?
    - perform long running tasks in the background instead of freezing up the main thread
    - the API requires us to provide a thread
- **concurrency** refers to an application doing more than one thing at a time (e.g. download data and draw a shape on the screen)
  - that doesn't mean that it necessarily does it "really" at the same time, but instead dedicates times slices to each task (e.g. download a little, draw a little etc)
  - that means a task doesn't have to be complete before another task can start
  
The JVM and OS ultimately decides when threads are scheduled to run, though we can influence it by setting priority.

*The order in which operations in multiple threads are executed can't be guaranteed*

### 2 Ways to start a thread

#### Subclassing `Thread`

The task we want to run in a thread must be wrapped in a subclass of Thread, and specifically in it's `run` method. This can also be done in an anonymous class (in that case it is / must be instantiated immediately). When using the same class, it must be instantiated for each new thread.
    
    // AnotherThread.java
    public class AnotherThread extends Thread {
      @Override
      public void run() {
        System.out.println("Hello from another thread");
      }
    }

    // Main.java
    public static void main(String[] args) {
      System.out.println("Hello from the main thread");
      Thread anotherThread = new AnotherThread();
      anotherThread.start();
      System.out.println("Hello again from the main thread");

      // basic example for an anonymous class' thread
      new Thread() {
        public void run() {
          System.out.println("Hello from the anonymous class thread");
        }
      }.start();
    }

#### Implementing `Runnable` (used more often?)

    // MyRunnable.java
    public class MyRunnable implements Runnable {
      @Override
      public void run() {
        System.out.println(ThreadColor.ANSI_RED + "Hello from MyRunnables implementation of run");
      }
    }

    //Main.java
    new Thread(new MyRunnable()).start();

    // new thread using anonymous Runnable class
    new Thread(
            new Runnable() {
              @Override
              public void run() {
                System.out.println(ThreadColor.ANSI_CYAN+"Hello from anonymous Runnable");
              }
            }).start();

- Common mistake: using the run() method instead of start(); This results in the thread being started on the thread that inits the thread, e.g. the main thread 

- Threads can be put to sleep (Thread.sleep depends on OS functions and length is not guaranteed) and can be woken up by other threads. See 020a_threads.
  - threads can know if they're being interruped by catching the `InterruptedException` or by checking the interrupted() method regularly 

- Threads can be joined to postpone operations until another thread is done; the join method can be passed a timeout value to avoid hanging if the joined thread never terminates
      
      //...
      public void run() {
        System.out.println(ThreadColor.ANSI_CYAN + "Hello from anonymous Runnable");
        try {
          anotherThread.join(1000); // wait max. 1 sec
          System.out.println(ThreadColor.ANSI_CYAN + "anotherThread terminated so I'm running again");
        } catch (InterruptedException e) {
          System.out.println(ThreadColor.ANSI_CYAN + "I couldn't wait. I was interrupted");
        }
      }
- priorities can be set with `setPriority()` (OS dependent and not always supported)

#### Thread Synchronization

- **every thread has it's own thread stack but share the heap**
  - instance variables of a shared object are stored on the heap (shared), so all threads using that object use the same variable, leading to **thread interference / race conditions** when both are writing a shared ressource / variable
  - local variables on the stack
- Thread can be suspended anywhere (unpredictably) by th jvm, even inside a for loop (e.g. after incrementing a counter, but before checking if the condition is met)
- Thread *can't* be interrupted during atomic operations:
  - reading and writing reference variables
  - reading and writing primitive variables *except* long and double as these need 2 operations internally
  - reading and writing all variables declared `volatile`
- Threads working with methods of the same object can be synchronized by adding `synchronized` to the methods signature:

`public synchronized void doCountdown() { //...`

- The object reference is stored on the stack, the object value on the heap
- Constructors can't be synchronized (which wouldn't make sense anyway)
- Code blocks can be synchronized as well, but only with instance (as opposed to local) variables (only objects, not primitives, as primitives don't have an internal lock state associated with them), as each thread gets it's own copy of local variables anyway.
  - exception: local string variables can be used to synchronize because of javas internal workings (string pools) (?)
  - this can be done with the reference of the object itself

    
    synchronized (this) {
      for (i = 10; i > 0; i--) { // uses instance variable i
        System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
      }
    }

or the not thread safe object that is accessed:

    synchronized (buffer) {
      buffer.add(num); // buffer is a List
    }

- drawbacks of `synchronized` blocks:

  - threads that are blocked can't be interrupted until they get the lock of the object they are synchronizing on
  - synchronized block must be in the same method
  - can't find out about lock status and timeout if necessary
  - no set order of threads that get the lock
  
- static objects and methods can be synchronized as well, in that case the Class owns the lock associated with the object's class
- A thread can acquire an object it already owns (?)
- always synchronize the smallest amount of code possible (don't oversynchronize), so for small functions `synchronized` in the method signature is fine, for larger ones use synchronized blocks to apply synchronization only to the relevant parts 

Terminology

- *Critical Section*: refers to code referencing a shared resource like a variable
- *Thread safe*: All critical sections are synchronized, so the dev using the code doesn't have to worry about thread interference

- *Some* collections such as `ArrayList` aren't thread safe / synchronized, so access to them must be either synchronized manually or use [Collections.synchronized*](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#synchronizedCollection-java.util.Collection-)
- None of the javaFX components are thread safe, that's the reason only the javafx main thread can change UI componentes
- When synchronizing code, *only* synchronize the parts that *needs* to be synchronized to avoid unnecessary blocking of threads

- Java provides methods wait, notify, notifyAll to avoid deadlocks caused by using loops to wait for input from another thread; see `020c_Messages_(Producer_consumer)` 

      public synchronized String read() {
        // loop until there is a message to read, used by consumer
        while (empty) {
          try {
            // avoid deadlocks; always call in a loop that checks wakeup
            // condition because thread may wake up for other reasons as well
            // before the condition is satisfied
            wait(); // avoid deadlocks
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        empty = true;
        // notify that method is done (BEFORE return);
        // we don't use notyfy(threadname) because we don't know it
        // (Thread doesn't accept any parameters we could pass such as the thread name)
        notifyAll();
        return message;
      }

### java.util.concurrent / Locks

#### Basics

- help developers to synchronize code
- different lock types available:
  - [java.util.concurrent.locks.ReentrantLock](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/ReentrantLock.html)
- drawback: puts the responsibility of releasing the lock on the developer

**<u>The following two code examples are not the intended way of using locks</u>**

- Example (bufferLock must be instantiated in main and passed to the Runnable classes passed to `new Thread`)

      bufferLock.lock();
      buffer.add(num);
      bufferLock.unlock();

- as a thread can acquire the lock that it already holds multiple times, such as in a loop that breaks before `lock.unlock()` is reached, the maximum number of locks can be exceeded, so they must be explicitely closed at the right points (also see 020d_concurrent_(producer_consumer)):

      while (true) {
        bufferLock.lock();
        if (buffer.isEmpty()) {
          bufferLock.unlock();
          continue;
        }
        if (buffer.get(0).equals(Main.EOF)) {
          System.out.println(color + "Exiting");
          bufferLock.unlock();
          break;
        } else {
          System.out.println(color + "Removed " + buffer.remove(0));
        }
        bufferLock.unlock();
      }

#### Using blocks with try/finally

    bufferLock.lock(); // OUTSIDE the try/finally
    try {
      buffer.add(num);
    } finally {
      bufferLock.unlock();
    }

additionally, tryLock() can be used to check AND lock if the lock is available; optionally, a timeout can be passed to tryLock that releases the lock automatically after a given amount of time, without parameters it doesn't honor fair ordering locks:

    if(bufferLock.tryLock()) { // without timeout
      // no extra bufferLock.lock() necessary
      try {
        if (buffer.isEmpty()) {
          continue;
        }
        if (buffer.get(0).equals(Main.EOF)) {
          System.out.println(color + "Exiting");
          break;
        } else {
          System.out.println(color + "Removed " + buffer.remove(0));
        }
      } finally {
        bufferLock.unlock();
      }
    }

[Lock Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/Lock.html)

### Thread Pools / ExecutorService

- Thread pool: managed pool of threads
- used through ExecutorService implementation
- the ExecutorService must be shut down explicitely, otherwise the application keeps running
  
      ExecutorService executorService = Executors.newFixedThreadPool(3);

      executorService.execute(producer);
      executorService.execute(consumer1);
      executorService.execute(consumer2);

      executorService.shutdown();

To get a result back from a thread using executorService, use .submit(Callable) to get a result of type `Future` back:

    Future<String> future = executorService.submit(new Callable<String>() {
      @Override
      public String call() throws Exception {
        System.out.println(ThreadColor.ANSI_WHITE+ "I'm being printed from the callable class");
        return "this is the callable result";
      }
    });

    try {
      String result = future.get();
      System.out.println(result);
    } catch (ExecutionException | InterruptedException e) {
      e.printStackTrace();
    }

- in JavaFX, the JavaFX.concurrent package should be used as it doesn't block the UI thread

[ExecutorService documentation](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html)

### ArrayBlockingQueue Class

See 020e_concurrent_ArrayBlockingQueue with comments

### Deadlocks

- occurs when 2 or more threads are blocked on locks (e.g. thread1 is holding lock on lock1 and waits, *nested*, for lock2, thread2 is holding lock2 and waits for lock1)
- possible solutions: make threads obtain locks in the same order
- applies e.g. to nested block acquisition

- another way for a deadlock to occur is 2 classes with synchronized methods calling methods from the other class
- see 020g_more_deadlocks_SayHello

- Strategies if deadlocked:
  - is a set of locks being obtained in a different order by multiple threads? If yes, can these be changed to obtain the locks in the same order?
  - Are we oversynchronizing the code?
  - Can we rewrite the code to break any circular call patterns?
  - would using ReentrantLocks help?
  - is there a wait() method in the code that releases a lock before exiting a synchronized block?

### Thread starvation

- threads don't lock but rarely have the opportunity to run
- often caused by thread priority (`threadObj.setPriority(int);`)
- thread priority is a suggestion to the OS and not binding

### Fair locks and live locks

#### Fair lock

- fair = first come, first served
- not all locks support fairness parameter
- example: `ReentrantLock(true);`
- only fairness in acquiring lock is guaranteed, not thread scheduling
- fair locks can impact performance as they add an extra layer

#### Livelock

> **Livelock** occurs when two or more processes continually repeat the same interaction in response to changes in the other processes without doing any useful work. These processes are not in the waiting state, and they are running concurrently. This is different from a deadlock because in a deadlock all processes are in the waiting state.  
https://www.geeksforgeeks.org/deadlock-starvation-and-livelock/

#### Slipped condition:

- can occur when a thread can be suspended between reading a condition and acting on it
- e.g. two threads read from buffer by 
  - checking status,
  - read buffer if OK
  - if data is EOF sets status to EOF and terminates, otherwise sets status to OK
- If not synchronized properly, this can happen:
  - t1 checks status, gets ok, suspends
  - t2 checks status, gets ok, reads EOF and sets status to EOF and terminates
  - t1 runs again and tries to read data from buffer (as it still "thinks" it's OK), but doesn't get any data and crashes
- Solution: synchronized / locks like always

  
### Other Thread issues

Problem:

- each thread has a cpu cache which can contain copies of values in main memory
- if threads run on different cpus (cores) with their own cache, the caches can become out of sync with each other
- this can happen even to atomic operations such as assigning / reading an `int` variable 

Solution(s):

- volatile variables (atomic action that can't be suspended);
- the JVM writes the value back to main memory immediately after the thread updates the value in its CPU cache, guaranteeing that every time a thread reads from a volatile variable, it gets the lates value.
- Usage. `public volatile int counter;`;
- Code using volatile variables must still be synchronized as if more than one thread can update a variable, we can still get a race condition
  - e.g. `counter++;`, which is a non-atomic operation (read counter value, add 1 to counter, write counter value back to memory)
- common usage for volatile: long or double variables (non-atomic)  
- when only 1 thread can change the value of a shared variable, or none of the threads updates the value of a shared variable in a way that depends on its existing value, using `volatile` means that we don't need to synchronize the code for this variable as the value in memory will always be the latest value.

- [java.util.concurrent.atomic](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/package-summary.html) provides classes to ensure that reading / writing variables is atomic (it provides limited amount of types)
- AtomicInteger can't be used as a substitute for an Integer object
- `compareAndSet(expected value, new value)` -> only updates if value is expected value

### JavaFX background threads

- [javafx.concurrent.Task class](https://docs.oracle.com/javase/8/javafx/api/javafx/concurrent/Task.html)
- every task that touches the UI must run on the JavaFX application thread
- in Controller, Platform.runLater can be used to directly change UI elements; this is however not good practice 
- A better way is to bind the data to the UI element

sample.fxml:

    <Button text="Get Employee names" onAction="#buttonPressed" GridPane.columnIndex="0" GridPane.rowIndex="0" prefWidth="400"/>
    <ProgressBar fx:id="progressBar" GridPane.columnIndex="0" GridPane.rowIndex="1" prefWidth="400" />
    <Label fx:id="progressLabel" GridPane.rowIndex="2" GridPane.columnIndex="0"/>
    <ListView fx:id="listView" GridPane.rowIndex="3" GridPane.columnIndex="0" prefWidth="400"/>



Controller.java:

    public class Controller {
      @FXML ProgressBar progressBar;
      private Task<ObservableList<String>> task;
      @FXML private ListView<String> listView;
      @FXML private Label progressLabel;
    
      public void initialize() {
    
        task =
            new Task<ObservableList<String>>() {
              @Override
              protected ObservableList<String> call() throws Exception {
                String[] names = {"Peer", "Tim", "Jack", "Bill", "crazy german christina", "bob"};
    
                ObservableList<String> employees = FXCollections.observableArrayList();
    
                for (int i = 0; i < names.length; i++) {
                  employees.add(names[i]);
                  updateProgress(i + 1, 6);
                  updateMessage("Added " + names[i] + " to the list");
                  //updateValue(employees); throws exception as it's not a fx application thread
                  Thread.sleep(200);
                }
                // just reset
                updateProgress(0, 6);
    
                return employees;
              }
            };
    
        // Bind listview to the returned value of the task so it
        // updates when ready
        listView.itemsProperty().bind(task.valueProperty());
        progressBar.progressProperty().bind(task.progressProperty());
        progressLabel.textProperty().bind(task.messageProperty());
      }
    
      @FXML
      public void buttonPressed() {
        new Thread(task).start();
      }
    }

- One more way is to use `javafx.concurrent.Service` - **recommended way** as it allows the javafx runtime to manage threads for us

EmployeeService.java:

    public class EmployeeService extends Service<ObservableList<String>> {
      @Override
      protected Task<ObservableList<String>> createTask() {
        return new Task<ObservableList<String>>() {
          @Override
          protected ObservableList<String> call() throws Exception {
            //...
            // same as in tasks example
            return employees;
          }
        };
      }
    }

Controller.java:

    public class Controller {
      @FXML ProgressBar progressBar;
      @FXML private ListView<String> listView;
      @FXML private Label progressLabel;
      private Service<ObservableList<String>> service;
    
      public void initialize() {
        service = new EmployeeService();
        listView.itemsProperty().bind(service.valueProperty());
        progressBar.progressProperty().bind(service.progressProperty());
        progressLabel.textProperty().bind(service.messageProperty());
        progressBar.visibleProperty().bind(service.runningProperty());
        progressLabel.visibleProperty().bind(service.runningProperty());  
      }
    
      @FXML
      public void buttonPressed() {
        service.start();
      }
    }

- for refined code with disabled progressbar etc. refer to 020j_javafx_task_example

### sidenotes:

- for coloring console output, look in ThreadColor.java in 020a_threads (Just prepend to the output string)

## Lambda Expressions and functional interfaces

### Lambda expressions

- very similar to JS arrow functions
- are always optional (syntactic sugar) as we can still always use anonymous classes in their stead
- remove boilerplate code, e.g. for anonymous Runnable classes for threads:

      // Anonymous class
      new Thread(new Runnable() {
        @Override
        public void run() {
          // this is the only non-boilerplate code
          System.out.println("printing from the Runnable anon");
        }
      }).start();
      
      // as a Lambda expression:
      new Thread(() -> System.out.println("Printing from lambda runnable")).start();

- 3 parts:
  - argument list
  - arrow token
  - body

- [method references](https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html) can be used to make it even more to the point (single argument is implied):

      // these all have the same result
      myList.forEach(String s -> s.toUpperCase());
      myList.forEach(s -> s.toUpperCase());
      myList.forEach(String::toUpperCase);
      


When the compiler sees a Lambda expression in the above example, what does it do?

- It knows that one of the Thread constructors accepts a Runnable as a parameter
- it knows Runnable interface has only one method
- this way, it can match the body of the Lambda to the body of the only method of the interface that has to be implemented

Because of this, Lambda expressions can only be used with functional interfaces. 

- An interface with only single abstract method is called functional interface(or Single Abstract method interface), for example: Runnable, callable, ActionListener etc. Exceptions are functional interfaces such as [Comparator<T>](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html), even though they have multiple methods (here: `compareTo`and `equals`).
- We can use a Lambda here because `equals` always has a default implementation inherited from `Object`, so the only other method `compareTo` is unambigeous. Here, also the parameter types can be inferred by the compiler (`Employee`)

      // Comparator using an anonymous class
      Collections.sort(employees, new Comparator<Employee>() {
        @Override
        public int compare(Employee employee1, Employee employee2) {
          return employee1.getName().compareTo(employee2.getName());
        }
      });
      
      // as a Lambda, overriding compareTo:
      Collections.sort(employees, (employee1, employee2)->employee1.getName().compareTo(employee2.getName()));

- IntelliJ shows which method is overridden when clicking on the gutter icon beside the line number; If it's not shown, search "gutter" in project settings and enable lambda there (seems to be disabled by default)
- like in JS, if there are multiple statements in the lambda's body, use curly braces:

      new Thread(() -> {
        System.out.println("Printing from multiline lambda runnable");
        System.out.println("Printing more from multiline  lambda runnable");
      }).start();

- this is to be avoided as lambda expressions are meant to be concise and a replacement only for short function bodies 
- for single parameters, parameter paranthesis can be omitted:

      clickMeButton.setOnAction((e)-> System.out.println("Button clicked with cool kid lambda"));

- Own interfaces can be implemented on the fly as well:


    interface UpperConcat {
      public String upperAndConcat(String s1, String s2);
    }
    
    public final static String doStringStuff(UpperConcat uc, String s1, String s2) {
      return uc.upperAndConcat(s1,s2);
    }
    
    UpperConcat uc = (s1,s2) -> (s1+s2).toUpperCase();

    // which now can be used like so:
    String sillyString2 = doStringStuff(uc, "one String", "And another");

    // or, as an anonymous implementation:
    String sillyString2 = doStringStuff((s1,s2) -> (s1+s2).toUpperCase(), "one String", "And another");

- A Lambda expression isn't a class but an expression (?)
- Lambdas are treated like a nested code block {}

- Variables from outside the scope of the lambda that are used IN the lambda must be final (or effectively final, meaning not being changes in any place in the code)
- Variables are remembered by the lambda even if the scope the variable comes from doesn't exist anymore:


      public void printValue() {
        int number = 25;
        Runnable r = () -> {
          try {
            Thread.sleep(5000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println("the value is " + number); //25
        };
        new Thread(r).start();
        // even though the function terminates before the thread 
        //prints the number, it will still print 25
      }

- in enhanced for loops, a new variable is created for each iteration, so this still works:


      for(Employee employee: employees) {
        // employee is effectively final so it can be accessed
        // in the Lambda expression
        System.out.println(employee.getName());
        new Thread(()-> System.out.println(employee.getAge())).start();
      }

- Since Java 8, functional interfaces have enhanced collections such as lists which now also have forEach etc. methods that can be used with lambdas:

      employees.forEach(employee -> System.out.println(employee.getName()));



### Functional interfaces & predicates

Predicates: lambda expressions or (anonymous) class that match the [java.util.function.predicate](java.util.function.predicate) interface that accepts one parameter and returns boolean
    
    // with Lambda
    printEmployeesByAge(employees, "Employees 30 and under", e->e.getAge()<=30);
    // with anonymous class
    printEmployeesByAge(employees, "Employees between 20 and 30", new Predicate<Employee>() {
      @Override
      public boolean test(Employee employee) {
         return employee.getAge()>=20 && employee.getAge()<=30;
      }
    });

    // example function just for reference
    private static void printEmployeesByAge(List<Employee> employees, String ageText, Predicate<Employee> ageCondition) {
      System.out.println(ageText);
      for(Employee employee: employees) {
        if(ageCondition.test(employee)) {
          System.out.println(employee.getName());
        }
      }
    }

There are "specialized" predicates such as `IntPredicate`, which exist to avoid unnecessary autoboxing / unboxing when working with primitives for performance reasons.

    IntPredicate intp = i -> i > 15;
    System.out.println(intp.test(10)); // false

Predicates can be chained with logical operators such as `.and` and `.or`:

    IntPredicate gt15 = i -> i > 15;
    IntPredicate lt30 = i -> i < 30;
    System.out.println(gt15.and(lt30).test(50));
    
Supplier interface (code should explain most of it):
    
    Supplier<Integer> supplier = ()->new Random().nextInt(1000);

    for (int i = 0; i<10;i++) {
      System.out.println(supplier.get());
    }

- Suppliers don't allow parameters
- Are often used in testing
- have specialized versions such as `IntSupplier`, `BooleanSupplier` etc:


    IntSupplier supplier = ()->new Random().nextInt(1000);
    for (int i = 0; i<10;i++) {
      System.out.println(supplier.getAsInt());
    }

To use parameters, use the `Function` interface with generics, indicating the parameter type and the return type separated by comma.  
Functions must be called with apply `fname.apply(argument)` and not like a method `fname(argument)`

    // get the last name of employee by questionable means
    Function<Employee, String> getLastName =
            employee -> employee.getName().substring(employee.getName().indexOf(' ') + 1);
    
    // first name function for later use    
    Function<Employee, String> getFirstName =
        employee -> employee.getName().substring(0,employee.getName().indexOf(' '));

    String lastName = getLastName.apply(employees.get(0));

Functions can be passed as parameters as well:

    // class method
    private static String getAName(Function<Employee, String> getName, Employee employee) {
      return getName.apply(employee);
    }

    // in Main or wherever:
    System.out.println(getAName(getFirstName, john)); // John
    System.out.println(getAName(getLastName, john)); // Doe

... which makes them, of course, useful for callbacks.

Functions without parameters can use the java.util.Supplier interface, where only the return type is defined, and are called with `.get()` instead of `apply`:

    Supplier<String> iLoveJava = () -> "I love Java";
    System.out.println(iLoveJava.get());

Functions can be chained together like predicates:

    Function<Employee, String> upperCase = employee -> employee.getName().toUpperCase();
    Function<String, String> firstName = name -> name.substring(0, name.indexOf(' '));
    // using andThen, function evaluation is left --> right
    Function<Employee, String> chainedFunction = upperCase.andThen(firstName);
    // using compose, function evaluation is left <-- right (right one first) 
    Function<Employee, String> composedVersion = firstName.compose(upperCase);

    System.out.println(chainedFunction.apply(john)); // JOHN
    System.out.println(composedVersion.apply(john)); // JOHN

Functions with multiple arguments can be done with `BiFunction`

    BiFunction<String, Employee, String> concatAge =
            (String name, Employee employee) -> name.concat(" " + employee.getAge());
    String upperName = upperCase.apply(john);
    System.out.println(concatAge.apply(upperName, john)); // JOHN DOE 30

BiFunctions can only be the first function in a chain so it doesn't have a compose method, only andThen.

Unary operators (spezialization of function that operates on a single operand and returns a result of the same type as the operand)

    IntUnaryOperator incBy5 = i->i+5;
    System.out.println(incBy5.applyAsInt(10));

Overview of the Java.util.function interfaces:

![Overview of the Java.util.function interfaces](images/function.png "Overview of the Java.util.function interfaces")

### Streams

#### Overview

- sequence of chained computations
- [Stream interface docu](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)
- set of object references
- each step of a stream operation must be stateless (not depending on state outside of the operation)
- each stream operation works on the results of the last step in the chain
- each stream method (map, filter, forEach etc.) return a new stream (note that the Stream class forEach is not the same as the `Collection`s forEach)
- forEach doesn't return a value so it must be the last step (terminal operation) as opposed to intermediate operations

Example (print a sorted list of items starting with "G"):
    
    List<String> someBingoNumbers =
        Arrays.asList("N40", "B10", "N46", "B6", "g53", "G49", "G60", "I17", "I26", "O71");
    someBingoNumbers
                .stream()
                .map(String::toUpperCase) // method reference notation, same as s -> s.toUpperCase()
                .filter(n -> n.startsWith("G")) 
                .sorted()
                .forEach(s -> System.out.println(s)); // terminal operation


![Example explanation](images/streams.png "Example streams")

Streams can be instantiated using `of`:

    Stream<String> ioNumberStream = Stream.of("I26", "I17", "I29", "O71");
    Stream<String> inNumberStream = Stream.of("N40", "N36", "I26", "I17", "I29", "O71");
    Stream<String> concatStream = Stream.concat(ioNumberStream, inNumberStream);
    System.out.println(concatStream.distinct().count());

To use side effect in an intermediate operation, e.g. printing it out, either `peek` or just `map` with a return in the lambda function body can be used:

    concatStream.map(s->{System.out.println(s); return s; }).doOtherStreamStuff();
    concatStream.peek(System.out::println);

`.peek` is mostly used for debugging to view intermediate results of stream operations

#### FlatMap / collect / reduce

`FlatMap` intended use: flattens nested lists (or rather streams)

`departments.stream().flatMap(department -> department.getEmployees().stream())
    .forEach(System.out::println);`

[collect / Collectors](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html) is used to reduce and/or store the content of the stream at the end of the chain

Example using `collect` with `Collectors.toList()`, see other interesting examples in doc (groupBy etc)

    List<String> sortedGNumbers =
                someBingoNumbers.stream()
                        .map(String::toUpperCase)
                        .filter(s -> s.startsWith("G"))
                        .sorted()
                        .collect(Collectors.toList());

Example using supplier, accumulator and collector as arguments (supplier (creates objects), accumulator (used to add single item), combiner (used by java to improve efficiency of operation))

    List<String> sortedGNumbers =
            someBingoNumbers.stream()
                    .map(String::toUpperCase)
                    .filter(s -> s.startsWith("G"))
                    .sorted()
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

`reduce`: 3 versions; example of version that accepts "by function":
    
    // get younges employee
    departments.stream()
            .flatMap(department -> department.getEmployees().stream())
            .reduce((em1, em2) -> em1.getAge() < em2.getAge() ? em1 : em2)
            .ifPresent(System.out::println); 

This reduce version has an optional result so ifPresent is used to do the terminal operation of the stream.

- Streams can't be reused
- operations in a stream are lazily evaluated, meaning they are not performed until there's a terminal operation
  
      Stream.of("ABC", "AC", "BAA", "CCCC", "XY", "ST")
          .filter(
              s -> {
                // this doesn't print out anything if we leave off 
                // the forEach at the end
                System.out.println(s); 
                return s.length() == 3;
              })
          .count(); // terminal operation

- Lists of numbers have more specialized interfaces (IntStream, LongStream...) with additional methods
- parallel streams to increase performance

### Lambda best practices

- same as in JS
- don't abuse lambdas for long functions;
- specify parameter types if it makes it easier to read 
- use return statement if it makes it easier to understand; use the same style in the same file



### Intellij sidenote: if classes in package aren't recognized (but the project still runs), use file-> invalidate caches