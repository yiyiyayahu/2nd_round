/*
'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
*/

/*
在昨儿晚上做出regular expression matching之后，可算是把这道题做出来了，而且现在觉得不是很难了，好凯西
其实如果说用dp做的话，还是regular expression matching比较难
这个应该还可以缩略一点，就是二维数组压缩成一维，一会儿再想想好啦
开心~~~
*/
public class Solution {
    public boolean isMatch(String s, String p) {
        int slen = s.length(), plen = p.length();
        boolean[][] dp = new boolean[slen+1][plen+1];

        dp[0][0] = true;
        for(int j = 1; j <= plen; j++) {
            if(p.charAt(j-1) == '*') dp[0][j] = true;
            else break;
        }
        for(int i = 1; i <= slen; i++) {
            for(int j = 1; j <= plen; j++) {
                char c = p.charAt(j-1);
                if(c == '?' || c == s.charAt(i-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else if(c == '*') {
                    if(j > 1 && p.charAt(j-2) == '*') {
                        dp[i][j] = dp[i][j-1]; 
                    } else {
                        for(int k = i; k >= 0; k--) {
                            if(dp[k][j-1]) dp[i][j] = true;
                        }
                    }
                }
            }
        }   
        return dp[slen][plen];
    }
}