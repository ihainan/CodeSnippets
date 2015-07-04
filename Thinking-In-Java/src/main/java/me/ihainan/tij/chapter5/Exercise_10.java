package me.ihainan.tij.chapter5;

/**
 * 验证消息回收机制
 */
public class Exercise_10 {
    public static class MyClass{
        @Override
        protected void finalize() throws Throwable {
            System.out.println("Execute finalize()");
            super.finalize();
        }
    }
    public static void main(String[] args){
        MyClass myClass = new MyClass();
        System.gc();    // Why it doesn't work?
        System.runFinalization();
    }
}
