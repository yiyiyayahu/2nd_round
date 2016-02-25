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
 
 /*
 用一个int来记录当前这一个level的node数目，就不用再分配一个queue的extra space了
 */
 public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if(root == null) return ret;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode n = queue.remove();
                if(i == size-1) ret.add(n.val);
                if(n.left != null) queue.add(n.left);
                if(n.right != null) queue.add(n.right);
            }
        }
        
        return ret;
    }
}

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