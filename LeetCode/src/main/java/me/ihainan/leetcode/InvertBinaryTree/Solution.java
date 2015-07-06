package me.ihainan.leetcode.InvertBinaryTree;

/**
 * TITLE        : Invert Binary Tree    <br>
 * URL          : https://leetcode.com/problems/invert-binary-tree/ <br>
 * DIFFICULTY   : Easy <br>
 * COMMENT      : 注意 root 为 Null 的情况 <br>
 */
public class Solution {
    /**
     * 二叉树定义
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 反转二叉树
     *
     * @param root 二叉树的根节点
     * @return 反转之后二叉树的根节点
     */
    public TreeNode invertTree(TreeNode root) {
        if (root != null && (root.left != null || root.right != null)) {
            if (root.left != null) {
                root.left = invertTree(root.left);
            }

            if (root.right != null) {
                root.right = invertTree(root.right);
            }

            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
        }
        return root;
    }

    /**
     * 测试函数
     */
    public void test() {
        TreeNode root = new TreeNode(0), node1 = new TreeNode(1), node2 = new TreeNode(2), node3 = new TreeNode(3), node4 = new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.right = node4;
        // invertTree(root);
        invertTree(null);
        System.out.println("Hello World");
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.test();
    }
}
