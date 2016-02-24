/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].
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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if(root == null) return ret;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<TreeNode> next = new LinkedList<TreeNode>();
        queue.add(root);
        
        while(!queue.isEmpty()) {
            TreeNode n = queue.remove();
            if(n.left != null) next.add(n.left);
            if(n.right != null) next.add(n.right);
            
            if(queue.isEmpty()) {
                ret.add(n.val);
                queue = next;
                next = new LinkedList<TreeNode>();
            }
        }
        
        return ret;
    }
}
