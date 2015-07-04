package me.ihainan.tij.chapter5;

/**
 * 再次垃圾回收机制的无聊程序
 */
public class Program5_7 {
    public static class NewClass{
        int number;
    }

    public static class MyClass{
        NewClass newClass;
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("Executed finalize()");
        }

        public void f(){
            System.out.println(newClass.number);
        }

    }
    public static void main(String[] args){
        MyClass myClass = new MyClass();
        myClass = new MyClass();
        // myClass.f(); // NullPointerException
    }
}
