/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?
*/
/*
转成的一维的，时间还是O(mn)，空间变成了O(n)
注意外面循环的j是从1算起的，因为m是从1开始的
*/
public class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        for(int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        
        for(int j = 1; j < m; j++) {
            for(int i = 1; i < n; i++) {
                dp[i] = dp[i] + dp[i-1];
            }
        }
        return dp[n-1];
    }
}

public class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for(int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
