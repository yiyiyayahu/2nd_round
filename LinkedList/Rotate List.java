/*
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
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
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        
        ListNode end = head;
        int len = 1;
        while(end.next != null) {
            end = end.next;
            len ++;
        }
        k = k % len;
        if(k == 0) return head;
        
        ListNode p = null, prev = head;
        for(int i = 1; i < len - k; i++) {
            prev = prev.next;
        }
        p = prev.next;
        prev.next = null;
        end.next = head;
        return p;
    }
}
