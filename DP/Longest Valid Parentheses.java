/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4. 
*/

public class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int maxLen = 0, len = s.length();
        int start = 0;
        for(int i = 0; i < len; i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if(stack.isEmpty()) {
                    start = i+1;
                } else {
                    stack.pop();
                    maxLen = Math.max(maxLen, stack.isEmpty()?i-start+1:i-stack.peek());
                }
            }
        } 
        return maxLen;
    }
}
