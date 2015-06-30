/*
Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].
*/


/*
思路是这样的，用两个指针来track当前的路线 prev curr，看看是往下走还是已经遍历完了往上走
1) if(prev==null||prev.left==null||prev.right==null)说明还在向下走，
  curr.left如果不是null的话就push进stack，else，如果curr.right不是null的话push进stack
  如果左右都是null说明是叶子节点，就直接add到lists，并且stack.pop
2) if(curr.left==prev)说明左边已经遍历完了，那如果curr.right!=null就把curr.right压入stack，不然表示这个节点的左右子树都遍历完了，add到lists，并且stack.pop
3) if(curr.right==prev)说明右边遍历完了，可以直接add到lists，并且stack.pop
*/
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> lists = new ArrayList<Integer>();
        if(root == null) return lists;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode prev = null, curr = root;
        
        while(!stack.isEmpty()) {
            curr = stack.peek();
            if(prev == null || prev.left == curr || prev.right == curr) {
                if(curr.left != null) stack.push(curr.left);
                else if(curr.right != null) stack.push(curr.right);
                else {
                    stack.pop();
                    lists.add(curr.val);
                }
            } else if(curr.left == prev && curr.right != null) {
                stack.push(curr.right);
            } else {
                stack.pop();
                lists.add(curr.val);
            }
            prev = curr;
        }
        return lists;
    }
}
