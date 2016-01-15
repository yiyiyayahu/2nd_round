/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

prices = [1, 2, 3, 0, 2]
maxProfit = 3
transactions = [buy, sell, cooldown, buy, sell]
*/

/*
开始是这么想的，要么就是dp[i-1]，要么就是i天参与交易
那就是j在[0,i-1]之间，然后i和j交易，j-1天在cool down，那么就是dp[j-2]+prices[i]-prices[j]

不过这个是可以优化的对吧，就是里面那个cooldown那里也可以是个小dp
*/
public class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len < 2) return 0;
        
        int[] dp = new int[len];
        for(int i = 1; i < len; i++) {
            int tmpMax = 0;
            for(int j = 0; j < i; j++) {
                int diff = prices[i] - prices[j];
                if(diff > 0) {
                    if(j < 2) tmpMax = Math.max(tmpMax, diff);
                    else tmpMax = Math.max(tmpMax, dp[j-2] + diff);
                }
            }
            dp[i] = Math.max(tmpMax, dp[i-1]);
        }
        return dp[len-1];
    }
}
