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
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.
*/

/*
开始写的有问题：
首先呢，有右子树的时候不是直接返回p.right而是返回leftmost(p.right)
之后呢，原来写的是，因为是BST嘛，如果p.val<root.val说明在左子树里面，就在左子树里面找，反之就在右子树里面找
但是总是有问题，所以后来就干脆改为iterative的version了
没有很好的利用到BST这个条件，再想想

下面这个解法就是根据inorderTraversal来的，如果p被pop出来了，那pop的下一个自然就是inorder successor
*/

public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null || p == null) return null;
        if(p.right != null) {
            TreeNode n = p.right;
            while(n.left != null) n = n.left;
            return n;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        
        boolean foundP = false;
        while(curr != null || !stack.isEmpty()) {
            if(curr == null) {
                curr = stack.pop();
                if(foundP) return curr;
                if(curr == p) foundP = true;
                curr = curr.right;
            } else {
                stack.push(curr);
                curr = curr.left;
            }
        }
        return null;
    }
}

/*
这个解法好巧妙的说
1. 如果tmp.val > p.val, 那我先缓存一下tmp，tmp向左移看看能不能在左子树里面找到另外一个离p更近的，如果不能找到就直接返回res，找到就更新res
2. 如果tmp.val <= p.val，那么tmp不可能是res，就tmp右移，在右子树里面接着找
*/
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null || p == null) return null;
        TreeNode res = null;
        TreeNode tmp = root;
        while(tmp != null) {
            if(tmp.val > p.val) {
                res = tmp;
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
        }
        return res;
    }
}
