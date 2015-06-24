/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
*/


/*
额，这道题做了好多次才对。开始居然写的特别简单：
        if(root == null) return true;
        if(root.left != null && root.left.val >= root.val) return false;
        if(root.right != null && root.right.val <= root.val) return false;
        return isValidBST(root.left) && isValidBST(root.right);
但很明显不行啊，左子树的所有节点都要比root.val小，我这个相当于只比了一层啊

之后就是递归。但是这道题有这么几种testcase
[2147483647] -- true
[2147483647,2147483647] --false
所以上下限开始设成Integer.MIN_VALUE, Integer.MAX_VALUE在这里简单判断if(n.val <= low || n.val >= high) return false就不对了
唉，和之前犯的同样的错误
解决方法是加入两个boolean变量来track

但是我这里又犯了一个错误！！！
helper(n.left, low, n.val, true, checkRight)，这里在递归里面应该把checkRight保留对吧。。。我直接设成false了，囧
因为这两个变量其实只是解决这个tree不完全，就是low和high不应该完全是Integer.MIN_VALUE, Integer.MAX_VALUE才用到的，
如果low和high都有相应地value了，我还是要都判断的，也就是if(n.val <= low || n.val >= high)
递归的这个问题我发现我犯过不只一次了，就是没有把传进来的变量传下去

复杂度：
O(n) runtime, O(n) stack space – Top-down recursion:
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
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE, false, false);
    }
    
    public boolean helper(TreeNode n, int low, int high, boolean checkLeft, boolean checkRight) {
        if(n == null) return true;
        if(checkLeft && n.val >= high) return false;
        if(checkRight && n.val <= low) return false;
        
        if(!helper(n.left, low, n.val, true, checkRight)) return false;
        return helper(n.right, n.val, high, checkLeft, true);
    }
}
