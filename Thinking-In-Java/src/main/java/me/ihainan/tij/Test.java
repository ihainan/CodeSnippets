package me.ihainan.tij;

/**
 * 测试用程序
 */

class MyClass2{
    public double y;
}

class MyClass{
    public int x;
    public MyClass2 myClass2;
    public void f(){
        x++;
        System.out.println(x);
    }
}

public class Test {
    public static void main(String[] args){
        MyClass myClass = new MyClass();
        myClass.f();
    }
}
