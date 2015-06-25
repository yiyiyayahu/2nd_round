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
 这个其实算是bottom-up的做法，因为下面那个重复调用了算height的函数，其实这里，比如在检查left subtree的过程中，
 如果不是balanced，就可以直接return false了 （也就是return -1）
 这样时间复杂度就是O(n) 空间复杂度是O(n)
 */
public class Solution {
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }
    
    public int height(TreeNode root) {
        if(root == null) return 0;
        
        int left = height(root.left);
        if(left == -1) return -1;
        int right = height(root.right);
        if(right == -1) return -1;
        if(Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }
}

/*
原来我这个题用的一直是最差的解法
时间复杂度是O(n^2) 空间是O(n) stack space(recursion).
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
