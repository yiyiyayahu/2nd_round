/*
Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. 
For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.

Note:
(1) 1 is a super ugly number for any given primes.
(2) The given numbers in primes are in ascending order.
(3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
*/

/*
和ugly number II的解法差不多，唯一的区别就是从3个2，3，5固定的质数扩展到k个
一直不清楚提示的Heap是什么解法，可以再研究一下
Heap的做法时间复杂度不是很好，比如用最大堆来找最小的，那么heap的操作要用掉O(logk)
*/
public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int k = primes.length;
        
        int[] dp = new int[n];
        dp[0] = 1;
        int[] index = new int[k];
        
        for(int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < k; j++) {
                min = Math.min(min, dp[index[j]] * primes[j]);
            }
            dp[i] = min;
            for(int j = 0; j < k; j++) {
                if(min == dp[index[j]] * primes[j]) index[j] ++;
            }
        }
        return dp[n-1];
    }
}
