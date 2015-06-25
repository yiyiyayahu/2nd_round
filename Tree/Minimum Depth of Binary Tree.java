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
*/

public class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null) return minDepth(root.right) + 1;
        if(root.right == null) return minDepth(root.left) + 1;
        
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}
