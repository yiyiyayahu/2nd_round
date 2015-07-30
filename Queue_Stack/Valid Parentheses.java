/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/

/*
犯了好多错误啊。。。
开始写的是Stack<String>。。。囧，显然type不对啊。。。
在stack.pop()之前没有考虑stack是empty的情况
最后跳出for循环也没考虑到stack有可能还不是empty
唉，做的太急了，还是要确定了再提交。。。
*/
public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        int len = s.length();
        if(len%2 == 1) return false;

        for(int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if( c=='(' || c=='[' || c=='{') stack.push(c);
            else {
                if(stack.isEmpty()) return false;
                char p = stack.pop();
                if((c == ')' && p == '(') || (c == ']' && p == '[') || (c == '}' && p == '{')) {
                	continue;
                } else {
                	return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
