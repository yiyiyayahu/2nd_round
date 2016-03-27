/*
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
*/


/*
额，这道题写了好多次。。。我觉得思路倒是很清晰的，recursive call左边和右边，看看root是不是可以involve进去，如何更新longest
1. 第一次犯的错误是，longest初始化为1，如果发现，诶，root+left这一只是valid，就更新longest变成leftmax+1
但是有个问题诶，有可能leftmax+1还是小于rightmax啊，解决办法就是longest初始化为Math.max(leftmax, rightmax)
2. 第二次犯的错误是，有可能leftmax和rightmax不包含left和right这两个点，那如果检测left.val==root.val+1，不应该直接leftmax+1
解决办法是加入了一个boolean array来track
3. 第三次犯的错误是，有可能left和right都没有一个好的值，也就是longest还是1，那这个时候呢，要把isRootIn[0]设为true
比如[2,#,3,2,#,1]这个，如果不把3这个点的isRootIn[0]设为true的话，2和3就没办法合在一起返回2了
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
    public int longestConsecutive(TreeNode root) {
        return helper(root, new boolean[1]);
    }
    
    public int helper(TreeNode root, boolean[] isRootIn) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) {
            isRootIn[0] = true;
            return 1;
        }
    
        boolean[] isLeftIn = new boolean[1];
        boolean[] isRightIn = new boolean[1];
        int leftmax = helper(root.left, isLeftIn);
        int rightmax = helper(root.right, isRightIn);
        int longest = Math.max(leftmax, rightmax);
        
        if(root.left != null && isLeftIn[0] && root.left.val == root.val+1) {
            if(rightmax < leftmax+1) {
                isRootIn[0] = true;
                longest = leftmax + 1;
            }
        }
        
        if(root.right != null && isRightIn[0] && root.right.val == root.val+1) {
            if(longest < rightmax+1) {
                isRootIn[0] = true;
                longest = rightmax+1;
            }
        }
        if(longest == 1) {
            isRootIn[0] = true;
        }
        return longest;
    }
}
