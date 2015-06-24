/*
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
*/

/*
时间复杂度O(n) 空间复杂度为啥是O(logn)?
Assume that n is the total number of nodes in the tree. The runtime complexity is O(n)
because it traverse each node exactly once. As the maximum depth of a binary tree is
O(log n), the extra space cost is O(log n) due to the extra stack space used by the
recursion.
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
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
