package me.ihainan.tij.chapter8;

/**
 * Created by ihainan on 7/7/15.
 */
public class Exercise_10 {
    public static class ClassA{
        private int f1(){
            return 3;
        }

        public void f2(){
            System.out.println(f1());
        }
    }

    public static class ClassB extends ClassA{
        @Override
        public void f2(){
            System.out.println("SB");
        }
    }

    public static void main(String[] args){
        ClassA object = new ClassB();
        object.f2();
    }
}
