/*
 Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed. 
*/

/*
链表还是不熟练，写了好久，还是用eclipse debug来着，总有NPE
关于while循环里面，光是first != null是不够的
而且代码写的还不简洁，多多练习
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
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode prev = new ListNode(-1);
        ListNode first = head;
        prev.next = first;
        ListNode tmp = prev;
        ListNode second, next;
        

        while(first != null && first.next != null) {
        	second = first.next;
            next = second.next;
            
            prev.next = null;
            first.next = null;

            second.next = first;
            prev.next = second;
            first.next = next;

            prev = first;
            first = first.next;
        }

        return tmp.next;      
    }
}
