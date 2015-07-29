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
