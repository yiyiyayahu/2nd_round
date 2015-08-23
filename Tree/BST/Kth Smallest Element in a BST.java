/*
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
How would you optimize the kthSmallest routine?

Hint:

Try to utilize the property of a BST.
What if you could modify the BST node's structure?
The optimal runtime complexity is O(height of BST).
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
 inorder traversal的第k个就好了
 */
 public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = inorderTraversal(root);
        return list.get(k-1);
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> lists = new ArrayList<Integer>();
        if(root == null) return lists;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();

        TreeNode n = root;
        while(!stack.isEmpty() || n != null) {            
            if(n != null) {
            	stack.push(n);
            	n = n.left;
            } else {
                n = stack.pop();
                lists.add(n.val);
                n = n.right;
            }
        }
        return lists;
    }
}


public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2-o1;
		}
        });
        helper(root, queue, k);
        return queue.peek();
    }
    
    public void helper(TreeNode root, PriorityQueue<Integer> queue, int k) {
        if(root == null) return;
        if(queue.size() < k) {
            queue.add(root.val);
        } else {
            if(root.val < queue.peek()) {
                queue.remove();
                queue.add(root.val);
            }
        }
        helper(root.left, queue, k);
        helper(root.right, queue, k);
    }
}


