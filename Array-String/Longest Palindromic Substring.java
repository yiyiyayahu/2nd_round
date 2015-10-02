/*
Given a string S, find the longest palindromic substring in S. 
You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
*/

/*
就是从中间开始扩展
*/
public class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return s;
        int len = s.length();
        int maxLen = 0;
        String result = "";

        for(int i = 0; i < 2*len - 1; i++) {
            int left = i/2; int right = i/2;
            if(i % 2 == 1) right ++;
            
            String str = expandFromCenter(s, left, right);
            if(str.length() > maxLen) {
                result = str;
                maxLen = str.length();
            }
        }

        return result;
    }
    
    public String expandFromCenter(String s, int left, int right) {
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left --;
            right ++;
        }
        return s.substring(left+1, right);
    }
}
