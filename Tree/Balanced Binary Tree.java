/*
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree 
in which the depth of the two subtrees of every node never differ by more than 1.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
/*
原来我这个题用的一直是最差的解法
时间复杂度是O(n^2) 空间是O(n) stack space(recursion).这个空间是怎么算的我要研究下。。。
*/
public class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null)  return true;
        if(Math.abs(height(root.left) - height(root.right)) > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }
    
    public int height(TreeNode root) {
        if(root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}
