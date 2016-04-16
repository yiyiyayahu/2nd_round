/*
Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

Hint:

Beware of overflow.
*/

/*
multiplier makes the digit two parts
314152
if m = 1000, then it breaks into 314, 152, and to count the number of digit one in thousands, that would be
(1+31)*1000=32000 (4 is larger than 1, and thus, for every 0-31, there would be 1000-1999's ones in thousands)
if m = 100, then it breaks into 3141, 52, and to count the number of digit one in hundreds, that would be
314*100 + 53 (1 equals to 1, thus, for every 0-313, there would be 100-199, however for 314, there would only be 100-152, in total 53)

remember to do (a/10)*m or (a/10+1)*m, not directly a*m...
*/
public class Solution {
    public int countDigitOne(int n) {
        int count = 0;
        for(long m = 1; m <= n; m*=10) {
            long a = n/m, b = n%m;
            if(a%10 > 1) count += (a/10+1)*m;
            else if(a%10 == 1) count += a/10*m + (b+1);
            else count += a/10*m;
        }    
        return count;
    }
}

/*
https://leetcode.com/discuss/44281/8-lines-o-log-n-c-java-python
http://www.cnblogs.com/grandyang/p/4629032.html

这道题我觉得不好想啊啊啊啊，而且感觉没什么意思呢
比如56，十位上有(5+1)个，个位上也就是10-19有10个，6+10=16
可以用(x+8)/10来判断一个数是否大于等于2。

我之前的理解完全是错误的，什么乱七八糟的，删掉了
*/

public class Solution {
    public int countDigitOne(int n) {
        int ones = 0;
        for (long m = 1; m <= n; m *= 10)
            ones += (n/m + 8) / 10 * m + (n/m % 10 == 1 ? n%m + 1 : 0);
        return ones;        
    }
}
