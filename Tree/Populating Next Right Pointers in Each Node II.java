/*
Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/


/*
好巧妙的做法！！！先是找到下一个的点在哪里，然后先连上
注意recursive的时候先右边后左边
*/
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
  
        TreeLinkNode p = root.next;
        while(p != null) {
            if(p.left != null) {
                p = p.left;
                break;
            }
            if(p.right != null) {
                p = p.right;
                break;
            }
            p = p.next;
        }
        
        if(root.right != null) {
            root.right.next = p;
        }
        if(root.left != null) {
            root.left.next = (root.right != null) ? root.right : p;
        }
        
        connect(root.right);
        connect(root.left);
    }
}

/*
这个解法是我自己写的，虽然不是很巧妙，但是怎么说呢，不要去想以前巧妙的思路，一步一步分析问题一步一步解决，相信自己总会做出来的就可以了
这道题和I的区别无非就是left，right子节点都可能是null，不知道怎么连嘛。那就构造这样一个函数好啦
*/
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        
        TreeLinkNode curr = root;
        while(curr != null) {
            for(TreeLinkNode tmp = curr; tmp != null; tmp = tmp.next) {
                if(tmp.left == null && tmp.right == null) continue;
                
                if(tmp.left != null && tmp.right != null) tmp.left.next = tmp.right;
                TreeLinkNode next = getNextChild(tmp.next);
                if(tmp.right != null) tmp.right.next = next;
                else tmp.left.next = next;
            }
            curr = getNextChild(curr);
        }
    }
    
    public TreeLinkNode getNextChild(TreeLinkNode root) {
        if(root == null) return null;
        if(root.left == null && root.right == null) return getNextChild(root.next);
        if(root.left != null) return root.left;
        else return root.right;
    }
}
