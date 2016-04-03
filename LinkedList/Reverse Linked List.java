/*
Reverse a singly linked list.

click to show more hints.

Hint:
A linked list can be reversed either iteratively or recursively. Could you implement both?
*/

public class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode newtail = null;
        while(head != null) {
            ListNode next = head.next;
            head.next = newtail;
            newtail = head;
            head = next;
        }
        return newtail;
    }
}

/*
这个recursive的方法简洁一些
比如1->2->3->4
head.next -> node 2
ListNode p = reverseList(head.next); p是list的头: 4->3->2
这时候head.next还是2，只不过变成了尾巴，所以可以直接把head甩过去，head.next.next=head, head.next=null list就变成4->3->2->1
*/
public class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}

public class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode reversed = reverseList(head.next);
        ListNode tmp = reversed;
        while(tmp != null && tmp.next != null) {
            tmp = tmp.next;
        }
        head.next = null;
        tmp.next = head;
        return reversed;
    }
}
