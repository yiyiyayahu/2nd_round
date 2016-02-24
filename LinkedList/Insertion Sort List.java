/*
Sort a linked list using insertion sort.
*/


/*
犯的一个错误是：当找到tmp使得curr.val<tmp.val时，我直接将tmp.next指向next，但是这样就可能剪短了这个list
比如3,2,1第一轮，变成了2,3,1，这时候curr.val=1，tmp.val=2，于是把1挪到了2的前面，可是，这时候应该是3指向next而不是2对吧！
后来我想不出什么好方法，因为这个list是在变的嘛，我的preCurr就每次都再从头移过来
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
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode fakehead = new ListNode(Integer.MIN_VALUE);
        fakehead.next = head;
        
        ListNode curr = head;
        while(curr != null) {
            ListNode prev = fakehead, tmp = prev.next, next = curr.next;
            ListNode preCurr = tmp;
            while(preCurr != null && preCurr.next != curr) preCurr = preCurr.next;
            while(tmp != curr) {
                if(curr.val < tmp.val) {                     	
                    prev.next = curr;
                    curr.next = tmp;
                    preCurr.next = next;
                    break;
                } else {
                    prev = prev.next;
                    tmp = tmp.next;
                }
            }              
            curr = next;
        }
        return fakehead.next;
    }
}
