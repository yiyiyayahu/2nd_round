/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
*/

/*
和max depth不同的就是
比如 1
      \
       2
这个时候left是null，但是我不能直接返回0， 还应该看右边，因为这个时候不到leaf
只要注意这点就可以了
相当于DFS
time: O(n) space: O(logn) 
*/

public class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null) return minDepth(root.right) + 1;
        if(root.right == null) return minDepth(root.left) + 1;
        
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}
/*
还有一种是BFS的做法，time O(n) space O(n)
但是解决那种highly unbalanced tree很有效，如果是balanced的话就还是要遍历每个节点
*/
public class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int depth = 1;
        TreeNode rightMost = root;
        while(!queue.isEmpty()) {
            TreeNode n = queue.remove();
            if(n.left == null && n.right == null) break;
            if(n.left != null) queue.add(n.left);
            if(n.right != null) queue.add(n.right);
            if(n == rightMost) {
                depth ++;
                rightMost = (n.right != null) ? n.right : n.left;
            }
        }
        return depth;
    }
}
