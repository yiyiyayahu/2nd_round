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
/*
这个和下面的差不多，但是感觉自己思路清晰了一些
*/
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode fake = new ListNode(-1);
        fake.next = head;
        
        ListNode prev = fake;
        ListNode fst = head;
        
        while(fst != null && fst.next != null) {
            ListNode sec = fst.next;
            ListNode next = sec.next;
            
            prev.next = sec;
            fst.next = next;
            sec.next = fst;
            
            prev = fst;
            fst = next;
        }
        return fake.next;
    }
}

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

            second.next = first;
            prev.next = second;
            first.next = next;

            prev = first;
            first = first.next;
        }

        return tmp.next;      
    }
}
//递归写起来简单些，但是不是constant space，因为recursive用的是stack空间，应该是O(n)
//stack存放参数和function返回地址的，push和pop。。
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode p = head;
        ListNode n = swapPairs(p.next.next);
        
        ListNode next = p.next;
        p.next = n;
        next.next = p;
        
        return next;
    }
}
