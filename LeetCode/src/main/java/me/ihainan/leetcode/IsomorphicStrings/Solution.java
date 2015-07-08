package me.ihainan.leetcode.IsomorphicStrings;

import java.util.HashMap;

/**
 * TITLE        : Isomorphic Strings  <br>
 * URL          : https://leetcode.com/problems/isomorphic-strings/
 * DIFFICULTY   : Easy <br>
 * COMMENT      :
 */
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> hashMap = new HashMap<Character, Character>();
        for(int i = 0; i < s.length(); ++i){
            Character c = hashMap.put(s.charAt(i), t.charAt(i));
            if(c != null && c != t.charAt(i)){
                return false;
            }
        }
        return true;
    }

    /**
     * 测试函数
     */
    public void test() {
        System.out.println(isIsomorphic("ab", "aa"));
        System.out.println(isIsomorphic("foo", "bar"));
        System.out.println(isIsomorphic("paper", "title"));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.test();

    }
}