/*
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), 
where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.
Here's an example:
    10
    / \
   5  15
  / \   \ 
 1   8   7
The Largest BST Subtree in this case is the highlighted one. 
The return value is the subtree's size, which is 3.
Show Hint 
Follow up:
Can you figure out ways to solve it with O(n) time complexity?
*/

/*
如果O(nlogn)就会比较简单。。。但是O(n)的做法我用了好多wrapper，我再想想
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
    public int largestBSTSubtree(TreeNode root) {
        if(root == null) return 0;
        int leftMax = largestBSTSubtree(root.left);
        int rightMax = largestBSTSubtree(root.right);
        
        if(isValidBST(root, null, null)) return leftMax + rightMax + 1;
        return Math.max(leftMax, rightMax);
    }
    
    public boolean isValidBST(TreeNode root, Integer low, Integer high) {
        if(root == null) return true;
        if(low != null && root.val < low) return false;
        if(high != null && root.val >= high) return false;
        
        return isValidBST(root.left, low, root.val) && isValidBST(root.right, root.val, high);
    }
}
