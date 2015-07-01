/*
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
*/

/*
要注意的是：每次dp[0]也要更新一下，I那里不用更新是因为每次到下一层的时候dp[0]是不变的。。。
然后就是注意code，i,j总是弄混。。。
*/

public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        int[] dp = new int[n];
        for(int i = 0; i < n; i++) {
            if(obstacleGrid[0][i] == 1) { dp[i] = 0; break; }
            else dp[i] = 1;
        }
        
        for(int i = 1; i < m; i++) {
            dp[0] = obstacleGrid[i][0]==1 ? 0 : dp[0];
            for(int j = 1; j < n; j++) {
                if(obstacleGrid[i][j] == 1) dp[j] = 0;
                else dp[j] += dp[j-1];
            }
        }     
        return dp[n-1];
    }
}
