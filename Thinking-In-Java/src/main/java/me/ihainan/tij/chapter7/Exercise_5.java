package me.ihainan.tij.chapter7;

/**
 * Chapter 7 - Exercise 5
 */

class A {
    public A() {
        System.out.println("From A");
    }
}

class B {
    public B() {
        System.out.println("From B");
    }
}

class C extends A {
    B b = new B();
}

public class Exercise_5 {
    public static void main(String[] args) {
        C c = new C();
    }
}
