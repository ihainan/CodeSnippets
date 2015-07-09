package me.ihainan.tij.chapter9.section2;

/**
 * Created by ihainan on 7/8/15.
 */
public class MyTestClass implements MyNewInterface {
    @Override
    public void f() {
        System.out.println("Invoke f()");
    }

    @Override
    public void publicF() {
        System.out.println("Invoke publicF()");
    }
}
