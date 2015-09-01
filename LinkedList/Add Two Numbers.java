/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/*
You are given two linked lists representing two non-negative numbers. 
The digits are stored in reverse order and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
*/

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode ret = new ListNode(-1);
        ListNode n = ret;
        
        int carry = 0;
        while(l1 != null || l2 != null) {
            int num = carry;
            num += (l1!=null) ? l1.val : 0;
            num += (l2!=null) ? l2.val : 0;
            if(num >= 10) {
                carry = 1;
                num = num - 10;
            } else {
                carry = 0;
            }
            n.next = new ListNode(num);
            n = n.next;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        if(carry == 1) n.next = new ListNode(1);
        return ret.next;
    }
}
