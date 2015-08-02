/*
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', 
return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example, 
Given s = "Hello World",
return 5.
*/

/*
我发现我的code写的是比以前简洁了哈~~~ 说明还是进步了点的
*/
public class Solution {
    public int lengthOfLastWord(String s) {
        if(s == null || s.length() == 0) return 0;
        
        int ret = 0, i = s.length()-1;
        while(i >= 0 && s.charAt(i) == ' ') i--;
        while(i >= 0 && s.charAt(i) != ' ') {
            ret ++;
            i--;
        }
        return ret;
    }
}
