/*
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w 
as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, 
since a node can be a descendant of itself according to the LCA definition.
*/


/*
CC150上面的原题，还是不好写的啊啊啊啊
关键点是怎么能一边遍历一边返回可能的root
    returns p if root's subtree includes p
    returns q if root's subtree includes q
    returns null if neither p nor q is in root's subtree
    returns commonAncestor
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        if(left != null && left != p && left != q) return left;
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(right != null && right != p && right != q) return right;

        
        if(left != null && right != null) {
        //p and q are in different subtrees
            return root;
        } else {
            return left==null?right:left;
        }
    }        
}
