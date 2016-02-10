/*
Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
*/

/*
很简单的一道题，就用dfs就行
然后dfs还是要注意不要把上一次的结果带入下一次调用，所以new StringBuilder(sb),不然调用root.left的时候会把sb改了，这样在root.right中就有多余的，结果就不对了
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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<String>();
        dfs(root, list, new StringBuilder());
        return list;
    }
    
    public void dfs(TreeNode root, List<String> list, StringBuilder sb) {
        if(root == null) return;
        
        if(sb.length() == 0) sb.append(root.val);
        else sb.append("->"+root.val);
        
        if(root.left == null && root.right == null) {
            list.add(sb.toString());
            return;
        }
        dfs(root.left, list, new StringBuilder(sb));
        dfs(root.right, list, new StringBuilder(sb));
    }
}
