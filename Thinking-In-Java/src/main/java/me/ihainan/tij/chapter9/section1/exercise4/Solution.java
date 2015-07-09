package me.ihainan.tij.chapter9.section1.exercise4;

/**
 * *************** Exercise 4 *****************
 * Create an abstract class with no methods.
 * Derive a class and add a method. Create a
 * static method that downcasts a reference from
 * the base class to the derived class and calls
 * the method. Demonstrate that it works in main().
 * Eliminate the need for the downcast by moving
 * the abstract declaration to the base class.
 * *********************************************
 */

abstract class BaseClass {
    // Nothing here
}

class DerivedClass extends BaseClass {
    public void f() {
        System.out.println("Hello World");
    }
}

abstract class NewBaseClass {
    public abstract void f();
}

class NewDerivedClass extends NewBaseClass {

    @Override
    public void f() {
        System.out.println("Hello World");
    }
}

public class Solution {
    public static void f(BaseClass myClass) {
        ((DerivedClass) myClass).f();
    }

    public static void newF(NewBaseClass myClass) {
        myClass.f();
    }

    public static void main(String[] args) {
        BaseClass myClass = new DerivedClass();
        f(myClass);
        NewBaseClass myClass2 = new NewDerivedClass();
        newF(myClass2);
    }
}

/* Output:
Hello World
Hello World
*/
