/**
 * Chapter 7 - Exercise 7
 */
package me.ihainan.tij.chapter7;



public class Exercise_7 {
    static class A {
        public A(int i) {
            System.out.println("From A1");
        }
        public A(){ System.out.println("From A2"); }
    }

    static class B {
        public B(String s) {
            System.out.println("From B1");
        }
        public B(){ System.out.println("From B2"); }
    }

    static class C extends A {
        B b = new B("String");
        B b2 = new B();

        public C(int i) {
            super(i);   // 无论如何，父类肯定会优先初始化（错误！！）
            System.out.println("From C1");
        }

        public C() {
            System.out.println("From C2");
        }
    }

    public static void main(String[] args){
        C c1 = new C();
        System.out.println();
        C c2 = new C(1);
    }
}
