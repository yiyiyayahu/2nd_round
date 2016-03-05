/*
Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5
*/

public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode curr = dummy;
        while(curr.next != null) {
            if(curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return dummy.next;
    }
}

public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return null;
        
        ListNode n = new ListNode(-1);
        n.next = head;
        ListNode tmp = n;
        while(tmp != null) {
            while(tmp.next != null && tmp.next.val == val) {
                tmp.next = tmp.next.next;
            } 
            tmp = tmp.next;
        }
        return n.next;
    }
}
