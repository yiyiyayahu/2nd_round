/*
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
*/

/*
这道题写了好多遍，囧
我主要纠结这么几点
3->3->4->4这种，删完3之后不能直接把头指针移到4，因为4也是要删掉的
1->1->1这种

如果用两个pointer就简单很多，curr一直挪到下一个节点不是duplicate，这样就可以prev.next = curr.next
如果prev.next==curr就说明curr不是duplicate，prev就可以直接后移了
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
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode fakehead = new ListNode(-1);
        fakehead.next = head;
        
        ListNode prev = fakehead;
        ListNode curr = head;
        
        while(curr != null) {
            while(curr.next != null && prev.next.val == curr.next.val) {
                curr = curr.next;
            }
            if(prev.next == curr) {
                prev = prev.next;
            } else {
                prev.next = curr.next;
            }
            curr = curr.next;
        }
        return fakehead.next;
    }
}
