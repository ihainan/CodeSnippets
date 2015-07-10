package me.ihainan.tij.chapter9.section4;

/**
 * 测试多重继承的无聊程序
 */

interface MyInterface1 {
    public static int x = 1;
    public static int y = 2;

    public void f1();

    // public void f_diff_return();
}

interface MyInterface2 {
    public static int x = 2;

    public void f();

    // public int f_diff_return();
}

class MyClass {
    public void f() {
        System.out.println("Hello World");
    }   // 刻意重名
}

class MyFinalClass extends MyClass implements MyInterface1, MyInterface2 {

    @Override
    public void f1() {
        System.out.println("Hello World once again");
    }

    /*
    @Override
    public void f_diff_return() {

    }
    */
}

class MyNewClass extends MyClass implements MyInterface1 {
    public static int x = 3;

    @Override
    public void f1() {

    }
}

public class Program_1 {
    public static void main(String[] args) {
        MyFinalClass myFinalClass = new MyFinalClass();
        myFinalClass.f();
        myFinalClass.f1();
        // System.out.println(myFinalClass.x);  // 错误，无法拿到 x
        // System.out.println(myFinalClass.y);
        System.out.println(MyInterface1.x);
        MyNewClass myNewClass = new MyNewClass();
        System.out.println(myNewClass.x);
    }
}
