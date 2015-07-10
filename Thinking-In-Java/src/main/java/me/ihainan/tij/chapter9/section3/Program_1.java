package me.ihainan.tij.chapter9.section3;

/**
 * 测试抽象类作为函数参数的无聊程序.
 * 实际上就是高大上的策略设计模式
 */

abstract class MyAbstractClass {
    public abstract void f();
}

class DerivedClass extends MyAbstractClass {

    @Override
    public void f() {
        System.out.println("Invoke f() method in DerivedClass");
    }
}

class NewDerivedClass extends MyAbstractClass {

    @Override
    public void f() {
        System.out.println("Invoke f() method in NewDerivedClass");
    }
}

public class Program_1 {
    public static void f(MyAbstractClass myObject) {
        myObject.f();
    }

    public static void main(String[] args) {
        DerivedClass derivedClass = new DerivedClass();
        f(derivedClass);
        NewDerivedClass newDerivedClass = new NewDerivedClass();
        f(newDerivedClass);
    }
}
