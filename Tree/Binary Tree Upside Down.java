/*
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, 
flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1
*/

/*
注意NPE，就是root.left==null的时候就可以直接返回了，开始没判断的话有可能tmp是null，这样while(tmp.right!=null)就是NPE
其次呢，该断掉的link断掉，比如root.left=null, root.right=null
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
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null || root.left == null) return root;
        
        TreeNode n = root;
        TreeNode left = upsideDownBinaryTree(root.left);
        TreeNode right = upsideDownBinaryTree(root.right);
        root.left = null;
        root.right = null;
        
        TreeNode tmp = left;
        while(tmp.right != null) tmp = tmp.right;
        tmp.left = right;
        tmp.right = root;
        return left;
    }
}

/*
iterative的解法
核心思想就是
node.left = parent.right;
node.right = parent

code按照顺序写下来就可以了
*/

public class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null || root.left == null) return root;
    
        TreeNode curr = root, parent = null, parentRight = null;
        
        while(curr != null) {
            TreeNode left = curr.left;
            curr.left = parentRight;
            parentRight = curr.right;
            curr.right = parent;
            parent = curr;
            curr = left;
        }
        
        return parent;   
    }
}
