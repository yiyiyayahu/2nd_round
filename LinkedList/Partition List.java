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
        
        ListNode dummyHead = new ListNode(-1);
        ListNode stop = dummyHead;
        dummyHead.next = head;
        while(stop.next != null && stop.next.val < x) {
            stop = stop.next;
        }
        
        ListNode tmp = stop.next;
        while(tmp != null && tmp.next != null) {
            if(tmp.next.val < x) {
            	ListNode curr = tmp.next;
            	tmp.next = tmp.next.next;
            	curr.next = null;
            	
            	ListNode stopNext = stop.next;
            	stop.next = curr;
            	curr.next = stopNext;
            	stop = curr;
            } else {
                tmp = tmp.next;
            }
        }
        return dummyHead.next;
    }
}

/*
cc150上面最原始的做法
*/
public class Solution {
    public ListNode partition(ListNode head, int x) {
        //s1 - beforeStart, s2 - beforeEnd, l1 - afterStart, l2 - afterEnd
        ListNode s1 = null, s2 = null;
        ListNode l1 = null, l2 = null;
        
        ListNode tmp = head;
        while(tmp != null) {
            ListNode next = tmp.next;
            tmp.next = null;
            if(tmp.val < x) {
                if(s1 == null) {
                    s1 = tmp;
                    s2 = s1;
                } else {
                    s2.next = tmp;
                    s2 = s2.next;
                }
            } else {
                if(l1 == null) {
                    l1 = tmp;
                    l2 = l1;
                } else {
                    l2.next = tmp;
                    l2 = l2.next;
                }
            }
            tmp = next;
        }
        if(s1 == null) return l1;
        s2.next = l1;
        return s1;
    }
}
