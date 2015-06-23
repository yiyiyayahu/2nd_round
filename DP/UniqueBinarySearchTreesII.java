/*
Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/


/*
这个不是dp，只是因为和I一起做了就放到这里了
提交了好多次，囧，不是很好做啊

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
    public List<TreeNode> generateTrees(int n) {
        return helper(1,n);        
    }
    
    public List<TreeNode> helper(int left, int right) {
    	List<TreeNode> lists = new ArrayList<TreeNode>();;
        if(left > right) {
        	lists.add(null); 
        	return lists;
        }
        if(left == right) {
        	lists.add(new TreeNode(left)); 
        	return lists;
        }
        for(int i = left; i <= right; i++) {
            List<TreeNode> lefts = helper(left, i-1);
            List<TreeNode> rights = helper(i+1, right);
            
            for(TreeNode l : lefts) {
            	for(TreeNode r : rights) {
                  TreeNode root = new TreeNode(i);
                  root.left = l;
                  root.right = r;
                  lists.add(root);
            	}
            }          
        }
        return lists;
    }
}
