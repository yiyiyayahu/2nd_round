/*
 Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5 
*/

/*
做这种题一定要注意nullpointer。。。又是试了好多次才成功的，不行的啊，我要多多自己test，检查一遍再提交，要练习这种能力
1. 虽然注意了if(n == null) return head;那里，但是其实应该放在n=n.next后面
2. i=1->k而不是0->k不然就夺走了一步
3. 前面反转的时候，其实把n.next直接设为null比较好做一点
*/

public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k < 2) return head;
        ListNode n = head;
        for(int i = 1; i < k; i++) {
          n = n.next;
          if(n == null) return head;
        }
        ListNode next = reverseKGroup(n.next, k);
        ListNode newtail = null;
        n.next = null;
        ListNode tmp = head;
        while(tmp != null) {
          ListNode node = tmp.next;
          tmp.next = newtail;
          newtail = tmp;
          tmp = node;
        }
        head.next = next;
        return newtail;        
    }
}
