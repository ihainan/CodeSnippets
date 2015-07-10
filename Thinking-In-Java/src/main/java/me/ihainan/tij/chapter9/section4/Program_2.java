package me.ihainan.tij.chapter9.section4;

/**
 * 测试多种继承中的菱形问题
 */

class BaseClass{
    public void f(){
        System.out.println("Invoke f from BaseClass");
    }
}

class DerivedClass_1 extends BaseClass{

}

class DerivedClass_2 extends BaseClass{

}

// class finalClass extends DerivedClass_1, DerivedClass_2  // 错误，不能继承多个类，只能继承接口

public class Program_2 {
}
