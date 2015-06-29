/*
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].
*/

//left - root - right


/*
我这个解法的空间复杂度是不好的，因为多加了一个set
开始想的是，stack，先push left点，push不进去的时候，pop同时加到list里面，再push当前pop出来的点的right节点
但是问题是容易死循环
比如[1,2]，stack push(1) push(2)，之后pop(2), list.add(2)，但是到1节点的时候还是会push(2)，之后pop(2), list.add(2)。。。
所以就要track一下访问过的节点不要再push了。

更好的方法想不到啊想不到，悲剧
*/
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> lists = new ArrayList<Integer>();
        if(root == null) return lists;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        HashSet<TreeNode> traversed = new HashSet<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode n = stack.peek();
            if(n.left != null && !traversed.contains(n.left)) {
            	stack.push(n.left);
            } else {
                n = stack.pop();
                lists.add(n.val);
                traversed.add(n);
                if(n.right != null) stack.push(n.right);
            }
        }
        return lists;
    }
}
