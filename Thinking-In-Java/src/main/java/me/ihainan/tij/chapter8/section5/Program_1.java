package me.ihainan.tij.chapter8.section5;

/**
 * 测试向下转型与运行时类型识别（RTTI）
 */

class Useful {
    public void f() {
        System.out.println("Invoke method f() in class Useful");
    }

    public void g() {
        System.out.println("Invoke method g() in class Useful");
    }
}

class MoreUseful extends Useful {
    public void f() {
        System.out.println("Invoke method f() in class MoreUseful");
    }

    public void g() {
        System.out.println("Invoke method g() in class MoreUseful");
    }

    public void u() {
        System.out.println("Invoke method u() in class MoreUseful");
    }
}

public class Program_1 {
    public static void main(String[] args) {
        Useful[] usefuls = new Useful[]{
                new Useful(),
                new MoreUseful()
        };

        // usefuls[0].u(); // 错误，没有该方法
        // usefuls[1].u(); // 错误，没有该方法

        ((MoreUseful) usefuls[1]).u();
        ((MoreUseful) usefuls[0]).u();  // 抛出异常
    }
}

/* Output:
Invoke method u() in class MoreUseful
Exception in thread "main" java.lang.ClassCastException: me.ihainan.tij.chapter8.section5.Useful cannot be cast to me.ihainan.tij.chapter8.section5.MoreUseful
	at me.ihainan.tij.chapter8.section5.Program_1.main(Program_1.java:28)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
	at java.lang.reflect.Method.invoke(Method.java:597)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:134)
*/
