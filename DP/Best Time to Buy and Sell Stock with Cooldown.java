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
这道题我现有的第二个解法的思路，但是时间复杂度是O(n^2) 肯定是不理想的，怎么转化为n呢
其实是这样的，比如我第i-1天卖掉或买入，从第i天的角度看，不一定是最优的，就像我之前写的j在0到i之间来了个loop是一样的
但是其实不需要loop，只要看前面的result，然后改进一下就行了
比如buy[i]表示在第i填买入，sell[i]表示在第i天卖出
那么buy[0]=-prices[0] sell[0]=0
sell[i] = max(sell[i-1]-prices[i-1]+prices[i],buy[i-1]+prices[i]-prices[i-1])前面那部分表示我在第i-1天卖出，但是我后悔了，变成第i天卖出；后面的表示i-1天买入，在第i天卖出
buy[i] = max(buy[i-1]+prices[i-1]-prices[i],sell[i-2]-prices[i])前面就是i-1天买后悔了，改成i天买；后面的因为cooldown的限制，只能是i-2天卖出，第i天才能买入
最后返回的应该是所有sell[i]最大的那个

唉，这个解法我不太想的出诶。。。
*/
public class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len < 2) return 0;
        
        int[] buy = new int[len];
        int[] sell = new int[len];
        buy[0] = -prices[0];

        int maxProfit = 0;
        for(int i = 1; i < len; i++) {
            int diff = prices[i] - prices[i-1];
            sell[i]= Math.max(sell[i-1]+diff, buy[i-1]+prices[i]);
            buy[i] = Math.max(buy[i-1]-diff, i < 2? -prices[i]:sell[i-2]-prices[i]);
            maxProfit = Math.max(maxProfit, sell[i]);
        }
        return maxProfit;
    }
}

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
