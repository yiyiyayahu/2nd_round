/*
 Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}. 
*/

/*
开始的做法是，一个指针移到最后，找到最后节点的node和它前面的node，挪一下，再recursive调用reorderList(head.next)
结果果断TLE，因为我每次都traverse一遍整个list，如果list很长呢。。。

后来看了一下以前的解法：先找到中间那个点，然后把后半部分reverse一下，再和前面的结合
唉，自己为啥又没想出来。。。
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
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;

        ListNode slow = head;
        ListNode fast = head;
        while(fast!= null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode end = reverse(slow.next);
        slow.next = null;
        ListNode fst = head;
        ListNode snd = end;
        while(fst != null && snd != null) {
            ListNode fstnext = fst.next;
            ListNode sndnext = snd.next;
            fst.next = snd;
            snd.next = fstnext;
            fst = fstnext;
            snd = sndnext;
        }
    }

    public ListNode reverse(ListNode node) {
        if(node == null || node.next == null) return node;
        
        ListNode tmp = node;
        ListNode tail = null;
        while(tmp != null) {
            ListNode next = tmp.next;
            tmp.next = tail;
            tail = tmp;
            tmp = next;
        }
        return tail;
    }     
}

/*
TLE的解法，注意这里还是要把prev.next=null就是要unlink一下
*/
public class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return;
        ListNode prev = head;
        ListNode end = head;

        while(prev.next != null && prev.next.next != null) {
            prev = prev.next;
        }
        end = prev.next;
        ListNode next = head.next;
        head.next = end;
        prev.next = null;
        end.next = next;
        reorderList(next);      
    }
}
