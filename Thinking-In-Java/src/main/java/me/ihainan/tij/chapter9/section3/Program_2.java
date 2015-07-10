package me.ihainan.tij.chapter9.section3;

/**
 * 测试接口作为函数参数的无聊程序
 */

interface MyInterface {
    public void f();
}

class ClassA implements MyInterface {

    @Override
    public void f() {
        System.out.println("Invoke f() method in ClassA");
    }
}

class ClassB implements MyInterface {

    @Override
    public void f() {
        System.out.println("Invoke f() method in ClassB");
    }
}

public class Program_2 {
    public static void f(MyInterface i) {
        i.f();
    }

    public static void main(String[] args) {
        f(new ClassA());
        f(new ClassB());
    }
}
