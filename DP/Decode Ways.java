/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
*/

/*
这道题写了好久。。。
首先是思路，其实应该是
dp[i]表示string 0到i的# of decoding ways
dp[i] = --dp[i-1]
        |-dp[i-1]+dp[i-2]    if (s.charAt(i-1) + s.charAt(i)) - > "10"-"26"
        
难点的处理就在0
比如当前是0的话，检测前一个数字，如果是0或者3以上的数字，那么就无解，返回0
但是有几个case我没考虑到
12120，检测到1212的时候，是考虑到最后的那个2可以和1结合的，所以就是dp[12]+dp[121]，但是现在最后的那个0显然只能和前面的2结合，所以dp[1212]=dp[121]
然后我开始的做法是，既然我多算了这个dp[12]那么在处理到最后的0的时候就把多加的这个dp[12]减掉
但是还有个问题，比如16205，这个2是无法跟前面的6结合在一起的，但是我也错误的减掉了。。。

我后来的做法是，检测三个，当前，前一个，后一个，如果我发现后一个是0的话，我没有办法和前面的结合，那么就是dp[i]=dp[i-1]不加上之前的dp[i-2]了
*/

/*
转化为int来判断是否在10到26之间能简单点，但是都可以啦
*/
public class Solution {
    public int numDecodings(String s) {
        int len = s.length();
        if(len == 0 || s.charAt(0) == '0') return 0;
        
        int[] dp = new int[len+1];
        dp[0] = 1; dp[1] = 1;
        for(int i = 1; i < len; i++) {
            int curr = (int)(s.charAt(i)-'0');
            int prev = (int)(s.charAt(i-1)-'0');
            char next = (i < len-1? s.charAt(i+1) : 'c');
            
            int num = prev * 10 + curr;
            if(curr == 0) {
                if(prev >= 3 || prev == 0) return 0; 
                dp[i+1] = dp[i];
            } else if(next != '0' && (num>=10 && num<=26) ) {
                dp[i+1] = dp[i] + dp[i-1];
            } else {
                dp[i+1] = dp[i];
            }
        }
        return dp[len];
    }
}

public class Solution {
    public int numDecodings(String s) {
        int len = s.length();
        if(len == 0 || s.charAt(0) == '0') return 0;
        
        int[] dp = new int[len+1];
        dp[0] = 1; dp[1] = 1;
        for(int i = 1; i < len; i++) {
            char curr = s.charAt(i);
            char prev = s.charAt(i-1);
            char next = (i < len-1? s.charAt(i+1) : 'c');
            
            if(curr == '0') {
                if(prev >= '3' || prev == '0') return 0; 
                dp[i+1] = dp[i];
            } else if(next != '0' && (prev == '1' || (prev == '2' && curr >='0' && curr <= '6') )) {
                dp[i+1] = dp[i] + dp[i-1];
            } else {
                dp[i+1] = dp[i];
            }
        }
        return dp[len];
    }
}
