package me.ihainan.tij.chapter9.section1;

/**
 * Created by ihainan on 7/8/15.
 */
public class Program_1 {
    static abstract class ClassA{
        public abstract void f();
    }

    static class ClassB extends ClassA{

        @Override
        public void f() {
            System.out.println("Hello World");
        }
    }

    static abstract class ClassC{

    }

    public static void main(String[] args){
        ClassA classA = new ClassA() {
            @Override
            public void f() {
                System.out.println("Hello World From ClassA");
            }
        };
        classA.f();
        ClassB classB = new ClassB();
        classB.f();
        ClassC classC = new ClassC(){};
    }
}
