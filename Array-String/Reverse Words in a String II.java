/*
Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?

Related problem: Rotate Array
*/

/*
两遍reverse，第一遍全部reverse，第二遍再把每个单词恢复顺序
*/
public class Solution {
    public void reverseWords(char[] s) {
        reverse(s, 0, s.length-1);
        int start = 0;
        for(int i = 0; i < s.length; i++) {
            if(i == s.length-1) reverse(s, start, i);
            else if(s[i] == ' ') {
                reverse(s, start, i-1);
                start = i+1;
            }
        }
    }
    
    public void reverse(char[] s, int start, int end) {
        while(start < end) {
            char tmp = s[start];
            s[start] = s[end];
            s[end] = tmp;
            start ++;
            end --;
        }
    }
}
