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
- precedence: as usual, https://introcs.cs.princeton.edu/java/11precedence/
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

#### non static nested class:

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

- **Packages**: always lower case, unique, internet domain name reversed as prefix (de.pkro.mypackage), no dashes / starting numbers (replace / prepend with _), start with _ if it clashes with java keyword ,e.g. 1-world.com -> com._1_world
- **Class names**: CamelCase, UcFirst, should be nouns
- **Interfaces**: UcFirst
  - Consider what object implementing the interface will become / be able to do:
    - List
    - Comparable
    - Serializable
    - etc.
- **Method names**: mixedCase, often verbs
- **Constants (static final)**: all uppercase, _ to separate (e.g. MAX_CASE)
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

#### creating a jar file from a package:

- File -> project structure -> artefacts -> + button -> JAR -> From module with dependencies
- Indicate main class to make executable if desired
- extract to target jar
- ok
- build -> build artefacts -> build
- jar is created in /out/artifacts/

#### Use the created package (new project or existing):

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

- *"unified architecture for representing and manipulating collections."* - (https://docs.oracle.com/javase/tutorial/collections/)
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
- Download sdk for OS at https://gluonhq.com/products/javafx/
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
- change spacing of elements (inside the element tag): ` prefWidth="90"`

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

- Old and ugly javafx graphics library: https://repo1.maven.org/maven2/net/java/linoleum/jlfgr/1_0/ (add with module settings)
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

UI changes *must* be done in the UI (application) thread to avoid colisions (Java will throw an exception if changes are attempted in a new Runnable thread). The current type of thread can be checked using `Platform.isFxApplicationThread()`:

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

**This code isn't working on linux, so a workaround is used using apache commons**

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

https://www.tutorialspoint.com/how-to-add-data-to-a-tableview-in-javafx

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

