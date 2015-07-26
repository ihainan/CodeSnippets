package me.ihainan.tij.chapter12.section4;

/**
 * 自定义异常演示程序
 */

/**
 * 自定义异常，两个构造器
 */
class SimpleException extends Exception {
    public SimpleException() {
    }

    public SimpleException(String msg) {
        super(msg);
    }
}

public class CustomException {
    public static void f() throws SimpleException {
        throw new SimpleException();
    }

    public static void g() throws SimpleException {
        throw new SimpleException("Exception with extra message");
    }

    public static void main(String[] args) {
        /* 测试一 */
        try {
            f();
        } catch (SimpleException e) {
            // System.out.println("Message :" + e);
            // System.out.println("Class :" + e.getClass());
            e.printStackTrace();
        } finally {
            System.out.println("Executing finally block");
        }

        /* 测试二 */
        try {
            g();
        } catch (SimpleException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Executing finally block");
        }
    }
    /* Output
    Executing finally block
    me.ihainan.tij.chapter12.section4.SimpleException
    Executing finally block
        at me.ihainan.tij.chapter12.section4.CustomException.f(CustomException.java:14)
        at me.ihainan.tij.chapter12.section4.CustomException.main(CustomException.java:23)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
        at java.lang.reflect.Method.invoke(Method.java:597)
        at com.intellij.rt.execution.application.AppMain.main(AppMain.java:134)
    me.ihainan.tij.chapter12.section4.SimpleException: Exception with extra message
        at me.ihainan.tij.chapter12.section4.CustomException.g(CustomException.java:18)
        at me.ihainan.tij.chapter12.section4.CustomException.main(CustomException.java:32)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
        at java.lang.reflect.Method.invoke(Method.java:597)
        at com.intellij.rt.execution.application.AppMain.main(AppMain.java:134)
     */
}
