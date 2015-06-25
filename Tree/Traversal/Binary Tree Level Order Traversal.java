/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
*/

/*
我以前外面还套一个while(true)循环，后来发现没必要诶，下面这样写就好了呀
时间复杂度O(n) 空间的话也是O(n)
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if(root == null) return result;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<TreeNode> tmp = new LinkedList<TreeNode>();
        List<Integer> list = new ArrayList<Integer>();
        queue.add(root);
        
        while(!queue.isEmpty()) {
            TreeNode n = queue.remove();
            list.add(n.val);
            if(n.left != null) tmp.add(n.left);
            if(n.right != null) tmp.add(n.right);
            if(queue.isEmpty()) {
                result.add(list);
                queue = tmp;
                tmp = new LinkedList<TreeNode>();
                list = new ArrayList<Integer>();
            }
        }
        return result;
    }
}
