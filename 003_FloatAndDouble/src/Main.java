public class Main {
  public static void main(String[] args) {

    float floatMax = Float.MAX_VALUE;
    float floatMin = Float.MIN_VALUE;
    double doubleMax = Double.MAX_VALUE;
    double doubleMin = Double.MIN_VALUE;

    System.out.println("float min: " + floatMin);
    System.out.println("float max: " + floatMax);
    System.out.println("double min: " + doubleMin);
    System.out.println("double max: " + doubleMax);

    int myInt = 5;
    float myFloat = 5;
    double myDouble = 5;

    // myFloat = 5.3; // error as 5.3 is interpreted as double (default data type
    // for fp numbers)
    myFloat = 5.3f; // ok
    myFloat = (float) 5.3; // ok
    myFloat = 5;
    myDouble = 5.3; // ok
    myDouble = 5.3d; // good for documentation

    myInt = 5 / 3; // 1
    myFloat = 5f / 3; // 1.6666666
    myDouble = 5d / 3; // 1.6666666666666667

    System.out.println("myInt: " + myInt);
    System.out.println("myFloat: " + myFloat);
    System.out.println("myDouble: " + myDouble);

  }
}
