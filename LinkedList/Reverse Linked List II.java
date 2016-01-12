/*
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list. 
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
