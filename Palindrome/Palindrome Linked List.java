/*
Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?
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
 I. use a stack, traverse the linked list twice
    time: O(n) space: O(n)
 */
public class Solution {
    public boolean isPalindrome(ListNode head) {
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode tmp = head;
        while(tmp != null) {
            stack.push(tmp);
            tmp = tmp.next;
        }
        
        tmp = head;
        while(tmp != null) {
            if(tmp.val != stack.pop().val) return false;
            tmp = tmp.next;
        }
        return true;
    }
}
/*
II. reverse second half of the linked list and compare with the first half
*/

/*
III. recursively: use the recursion acting like a stack
*/

/*
http://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
*/
