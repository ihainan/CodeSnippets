package me.ihainan.tij.chapter9.section2;

/**
 * 验证接口的一个无聊程序
 */

// interface 和 class 一样，一个 .java 文件中只能有一个 public 的 class 或者 interface
// public
interface MyInterface {
    public void f();        // 不用加上 abstract 关键字

    // public void f2(){};  // 错误，不能实现
    // private void f3();   // 错误，方法不能是私有的
    void f4();              // 可以是包访问权限

    int x = 3;              // 包访问权限
    public int z = 4;       // 域，隐性变为 final 和 static
}

class MyClass implements MyInterface {

    @Override
    public void f() {
        System.out.println("Invoke f()");
    }

    @Override
    public void f4() {
        System.out.println("Invoke f4()");
    }
}

class MyNewClass implements MyNewInterface{

    @Override
    public void f() {
        System.out.println("Invoke f()");
    }

    @Override
    public void publicF() {
        System.out.println("Invoke publicF()");
    }
}

public class Program_1 {
    public static void main(String[] agrs) {
        MyClass myClass = new MyClass();
        System.out.println(MyClass.z);  // 抽象静态成员

        // 测试权限
        MyNewClass myNewClass = new MyNewClass();
        myNewClass.f();
        myNewClass.publicF();
        System.out.println(MyNewClass.x);
        System.out.println(MyNewClass.publicX);
    }
}
/* Output:
4
Invoke f()
Invoke publicF()
2
3
*/
