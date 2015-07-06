package me.ihainan.tij.chapter7;

/**
 * 验证常量的无聊例子
 */
public class Program_7_8_1 {
    public static int[] array = new int[]{1, 2, 3}; // 引用是常量，引用的对象并不是常量
    public static final int number;                 // 空白 final

    static {
        number = 3;
    }

    public static void f(final int x){
        // x = 3; // 错误，final 参数
        System.out.println(x);
    }
    public static void main(String[] args) {
        for (int i = 0; i < array.length; ++i) {
            array[i]++;
            System.out.println(array[i]);
        }

        f(3);
    }
}
