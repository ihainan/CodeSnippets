package me.ihainan.tij.chapter7;

/**
 * Chapter 7 - Exercise 13
 */
public class Exercise_13 {
    static class ClassA{
        public void f(int x){
            System.out.println("Int - " + x);
        }

        public void f(float x){
            System.out.println("Float - " + x);
        }

        public void f(String x){
            System.out.println("String - " + x);
        }
    }

    static class ClassB extends ClassA{
        public void f(boolean x){
            System.out.println("Float - " + x);
        }
    }
    public static void main(String[] args){
        ClassB classB = new ClassB();
        classB.f(true);
        classB.f(1);
        classB.f("Hello World");
        classB.f(0.1f);
    }
}
