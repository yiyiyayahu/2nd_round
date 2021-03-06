/*
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?
*/
    //root - left - right
    
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        if(root == null) return list;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode n = stack.pop();
            list.add(n.val);
            if(n.right != null) stack.push(n.right);
            if(n.left != null) stack.push(n.left);
        }
        return list;
    }
}
//额，这次是这么写的。。和之前好不一样啊
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode tmp = root;

        while(tmp != null || !stack.isEmpty() ) {
            if(tmp == null) {
                tmp = stack.pop().right;
            }else {
                stack.push(tmp);
                list.add(tmp.val);
                tmp = tmp.left;
            }
        }
        return list;
    }
}
