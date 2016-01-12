/*
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m == n || head == null || head.next == null) return head;
        
        ListNode fakehead = new ListNode(-1);
        fakehead.next = head;

        ListNode s = head, begin = fakehead;
        while(m > 1) {
            begin = begin.next;
            s = s.next;
            m--;
        }

        ListNode e = head;
        while(n > 1) {
            e = e.next;
            n--;
        }
        ListNode end = e.next;
        e.next = null;
        begin.next = null;
        
        ListNode newtail = end;
        while(s != null) {
            ListNode next = s.next;
            s.next = newtail;
            newtail = s;
            s = next; 
        } 

        begin.next = newtail;
        return fakehead.next;
    }
}
