package me.ihainan.leetcode.PowerOfTwo;

/**
 * TITLE        : Power of Two <br>
 * URL          : https://leetcode.com/problems/power-of-two/ <br>
 * DIFFICULTY   : Easy <br>
 * COMMENT      : <br>
 *     <ul>
 *         <li>Try: 536870912</li>
 *     </ul>
 */
public class Solution {
    /**
     * 检测整数是否是 2 某次方的幂
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if (n < 0) { return false; }
        double t = Math.log(n);
        double m = Math.log(n) / Math.log(2);
        return (int)m == m;
    }

    /**
     * 测试函数
     */
    public void test() {
        for(int i = -10; i <= 200; ++i) {
            // System.out.println(i + ":" + isPowerOfTwo(i));
        }
        System.out.println(isPowerOfTwo(536870912));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.test();
        // System.out.println(Math.log(-1));
    }
}