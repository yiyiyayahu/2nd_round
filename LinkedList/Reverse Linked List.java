/*
Reverse a singly linked list.

click to show more hints.

Hint:
A linked list can be reversed either iteratively or recursively. Could you implement both?
*/

public class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode newtail = null;
        while(head != null) {
            ListNode next = head.next;
            head.next = newtail;
            newtail = head;
            head = next;
        }
        return newtail;
    }
}


public class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode reversed = reverseList(head.next);
        ListNode tmp = reversed;
        while(tmp != null && tmp.next != null) {
            tmp = tmp.next;
        }
        head.next = null;
        tmp.next = head;
        return reversed;
    }
}
