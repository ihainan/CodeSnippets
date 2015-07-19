package me.ihainan.tij.chapter10.section3;

/**
 * 测试 .this 和 .new 的无聊程序
 */

public class Program_1 {
    public int x = 1;

    public class InnerClass {
        public int x = 2;

        public void printX() {
            System.out.println(x);
            System.out.println(this.x);
            System.out.println(Program_1.this.x);
        }
    }

    public InnerClass inner() {
        return new InnerClass();
    }

    public static void main(String[] args) {
        Program_1 program_1 = new Program_1();
        InnerClass innerClass = program_1.inner();
        innerClass.printX();

        InnerClass newInner = program_1.new InnerClass();
        newInner.printX();
    }
}
/* Output:
2
2
1
2
2
1
*/
