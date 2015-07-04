package me.ihainan.tij.chapter5;

/**
 * 验证无参构造器
 */
public class Program5_3 {
    public static class DefaultConstructor{
        public DefaultConstructor(int x){
            System.out.println("x = " + x);
        }
    }


    public static void main(String[] args){
        // DefaultConstructor defaultConstructor = new DefaultConstructor(); // error
        DefaultConstructor defaultConstructor = new DefaultConstructor(3);
    }
}
