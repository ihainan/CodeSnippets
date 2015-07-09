package me.ihainan.tij.chapter9.section1.exercise3;

/**
 * 第九章 - 练习3
 */

abstract class BaseClass{
    public abstract void print();
    public BaseClass(){print();}    // 抽象类内的抽象函数也是可以被其他抽象函数调用的
}

class DerivedClass extends BaseClass{
    public int x = 2;

    @Override
    public void print() {
        System.out.println("x = " + x);
    }
}

public class Solution {
    public static void main(String[] args){
        DerivedClass derivedClass = new DerivedClass();
        derivedClass.print();
    }
}
/* Output
x = 0   // 先执行基类的构造函数，构造函数中调用的非抽象方法会被替换成导出类中的方法
x = 2
*/