package me.ihainan.tij.chapter9.section2.anotherpackage;

import me.ihainan.tij.chapter9.section2.MyNewInterface;
import me.ihainan.tij.chapter9.section2.MyTestClass;

/**
 * Section 2 - 接口验证程序 - 证明 TiJ 是错的（抠鼻）
 */
public class Test {
    public static void main(String[] args) {
        MyTestClass myTestClass = new MyTestClass();
        myTestClass.f();    // f 默认是 public 的！
        System.out.println(MyTestClass.publicX);
        System.out.println(MyNewInterface.x);
    }
}

/* Output:
Invoke f()
3
2
*/