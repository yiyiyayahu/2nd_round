/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
*/

/*
一道还挺简单的dp，开始的时候还是思路不太清晰，还是递推式写出来之后比较管用，因为dp都是从前往后推的嘛
然后这里要注意Math.sqrt(n)返回的是double，要cast成int
*/
public class Solution {
    public int numSquares(int n) {
        int x = (int)(Math.sqrt(n));
        if(x * x == n) return 1;
        
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++) {
            int tmp = Integer.MAX_VALUE;
            for(int j = (int)(Math.sqrt(i)); j >= 1; j--) {
                tmp = Math.min(tmp, dp[i-j*j]+1);
            }
            dp[i] = tmp;
        }
        return dp[n];
    }
}
