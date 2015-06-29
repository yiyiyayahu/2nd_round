/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
*/

/*
很巧妙的做法，time: O(n) space: O(logn)
最好想的就是和convert sorted array类似，但是每次指针都要移到中间的位置。每个level都移n/2，一共logn个level，时间就是O(nlogn)

想想有没有可能我只遍历linkedlist一遍就能把node摆在应该在的位置呢！
我觉得下面这个做法很厉害，第一遍的时候就没想到，这次只是隐约有点印象，囧
算是bottom-up？用recursive，通过node移动，每次的node.val正好是当前TreeNode的值
*/
public class Solution {
    private ListNode node;
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        ListNode tmp = head;
        int len = 0;
        while(tmp != null) {
            tmp = tmp.next;
            len ++;
        }
        node = head;
        return helper(0, len-1);
    }
    public TreeNode helper(int start, int end) {
        if(start > end) return null;
        
        int mid = (start + end)/2;
        TreeNode left = helper(start, mid - 1);
        TreeNode root = new TreeNode(node.val);
        root.left = left;
        node = node.next;
        root.right = helper(mid+1, end);
        return root;
    }
}
