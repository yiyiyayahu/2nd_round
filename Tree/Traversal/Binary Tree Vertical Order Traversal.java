/*
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its vertical order traversal as:
[
  [9],
  [3,15],
  [20],
  [7]
]
Given binary tree [3,9,20,4,5,2,7],
    _3_
   /   \
  9    20
 / \   / \
4   5 2   7
return its vertical order traversal as:
[
  [4],
  [9],
  [3,5,2],
  [20],
  [7]
]
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
开始看到题目还是很懵。。。什么叫vertical order，怎么第二个例子里面，3，5，2就放在一行了，没什么思路
后来发现，很巧妙，可以这样理解
    0
  -1  1
-2 0 0 2
就左边就col-1，右边就col+1，然后用一个hashmap存col对应的list of node values就可以了
*/
public class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if(root == null) return ret;
        
        int min = 0, max = 0;
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<Integer> cols = new LinkedList<Integer>();
        
        queue.add(root);
        cols.add(0);
        
        while(!queue.isEmpty()) {
            TreeNode node = queue.remove();
            int col = cols.remove();
            
            if(!map.containsKey(col)) map.put(col, new ArrayList<Integer>());
            map.get(col).add(node.val);
            
            if(node.left != null) {
                queue.add(node.left);
                cols.add(col-1);
                min = Math.min(min, col-1);
            }
            if(node.right != null) {
                queue.add(node.right);
                cols.add(col+1);
                max = Math.max(max, col+1);
            }
        }
        
        for(int i = min; i <= max; i++) {
            ret.add(map.get(i));
        }
        return ret;
    }
}
