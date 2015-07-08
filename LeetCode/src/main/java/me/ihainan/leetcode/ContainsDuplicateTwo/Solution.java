package me.ihainan.leetcode.ContainsDuplicateTwo;

import java.util.HashMap;

/**
 * TITLE        : Contains Duplicate II <br>
 * URL          : https://leetcode.com/problems/contains-duplicate-ii/
 * DIFFICULTY   : Easy <br>
 * COMMENT      :
 */
public class Solution {
    /**
     * 检测整数数组中，是否存在相同两数，相邻距离不超过 k
     *
     * @param nums 整数数组
     * @param k    不能超过的值
     * @return 是否存在相同两数，相邻距离不超过 k
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> nearbyDuplicate = new HashMap<Integer, Integer>();
        /*
        for (int j = 0; j < nums.length; ++j) {
            int num = nums[j];
            if (nearbyDuplicate.containsKey(num)) {
                int i = nearbyDuplicate.get(num);
                if (j - i <= k) {
                    return true;
                }
            }
            nearbyDuplicate.put(num, j);
        }
        */

        // Better Code
        for(int i = 0; i < nums.length; ++i){
            Integer lastIndex = nearbyDuplicate.put(nums[i], i);
            if(lastIndex != null && i - lastIndex <= k){
                return true;
            }
        }
        return false;
    }

    /**
     * 测试函数
     */
    public void test() {
        int[] nums = new int[]{1, 0, 2, 1};
        System.out.println(containsNearbyDuplicate(nums, 3));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.test();

    }
}