/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
*/

/*
要注意几点：
1. 不考虑大小写的话可以先把s都转成小写的。而且这道题要考虑number的，不只是字母
2. 开始写成了while(!isAlpha(s.charAt(start))) start++; 这个就没有考虑range的问题，改成if，continue就比较好
时间复杂度O(n) 空间O(1)
*/

public class Solution {
    public boolean isPalindrome(String s) {
        if(s == null || s.length() == 0) return true;
        int len = s.length();
        s = s.toLowerCase();
        int start = 0, end = len-1;
        while(start <= end) {
            if(!isAlpha(s.charAt(start))) {
                start ++;
                continue;
            }
            if(!isAlpha(s.charAt(end))) {
                end --;
                continue;
            }
            if(s.charAt(start) != s.charAt(end)) return false;
            start ++; 
            end --;
        }
        return true;
    }
    
    public boolean isAlpha(char c) {
        return (c>='a' && c<='z') || (c>='0' && c<='9');
    }
}
