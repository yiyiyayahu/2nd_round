/*
Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,
              5
             / \
            1   5
           / \   \
          5   5   5
return 4.
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
这道题很显然是recursive做的，思路也很清晰，唯一的一点是和longest consecutive sequence一样的问题，就是我在遍历的时候root node有没有involve进去这个信息如何传递
开始还是忽略了这个问题，导致fail了几个test case  比如[-79,-79,null,97,null]这里，在root这个-79，check左边和右边，发现右边为空，左边正好和当前值相等，就会返回2了。。。
discuss里面有个解法很巧妙，就直接将返回的结果加个正负号来传递这个值，如果involve了，就返回正的，不然返回负的。。。好厉害，不然我还要分配一个boolean array进去
*/
public class Solution {

    public int countUnivalSubtrees(TreeNode root) {
        return Math.abs(helper(root));
    }
    
    public int helper(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        
        int left = helper(root.left);
        int right = helper(root.right);
        int count = Math.abs(left) + Math.abs(right);
        
        if((left == 0 || (left>0 && root.val==root.left.val)) && (right == 0 || (right>0 && root.val==root.right.val))) {
            count ++;
        } else {
            count = 0 - count;
        }
        return count;
    }
}
