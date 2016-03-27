/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
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
public class Solution {
    public int closestValue(TreeNode root, double target) {
        int candidate = 0;
        if(root.val > target && root.left != null) {
            candidate = closestValue(root.left, target);
        } else if(root.val < target && root.right != null) {
            candidate = closestValue(root.right, target);
        } else {
            return root.val;
        }
        
        if(Math.abs(root.val - target) > Math.abs(candidate - target)) return candidate;
        return root.val;
    }
}
