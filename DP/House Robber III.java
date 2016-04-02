/*
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." 
Besides the root, each house has one and only one parent house. 
After a tour, the smart thief realized that "all houses in this place forms a binary tree". 
It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:
     3
    / \
   2   3
    \   \ 
     3   1
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:
     3
    / \
   4   5
  / \   \ 
 1   3   1
Maximum amount of money the thief can rob = 4 + 5 = 9.
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
这个解法会TLE，只是个思路。。。有很多重复计算，再想想如何把结果存起来。。。
*/
public class Solution {
    public int rob(TreeNode root) {
        return Math.max(dfs(root, true), dfs(root,false));
    }
    
    public int dfs(TreeNode node, boolean isChosen) {
        if(node == null) return 0;
        
        int left = dfs(node.left, false);
        int right = dfs(node.right, false);
        
        if(isChosen) {
            return left+right+node.val;
        } else {
            int leftIn = dfs(node.left, true);
            int rightIn = dfs(node.right, true);
            return Math.max(left, leftIn) + Math.max(right, rightIn);
        }
    }
}
