/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.

Hints:
If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
*/

/*
我开始的想法居然是先遍历一遍，再构造树。。。
但是很显然这样很不高效，还有extra space，能不能在preorder的过程中做出来呢
可以先把右边存起来，然后左边的遍历完了，一点点挪到右边，最后再把stack里面存着的right tree遍历加进去
我这里用到一个stack，应该还可以优化不用stack的，再想想
recursive比较好想其实
后来写code出了一个问题，就是left==null的情况没考虑
还有就是其实根本不需要辅助函数返回一个TreeNode
root.left就是flatten好的left，root.right就是flatten好的right
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
    public void flatten(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        while(p != null || !stack.isEmpty()) {
            if(p.right != null) {
                stack.push(p.right);
            }
            if(p.left != null) {
                p.right = p.left;
                p.left = null;
            } else if(!stack.isEmpty()) {
                TreeNode tmp = stack.pop();
                p.right = tmp;
            }
            p = p.right;
        }
    }
}
 public class Solution {
    public void flatten(TreeNode root) {
        if(root == null) return;
        
        flatten(root.left);
        flatten(root.right);
        
        TreeNode left = root.left;
        TreeNode right = root.right;
        if(left == null) return;
        
        root.right = left;
        root.left = null;
        while(left.right != null) left = left.right;
        left.right = right;
    }
}



public class Solution {
    public void flatten(TreeNode root) {
        flattenNode(root);
    }
    public TreeNode flattenNode(TreeNode root) {
        if(root == null) return root;
        
        TreeNode left = flattenNode(root.left);
        TreeNode right = flattenNode(root.right);
        
        root.left = null;
        if(left == null) {
            root.right = right;
            return root;
        }
        
        root.right = left;
        
        while(left.right != null) {
            left = left.right;
        }
        left.right = right;
        return root;
    }
}
