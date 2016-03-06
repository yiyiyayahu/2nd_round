/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
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
时间复杂度O(n)，空间的话是O(logn)还是O(n)啊啊啊啊，有个blog上面说的是O(logn)啊
空间是O(logn), 因为只recursive了depth of the tree
*/
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return helper(root.left, root.right);
    }
    public boolean helper(TreeNode left, TreeNode right) {
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        if(left.val != right.val) return false;
        return helper(left.left, right.right) && helper(left.right, right.left);
    }
}

/*
iterative的解法
开始以为只要类似层递归那种就可以呢，然后检验每一层的arr是不是对称的
后来发现，不行，有可能在某个位置是null的就不太好办了。
不过其实也是可以做的，ArrayList<Integer>里面放null就好了，只是如果上一层是连着的两个null，下一层出现四个null有点搞笑哈。。。
还不如用两个queue呢，一个是left的，一个是right的
*/

public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        if(root.left == null && root.right == null) return true;
        if(root.left == null || root.right == null) return false;
        
        Queue<TreeNode> q1 = new LinkedList<TreeNode>();
        Queue<TreeNode> q2 = new LinkedList<TreeNode>();
        
        q1.add(root.left);
        q2.add(root.right);
        
        while(!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode left = q1.remove();
            TreeNode right = q2.remove();
            
            if(left.val != right.val) return false;
            if((left.left == null && right.right != null) || (left.right == null && right.left != null)) return false; 
            if((left.left != null && right.right == null) || (left.right != null && right.left == null)) return false;
            
            if(left.left != null && right.right != null) {
                q1.add(left.left);
                q2.add(right.right);
            }
            if(left.right != null && right.left != null) {
                q1.add(left.right);
                q2.add(right.left);
            }
        }
        return true;
    }
}
