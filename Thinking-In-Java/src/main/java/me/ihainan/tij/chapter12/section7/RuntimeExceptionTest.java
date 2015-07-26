package me.ihainan.tij.chapter12.section7;

/**
 * Runtime Exception 测试程序
 */
public class RuntimeExceptionTest {
    public static void main(String[] args) {
        // throw new RuntimeException();
        // throw new NullPointerException();
        try {
            throw new ArrayIndexOutOfBoundsException();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
