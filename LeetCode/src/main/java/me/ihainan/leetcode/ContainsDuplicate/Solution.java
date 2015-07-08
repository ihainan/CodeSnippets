package me.ihainan.leetcode.ContainsDuplicate;

import java.util.HashMap;

/**
 * TITLE        : Contains Duplicate <br>
 * URL          : https://leetcode.com/problems/contains-duplicate/ <br>
 * DIFFICULTY   : Easy <br>
 * COMMENT      :
 */
public class Solution {
    /**
     * 检测整数数组中是否包含相同数值
     *
     * @param nums 整数数组
     * @return 数组中是否包含相同数值
     */
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Boolean> isDuplicate = new HashMap<Integer, Boolean>();
        for(int num: nums){
            if(isDuplicate.keySet().contains(num)){
                return true;
            }
            isDuplicate.put(num, true);
        }
        return false;
    }

    /**
     * 测试函数
     */
    public void test() {
        int[] nums = new int[]{-0, 0, -1, -1};
        System.out.println(containsDuplicate(nums));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.test();

    }
}