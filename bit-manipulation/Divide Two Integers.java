/*
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
*/

/*
这个根本不是binary search嘛。。。明明就是bit manipulation
其实无非就是看这个divisor*(2^n + 2^(n-1) + ... + 1) = dividend，然后算2^n + 2^(n-1) + ... + 1的和。。。
还是一样的问题，注意溢出
*/

public class Solution {
    public int divide(int dividend, int divisor) {
        if(divisor == 0) return Integer.MAX_VALUE;
        if(dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        
        boolean isNeg = (dividend < 0) ^ (divisor < 0);
        long a = (long) dividend, b = (long) divisor;
        
        if(a < 0) a = -a;
        if(b < 0) b = -b;
        
        int ret = 0;
        while(a >= b) {
            long tmp = b;
            int tmpCount = 1;
            while( (tmp<<1) < a) {
                tmp <<= 1;
                tmpCount *= 2;
            }
            ret += tmpCount;
            a -= tmp;
        }
        return isNeg ? -ret : ret;
    }
}
