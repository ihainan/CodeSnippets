package me.ihainan.tij.chapter10.section1;

/**
 * *************** Exercise 1 *****************
 * Write a class named Outer containing an
 * inner class named Inner. Add a method to Outer
 * that returns an object of type Inner. In
 * main(), create and initialize a reference to
 * an Inner.
 * *********************************************
 */

public class Example_1 {
    int y;
    public class Inner {
        public int x = 1;
        public void f(){
            System.out.println(y);
        }
    }

    public Inner getInner() {
        return new Inner();
    }

    public static void main(String[] agrs) {
        Example_1 example_1 = new Example_1();
        Inner inner = example_1.getInner();
        System.out.println(inner.x);
        inner.f();                       
    }
}
