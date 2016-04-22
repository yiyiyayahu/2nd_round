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
找规律的一道题，居然只要能让3当倍数就让3当倍数。。。
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
