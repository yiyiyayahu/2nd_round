/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
*/

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode tmp = head;
        while(tmp != null) {
            while(tmp.next != null && tmp.val == tmp.next.val) {
                tmp.next = tmp.next.next;
            }
            tmp = tmp.next;
        }
        return head;
    }
}

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode curr = dummy;
        while(curr.next != null && curr.next.next != null) {
            if(curr.next.val == curr.next.next.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return dummy.next;
    }
}
