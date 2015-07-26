package me.ihainan.tij.chapter12.section11;

/**
 * 异常匹配测试程序
 */

class CustomException extends Exception{}

public class ExceptionMatching {
    public static void main(String[] args){
        try{
            throw new CustomException();
        }
        // catch (CustomException e){
        //    System.out.println(e.getClass());
        // }
        catch (Exception e) {
            System.out.println(e.getClass());
        }
    }
    /* Output:
        class me.ihainan.tij.chapter12.section11.CustomException
     */
}
