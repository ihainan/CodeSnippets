package me.ihainan.tij.chapter7;

/**
 * Created by ihainan on 7/6/15.
 */
public class Exercise20_21 {
    public static class ClassA {
        private static void f() {
            System.out.println("Invoke f method in ClassA");
        }

        public void f2() {
            System.out.println("Invoke f2 method in ClassA");
        }
    }

    public static class ClassB extends ClassA {
        private static void f() {
            System.out.println("Invoke f method in ClassB");
        }

        // @Override // 不能shiyong
        public static void test() {
            f();
        }

        @Override
        public void f2() {
            System.out.println("Invoke f2 method in ClassA");
        }

    }

    public static void main(String[] args) {
        ClassB classB = new ClassB();
        classB.test();
    }
}
