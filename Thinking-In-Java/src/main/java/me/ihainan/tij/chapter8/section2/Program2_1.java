package me.ihainan.tij.chapter8.section2;

/**
 * Created by ihainan on 7/7/15.
 */
public class Program2_1 {
    static class ClassA{
        public static void f(){System.out.println("Invoke f method in ClassA");}
        public int x = 1;
        public int getX(){
            return x;
        }
    }

    static class ClassB extends ClassA{
        // @Override    // 错误，不能继承静态方法
        public static void f(){}
        public int x = 2;
        public int getX(){
            return x;
        }
    }

    public static void main(String[] args){
        ClassA c = new ClassB();
        c.f();
        System.out.println(c.getX() + " " + c.x);
    }
}
