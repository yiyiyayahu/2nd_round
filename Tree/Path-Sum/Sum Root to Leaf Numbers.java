/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
*/

public class Solution {
    int result = 0;
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        helper(root, 0);
        return result;
    }
    public void helper(TreeNode n, int tmp) {
        tmp = tmp * 10 + n.val;
        if(n.left == null && n.right == null) {
            result += tmp;
            return;
        } 
        if(n.left != null) helper(n.left, tmp);
        if(n.right != null) helper(n.right, tmp);
    }
}
