/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/

/*
悲剧，TLE了
我觉得我的时间复杂度应该是O(n^2)，也就是说有O(n)的解法？
为啥第二遍刷我还是不记得

我是这样想的：
dp[i] = dp[i-1] (prices[i] not involve)
      = prices[i] - prices[k] + dp[k] (k in [0,i) )
dp[0] = 0
我测了一下，觉得程序应该没问题呀
*/

public class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        int len = prices.length;
        int[] dp = new int[len];
        dp[0] = 0;
        for(int i = 1; i < len; i++) {
            int tmpMax = 0;
            for(int k = i-1; k >= 0; k--) {
                if(prices[i] > prices[k]) {
                    tmpMax = Math.max(prices[i]-prices[k]+dp[k], tmpMax);
                }
            }
            dp[i] = Math.max(dp[i-1], tmpMax);
        }
        return dp[len-1];
    }
}
