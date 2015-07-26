/*
Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), 
return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?

Related problem: Reverse Integer
*/

/*
优化方法：
https://leetcode.com/discuss/27338/8ms-c-code-some-ideas-about-optimization-spoiler
以4位为单位执行反转，将0x0至0xF的反转结果预存在一个长度为16的数组中，反转时直接查询即可。
*/

/*
java这个unsigned int不晓得肿么搞，为啥n >>>= 1;这个就可以捏
*/
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        int digits = 32;
        while (n != 0) {
            result <<= 1;
            result |= n&1;
            n >>>= 1;
            digits --;
        }
        result <<= digits;
        
        return result;
    }
}
