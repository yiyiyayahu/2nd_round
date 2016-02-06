/*
Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example:
Given 1->2->3->4->5->NULL,
return 1->3->5->2->4->NULL.

Note:
The relative order inside both the even and odd groups should remain as it was in the input. 
The first node is considered odd, the second node even and so on ...
*/

/*
我这道题都写了好久，一道easy的题啊。。。摔
总觉得把这道题写复杂了
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
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode pre = head;
        ListNode curr = head;
        int count = 1;
        ListNode tmp = head;
        
        while(curr != null) {
            if(count == 1) {
                curr = curr.next;
            } else if(count %2 == 0) {
                pre = pre.next;
                curr = curr.next;
            } else {
                ListNode next = tmp.next;
                tmp.next = curr;
                pre.next = curr.next;
                curr.next = next;
                
                tmp = curr;
                curr = pre.next;
            }
            count++;
        }
        return head;
    }
}
