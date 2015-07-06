package me.ihainan.tij.chapter7;

/**
 * 验证基类初始化的无聊程序
 */

class BaseClassA{
    public BaseClassA(){
        System.out.println("From Class A");
    }
}

class BaseClassB extends BaseClassA{
    public BaseClassB(){
        System.out.println("From Class B");
    }
}

public class Program7_2_1 extends BaseClassB{
    /*
    public Program7_2_1(){
        System.out.println("From Program7_2_1");
    }
    */

    public static void main(String[] args){
        Program7_2_1 program7_2_1 = new Program7_2_1();
    }
}
