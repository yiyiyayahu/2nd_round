/*
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
For example, given the range [5, 7], you should return 4. 
*/

/*
我觉得这种题好难想啊
最后的结果就是m和n左边都是1的地方，剩下其它位置0
比如5,6,7  101,110,111 -> 100 5和7只有第一位都是1，所以从5到7所有数字and以后会把后面的位都置0
*/

public class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int i = 0;
        while(m != n) {
            m >>= 1;
            n >>= 1;
            i ++;
        }
        return m << i;
    }
}
