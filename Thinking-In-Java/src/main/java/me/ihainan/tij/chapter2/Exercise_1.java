package me.ihainan.tij.chapter2;


/**
 * Exercise 2-1，测试类字段和局部变量是否初始化所造成的影响
 */
public class Exercise_1 {
    static class TestClass{
        int x;
        void doSomething(){
            int y;
            System.out.println(x);
            // System.out.println(y); /* 错误，y 未初始化 */
        }
    }

    public static void main(String agrs[]){
        TestClass tc = new TestClass();
        tc.doSomething();
    }
}
