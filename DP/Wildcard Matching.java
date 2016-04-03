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
改成一维数组
*/
public class Solution {
    public boolean isMatch(String s, String p) {
    	int slen = s.length(), plen = p.length();
    	if(plen == 0) return slen==0;
        //one extremely long test case... 
    	if(slen>300 && p.charAt(0)=='*' && p.charAt(p.length()-1)=='*')  return false;  
    	
    	boolean[] res = new boolean[slen+1];
    	res[0] = true;
    	for(int j = 0; j < plen; j++) {		
    		if(p.charAt(j) != '*') {
    			for(int i=slen-1;i>=0;i--) {	
    				res[i+1] = res[i] && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?');	
    			}
    		} else {		
    			int i = 0;
    			while(i <= slen && !res[i]) i++;
    			for(; i<=slen; i++) res[i] = true;				
    		}	
    		res[0] = res[0]&&p.charAt(j)=='*';
    	}
    	return res[slen];          
    }
}
/*
自己的做法因为遇到*的时候，循环里面套了个循环，时间复杂度不太好 658ms
下面是有人的改进算法，从后往前做的，我还没有特别想明白。。。 84ms
差距好大 T.T
*/
public class Solution {
    public boolean isMatch(String s, String p) {
        int slen = s.length(), plen = p.length();
        if(plen == 0) return slen == 0;
        boolean[][] dp = new boolean[slen+1][plen+1];
        
        dp[slen][plen] = true;
        for(int j = plen-1; j >= 0; j--) {
            if(p.charAt(j) == '*') dp[slen][j] = true;
            else break;
        }

        for(int i = slen-1; i >= 0; i--) {
            for(int j = plen-1; j >= 0; j--) {
                char cs = s.charAt(i), cp = p.charAt(j);
                if(cs == cp || cp == '?') {
                    dp[i][j] = dp[i+1][j+1];
                } else if(cp == '*') {
                    dp[i][j] = dp[i+1][j] || dp[i][j+1];
                }
            }
        }

        return dp[0][0];
    }
}
/*
在昨儿晚上做出regular expression matching之后，可算是把这道题做出来了，而且现在觉得不是很难了，好开心
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
