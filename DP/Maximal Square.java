/*
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.
*/

/*
唉，这道题写了好久。。。开始的时候想错了。觉得是这样的，比如当前是1，那么看上面，斜上面，左边如果都是1的话，那么边长是dp[i-1]+1
但是实际上不是这样的对吧
1111
1111
1111
应该返回9而不是16不是么
所以其实应该是Math.min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])+1
用一个int[m+1][n+1]来避免边界条件的冗余code
*/

public class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m+1][n+1];
        
        int maxSquare = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '1') {
                    dp[i+1][j+1] = Math.min(dp[i][j], Math.min(dp[i+1][j], dp[i][j+1])) + 1;
                    maxSquare = Math.max(maxSquare, dp[i+1][j+1]);
                }
            }
        }
        return maxSquare * maxSquare;
    }
}

/*
改进为一维的
其实需要的就是上一轮的dp[j-1]的值，这一轮的dp[j-1]和当前的dp[j]
然后因为dp[j-1]被更改过，要cache一下之前的dp[j-1]
因为是一维的，如果当前元素为0，记得把dp[j]置为0
同样为了code简洁，数组长度初始化为n+1
*/
public class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        
        int m = matrix.length, n = matrix[0].length;
        int[] dp = new int[n+1];
        
        int maxSquare = 0;
        for(int i = 0; i < m; i++) {
            int pre = 0;
            for(int j = 0; j < n; j++) {
                int tmp = dp[j+1];
                if(matrix[i][j] == '1') {
                    dp[j+1] = Math.min(pre, Math.min(dp[j], dp[j+1])) + 1;
                    maxSquare = Math.max(maxSquare, dp[j+1]);
                } else {
                	dp[j+1] = 0;
                }
                pre = tmp;
            }
        }
        return maxSquare * maxSquare;
    }
}
