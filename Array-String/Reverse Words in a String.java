/*
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Update (2015-02-12):
For C programmers: Try to solve it in-place in O(1) space.

click to show clarification.

Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.
*/

/*
开始用java自带函数s.trim(); s.replaceAll("\\s+", " "); 但是这样就没有意义了呀
所以one pass，从后往前扫描，找到一个word的beginning index, ending index就好了
leetcode那本书写的挺好的
先判断s.charAt(i) == ' '，就把那些多余的空格去掉了，然后更新j=i
这样再看if (i == 0 || s.charAt(i-1) == ' ')就是能找到当前的beginning index了

还有开始我trim了之后对于空格的处理是这样的
sb.append(s.substring(i,j));
if(i!=0) sb.append(' ');
但是这样有个问题就是如果有leading space的话，我的末尾一直是有这样一个空格的
所以不如先append(' ')
*/
public class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int j = s.length();
        for(int i = s.length() - 1; i >= 0; i--) {
        	if(s.charAt(i) == ' ') {
        		j = i;
        	} else if(i == 0 || s.charAt(i-1) == ' ') {
                if(sb.length() != 0) sb.append(' ');
                sb.append(s.substring(i,j));
                j = i-1;
            } 
        }
        return sb.toString();
    }
}
