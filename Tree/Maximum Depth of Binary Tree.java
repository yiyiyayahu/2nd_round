/*
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
*/

/*
时间复杂度O(n) 空间复杂度为啥是O(logn)?

recursion的空间复杂度是这样的
比如factorial(n) = n * factorial(n-1)
那我算f(5)的时候我会调用f(4)同时memory(stack)要把5的状态存下来，同理，f(4)调用f(3)，存f(4)的状态。。。
然后f(1)调用完了之后就stack一点点pop出来，所以这个时候空间复杂度是O(n)

那这道题呢？额，还是不咋懂。。囧
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
