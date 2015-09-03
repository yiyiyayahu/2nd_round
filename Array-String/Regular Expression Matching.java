/*
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
*/

/*
这道题好难写啊啊啊
考虑两个字符就比较好，然后recursive
http://articles.leetcode.com/2011/09/regular-expression-matching.html

这道题比较难的是这个*的处理上
开始想的是，比如aaaa,a*，从s的0位开始查，match就i++，然后return isMatch(s.substring(i), p.substring(2))
这样做的问题是，aaaa,a*aaaa，这个也是match的啊，但是我这样做的话就return false
*/
public class Solution {
    public boolean isMatch(String s, String p) {
        if(p.length() == 0) return s.length() == 0;
        if(p.length() == 1) {
            if(s.length() != 1) return false;
            if(p.charAt(0) == '.') return true;
            return p.charAt(0) == s.charAt(0);
        }
        if(p.charAt(1) != '*') {
            if(s.length() < 1) return false;
            if(p.charAt(0) != '.' && s.charAt(0) != p.charAt(0)) return false;
            return isMatch(s.substring(1), p.substring(1));
        } else {
            //if no match, important
            if(isMatch(s, p.substring(2))) return true;
            
            int i = 0;
            while(i < s.length() && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')) {
                if(isMatch(s.substring(i+1), p.substring(2))) return true;
                i++;
            }
        }
        return false;
    }
}
