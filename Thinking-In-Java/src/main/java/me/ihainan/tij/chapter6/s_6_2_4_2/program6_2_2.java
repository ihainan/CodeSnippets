package me.ihainan.tij.chapter6.s_6_2_4_2;

import me.ihainan.tij.chapter6.s_6_2_4_1.Program6_2_1;

/**
 * Protected 访问权限测试
 */
public class program6_2_2 extends Program6_2_1 {
    public static void main(String[] args){
        // this.x; // 错误，与 Program6_6_1 不在一个包内
    }
}
