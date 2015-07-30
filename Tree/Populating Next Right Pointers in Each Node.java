/*
Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. 
If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
*/

/*
递归做法
*/
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        
        if(root.left == null && root.right == null) return;
        root.left.next = root.right;
        root.right.next = (root.next != null) ? root.next.left : null;
        
        connect(root.left);
        connect(root.right);
    }
}

public class Solution {
    public void connect(TreeLinkNode root) {
      if(root == null) return;
  
      TreeLinkNode tmp = root;
      while(tmp != null) {
        TreeLinkNode start = tmp;
        while(start != null) {
            if(start.left == null && start.right == null) return;
            start.left.next = start.right;
            start.right.next = (start.next == null) ? null : start.next.left;
            start = start.next;
        }
        tmp = tmp.left;
      }      
    }
}
