/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
*/

/*
参考InorderTraversal做的
*/
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    TreeNode curr;
    Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        curr = root;
        stack = new Stack<TreeNode>();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if(!stack.isEmpty() || curr != null) {
            return true;
        }
        return false;
    }

    /** @return the next smallest number */
    public int next() {
        int value = 0;
        while(curr != null) {
            stack.push(curr);
            curr = curr.left;
        } 
        curr = stack.pop();
        value = curr.val;
        curr = curr.right;
        
        return value;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
