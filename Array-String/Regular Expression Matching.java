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
终于把dp版本的写出来了，太特么的不容易了！！！而且还是看了点答案改的
对dp可能还是不太熟，是dp就想递推，别又想着想着往recursive上面想去了

然后需要考虑的一些edge case：
1. "a" "c*a" 此时c*和谁都不match
2. "a" "ac*" 和上面的差不多
3. "" ".*" 此时返回true
4. "" "..*" 此时返回false

这里dp的初始化要注意，我开始只写了dp[0][0]=true但是显然是不够的，参照case 3，此时dp[0][2]的位置上应该是true
但是呢，要注意的是像case 4，我发现index 1的位置上面不是*，那后面的就都是false了，没必要再做下去就可以break出来了

接下来是递推，开始写的特别复杂
dp[i][j] = 
1) 如果当前的p不是*的话，那么就比较简单了，看dp[i-1][j-1]和当前的s和p是否match
2）如果p是*，有两种可能，这里还要看p里面*前的那个char prev：
        a) 如果prev和当前的s.charAt(i)match，那么一种是这个*代表一个prev -dp[i-1][j]，比如aa，a*，检测到*的时候，发现当前的a和*前的a一样
           当然也可以a*不match任何的序列 - dp[i][j-2]
           （比如"a"， "aa*"，如果不考虑dp[i][j-2]的话就return false）
        b) prev和当前的s不match，那么这个prev*组合不match任何序列 - dp[i][j-2]
*/
public class Solution {
    public boolean isMatch(String s, String p) {
        int slen = s.length(), plen = p.length();
        if(plen == 0) return slen == 0;
        
        boolean[][] dp = new boolean[slen+1][plen+1];
        dp[0][0] = true;
        
        for(int j = 1; j <= plen; j++) {
            if(j % 2 == 0) { 
                if(p.charAt(j-1) == '*') dp[0][j] = true;
                else break;
            } 
        }
        
        for(int i = 1; i <= slen; i++) {
            for(int j = 1; j <= plen; j++) {
                char curr = p.charAt(j-1);
                if(curr == '.' || curr == s.charAt(i-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else if(curr == '*') {
                    char prev = p.charAt(j-2);
                    if(prev == '.' || prev == s.charAt(i-1)) {
                        dp[i][j] = dp[i-1][j] || dp[i][j-2];
                    } else {
                        dp[i][j] = dp[i][j-2];
                    }
                } 
            }
        }

        return dp[slen][plen];      
    }
}

/*
自己的烂code
*/
public class Solution {
    public boolean isMatch(String s, String p) {
        int slen = s.length(), plen = p.length();
        if(plen == 0) return slen == 0;
        
        boolean[][] dp = new boolean[slen+1][plen+1];
        dp[0][0] = true;
        
        for(int j = 0; j < plen; j++) {
            if(j % 2 == 1) { 
                if(p.charAt(j) == '*') dp[0][j+1] = true;
                else break;
            } 
        }
        
        for(int i = 0; i < slen; i++) {
            for(int j = 0; j < plen; j++) {
                char curr = p.charAt(j);
                char prev = j-1<0 ? '?' : p.charAt(j-1);
                
                if(curr != '*') {
                    if( curr == '.' || curr == s.charAt(i) ) {
                        if(dp[i][j]) dp[i+1][j+1]=true; 
                        if(prev == '*' && j>1&&dp[i][j-2]) dp[i+1][j+1]=true;
                    }
                } else {
                    if(dp[i+1][j] || (j>0&&dp[i+1][j-1]) ) dp[i+1][j+1] = true;
                    else if(dp[i][j+1] && (prev=='.'||prev==s.charAt(i))) {
                        dp[i+1][j+1] = true;
                    } 
                }
            }
        }
        return dp[slen][plen];      
    }
}

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
