/*
Sort a linked list in O(n log n) time using constant space complexity.
*/

/*
就是mergesort，我觉得我对于mergesort的理解有点深入了？这道题关键在于merge对吧，其实最后就是分解为一个一个的，然后一点点merge起来
开始写错了一个就进入无限循环了，就是fast=head，如果只有两个元素的话，1-2，slow.next是null，然后就会一直调用sortList(head)一直循环下去
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
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode next = slow.next;
        slow.next = null;
        ListNode fst = sortList(head);
        ListNode snd = sortList(next);
        
        return merge(fst, snd);
    }

    public ListNode merge(ListNode fst, ListNode snd) {
        ListNode fake = new ListNode(-1);
        ListNode tmp = fake;
        while(fst != null && snd != null) {
            if(fst.val < snd.val) {
                tmp.next = new ListNode(fst.val);
                fst = fst.next;
            } else {
                tmp.next = new ListNode(snd.val);
                snd = snd.next;
            }
            tmp = tmp.next;
        }
        tmp.next = (fst != null) ? fst : snd;
        return fake.next;
    }    
}
