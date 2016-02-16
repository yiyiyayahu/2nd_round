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
又重新写了一遍，其实这道题用recursive做还是可以的
但是我跪在了：判断slen plen上，有两处：
1. if(plen == 0) return slen == 0;
2. if(next != '*') {
        if(slen < 1) return false;
        。。。
    }
*/
public class Solution {
    public boolean isMatch(String s, String p) {
        int slen = s.length(), plen = p.length();
        if(plen == 0) return slen == 0;
        
        char c = p.charAt(0);
        if(plen == 1) {
            if(slen != 1) return false;
            return c == s.charAt(0) || c == '.';
        }
        
        char next = p.charAt(1);
        if(next != '*') {
            if(slen < 1) return false;
            if(c != '.' && s.charAt(0) != c) return false;
            return isMatch(s.substring(1), p.substring(1));
        } 
        int i = 0;
        while(i < slen && (s.charAt(i) == c || c == '.')) {
            if(isMatch(s.substring(i), p.substring(2))) return true;
            i++;
        }
        return isMatch(s.substring(i), p.substring(2));
    }
}
/*
这道题好难写啊啊啊
考虑两个字符就比较好，然后recursive
http://articles.leetcode.com/2011/09/regular-expression-matching.html

这道题比较难的是这个*的处理上
开始想的是，比如aaaa,a*，从s的0位开始查，match就i++，然后return isMatch(s.substring(i), p.substring(2))
这样做的问题是，aaaa,a*aaaa，这个也是match的啊，但是我这样做的话就return false
所以在while loop中间 i++之前也要查一次，如果后面的都match了，就直接return true
还有就是.*和ab是match的
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
            int i = 0;
            while(i < s.length() && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')) {
                if(isMatch(s.substring(i), p.substring(2))) return true;
                i++;
            }
            return isMatch(s.substring(i), p.substring(2));
        }
    }
}
