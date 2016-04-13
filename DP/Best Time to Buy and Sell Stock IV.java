/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/


/*
我觉得还是这个思路比较好想：
f[i][k] ： 1）只进行k-1次交易
           2）进行k次交易：a) i is not involved b) i is involved: prices[i] - prices[j]

f[i][k] = max |- f[i][k-1]
              |- max |- f[i-1][k]
                     |- f[j-1][k-1] + prices[i] - prices[j]   0<j<i
Let : maxCurr = f[j-1][k-1] - prices[j] (j starting from i-1, calculate maxCurr while iterating)
*/

public class Solution {
    public int maxProfit(int k, int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        
        int len = prices.length;
        if( k >= len/2 ) return profitNoLimit(prices);
        
        int[][] f = new int[len][k+1];
        for(int j = 1; j <= k; j++) {
            int maxCurr = 0 - prices[0];
            for(int i = 1; i < len; i++) {
                maxCurr = Math.max(maxCurr, i>1 ? f[i-2][j-1]-prices[i-1] : 0-prices[i-1]);
                int tmp = maxCurr + prices[i];
                if(tmp < f[i-1][j]) tmp = f[i-1][j];
                f[i][j] = Math.max(f[i][j-1], tmp);
            }
        }
        return f[len-1][k];
    }
    
    public int profitNoLimit(int[] prices) {
        int profit = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[i-1]) {
                profit += prices[i] - prices[i-1];
            }
        }
        return profit;
    }
}

/*
General thinking: DP
 - either the (i-1)th day is involved or not
prices[i][j] = prices[i-1][j], if nothing happened on i-1th day
             = prices[i-1][j-1] + prices[i]-prices[i-1], if the transaction between i-1 and i is the last transaction
->
prices[i][j] = max(prices[i-1][j], prices[i-1][j-1] + prices[i]-prices[i-1]) 

- issue: prices[i-1][j-1] may involve the transaction of the i-1th day, if that happens, then the transaction from i to i-1 can be conbined, so in total only j-1 transactions, which is not the maximum j transactions we are talking about, may lose some possibilities

local[i][j] - must have transaction on the ith day
global[i][j] - currently the max profit in the first ith days and with j transactions

local[i][j] = global[i-1][j-1]) if prices[i] > prices[i-1]
            = local[i-1][j] + prices[i] - prices[i-1]
global[i][j] = Math.max(global[i-1][j], local[i][j])

写这道题的时候注意：
1. k>=len的时候就转化为II transaction多少次都可以了,只要后面比前面大，咱就交易
2. 数组长度其实是k+1
这道题巧妙的是局部最优和全局最优，再多想想多做做这类题

二维dp其实都可以压缩到一维
然后因为dp[i][j]要看i-1,j-1和i-1,j的情况的,所以从后往前推比较方便
*/
public class Solution {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if(len == 0) return 0;
        if(k >= len/2) {
            int max = 0;
            for(int i = 1; i < len; i++) {
                if(prices[i] > prices[i-1]) {
                    max += prices[i] - prices[i-1];
                }
            }
            return max;
        }

        int[] local = new int[k+1];
        int[] global = new int[k+1];
        for(int i = 1; i < len; i++) {
            int diff = prices[i] - prices[i-1];
            for(int j = k; j > 0; j--) {
                local[j] = Math.max(local[j]+diff, global[j-1]);
                global[j] = Math.max(global[j], local[j]);
            }
        }
        return global[k];  
    }
}

public class Solution {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if(len == 0) return 0;
        if(k >= len/2) {
            int max = 0;
            for(int i = 1; i < len; i++) {
                if(prices[i] > prices[i-1]) {
                    max += prices[i] - prices[i-1];
                }
            }
            return max;
        }

        int[][] local = new int[len][k+1];
        int[][] global = new int[len][k+1];
        for(int i = 1; i < len; i++) {
            int diff = prices[i] - prices[i-1];
            for(int j = 1; j <= k; j++) {
                local[i][j] = Math.max(local[i-1][j] + diff, global[i-1][j-1]);
                global[i][j] = Math.max(global[i-1][j], local[i][j]);
            }
        }        
        return global[len-1][k];   
    }
}
