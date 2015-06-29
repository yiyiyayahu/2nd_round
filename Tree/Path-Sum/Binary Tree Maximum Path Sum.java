/*
Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
*/

/*
这道题的trick之处在于，start & end at any point, 但是要是一条path，也就是说：
left+root+right不能返回上一层，因为helper函数要返回的是一支的最大值
但是要有一个中间变量track它，有可能某个subtree的left+root+right最大
         4
       /  \
      1    -1
     / \
    2   3
*/
public class Solution {
    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root == null) return 0;

        helper(root);
        return maxSum;
    }
    public int helper(TreeNode node) {
        if(node == null) return 0;
        
        int left = helper(node.left);
        int right = helper(node.right);
        
        int curr = node.val + (left>0?left:0) + (right>0?right:0);
        if(curr > maxSum) maxSum = curr;
        return Math.max(node.val, node.val + Math.max(left, right));
    }
}
