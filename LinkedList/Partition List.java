/*
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
*/

public class Solution {
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) return head;
        
        ListNode dummyHead = new ListNode(Integer.MAX_VALUE);
        ListNode stop = dummyHead;
        dummyHead.next = head;
        while(stop.next != null && stop.next.val < x) {
            stop = stop.next;
        }
        
        ListNode tmp = stop.next;
        while(tmp != null && tmp.next != null) {
            if(tmp.next.val < x) {
            	ListNode stopNext = stop.next;
            	stop.next = new ListNode(tmp.next.val);
            	stop = stop.next;
            	stop.next = stopNext;
            	
            	tmp.next = tmp.next.next;
            } else {
                tmp = tmp.next;
            }
        }
        return dummyHead.next;
    }
}
