package me.ihainan.tij.chapter4;

/**
 * 无聊的素数探测程序
 */
public class Exercise_4 {
    public static void main(String[] args){
        for(int i = 2; i <= 1000; ++i){
            boolean isPrimeNumber = true;
            for(int j = 2; j <= Math.sqrt(i); ++j){
                if(i != j && i % j == 0){
                    isPrimeNumber = false;
                }
            }
            if(isPrimeNumber){
                System.out.println(i + " 是一个素数");
            }
        }
    }
}
