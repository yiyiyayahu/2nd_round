/*
Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. 
Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: you may assume that n is not less than 2.

Hint:

There is a simple O(n) solution to this problem.
You may check the breaking results of n ranging from 7 to 10 to discover the regularities.
*/

/*
找规律的一道题，居然只要能让3当倍数就让3当倍数。。。就所有数最好都分成2和3，3要尽可能多，就可以了。。。
当然dp也可以做啦，但是找到这个规律就没必要dp了
https://leetcode.com/discuss/98548/explanation-for-integer-break
*/
public class Solution {
    public int integerBreak(int n) {
        if(n == 2 || n == 3) return n-1;
        int ret = 1, tmp = n;
        while(tmp > 4) {
            ret *= 3;
            tmp -= 3;
        }
        return ret * tmp;
    }
}
