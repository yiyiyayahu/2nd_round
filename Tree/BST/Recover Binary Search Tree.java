/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.
*/

/*
虽然写的磕磕绊绊的，但是也算是写出来了
还是先找到problem，然后一点点解决
这道题无非就是找两个不太对的node，swap一下他们的值
那怎么找呢，就是inorder traversal的时候发现prev.val > curr.val，那这个prev和curr可能是两个不对的点，但是也不一定。prev一定是不对的
比如inorder - [6,5,4,7]那不应该6和5互换，而应该6和4互换，所以遍历的过程中再更新second
不过这道题要求constant space，我这个还是用到stack，所以还是O(n)，再想想

下面这个用了recursive，其实也不算是严格的constant space，而且我觉得思想差不多，当然我的code还可以简化一下
*/

public class Solution {
    TreeNode first;
    TreeNode second;
    TreeNode prev;
    public void recoverTree(TreeNode root) {
        if(root == null) return;
        first = null; second = null; prev = null;
        inorder(root);
        if(first != null && second != null) {
            int tmp = first.val;
            first.val = second.val;
            second.val = tmp;
        }
    }
    public void inorder(TreeNode curr) {
        if(curr == null) return;
        //prev is the inorder previous node from curr
        inorder(curr.left);
        if(prev == null) prev = curr;
        else {
            if(prev.val > curr.val) {
                if(first == null) first = prev;
                second = curr;
            }
            prev = curr;
        }
        inorder(curr.right);
    }
}

public class Solution {
    public void recoverTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        
        TreeNode curr = root, prev = null;
        TreeNode first = null, second = null;
        while(curr != null || !stack.isEmpty()) {
            if(curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
            
                if(prev != null && prev.val > curr.val) {
                    if(first == null) first = prev;
                    second = curr;
                }
                prev = curr;
                curr = curr.right;
            }
        }
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;        
    }
}


public class Solution {
    public void recoverTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        
        TreeNode curr = root, prev = null;
        TreeNode first = null, second = null;
        while(curr != null || !stack.isEmpty()) {
            if(curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                
                if(prev != null && first == null && prev.val > curr.val) {
                    first = prev;
                    second = curr;
                }
                
                if(first != null && second != null && curr.val < second.val) {
                    second = curr;
                }
                
                prev = curr;
                curr = curr.right;
            }
        }
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }
}
