/*
 You are given coins of different denominations and a total amount of money amount. 
 Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin. 
*/

/*
时间复杂度 O(nm), n = amount, m = coins.length
这道题没有简化一点的做法么~~~
*/

public class Solution {
    public int coinChange(int[] coins, int amount) {
        if(amount == 0 || coins.length == 0) return 0;
        
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for(int num = 1; num <= amount; num ++) {
            int tmp = Integer.MAX_VALUE;
            for(int i = 0; i < coins.length; i++) {
                int n = num - coins[i];
                if(n >= 0) tmp = Math.min(tmp, dp[n]);
            }
            dp[num] = (tmp == Integer.MAX_VALUE) ? Integer.MAX_VALUE : tmp+1;
        }
        if(dp[amount] == Integer.MAX_VALUE) return -1;
        return dp[amount];
    }
}
