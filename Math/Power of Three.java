/*
Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion?
*/

/*
额，这题我只会用loop那种的，下面这个好巧妙啊，就是在int里面最大的3的倍数（貌似是19次方）是1162261467
所以只要1162261467%n==0就说明是3的倍数
这里要注意必须同时n>0才行
*/

public class Solution {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
