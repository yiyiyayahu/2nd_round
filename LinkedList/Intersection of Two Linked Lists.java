/*
Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
*/

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        
        int lenA = 0, lenB = 0;
        ListNode tmpA = headA, tmpB = headB;
        while(tmpA != null) {
            tmpA = tmpA.next;
            lenA ++;
        }
        while(tmpB != null) {
            tmpB = tmpB.next;
            lenB ++;
        }
        tmpA = headA; tmpB = headB;
        if(lenA > lenB) {
            for(int i = 0; i < lenA - lenB; i++) tmpA = tmpA.next;
        }
        if(lenA < lenB) {
            for(int i = 0; i < lenB - lenA; i++) tmpB = tmpB.next;
        }
        
        while(tmpA != null) {
            if(tmpA == tmpB) return tmpA;
            tmpA = tmpA.next;
            tmpB = tmpB.next;
        }
        return null;
    }
}
