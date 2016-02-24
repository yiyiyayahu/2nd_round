/*
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
*/

/*
好开心，这道题前几天还不会呢，后来看个视频讲解topological sort还挺有用的
这题看到之后第一反应是dfs，然后肯定要dp存一下中间量。第一次看到尝试写，失败，没思路
这次思路好清晰，瞬间写好。啦啦啦
我回忆了一下topological sort，就是从一个点开始，dfs搜索，走到不能再走的时候，设为current_level，return，然后上面的一次设自己的level，每次减一
那这道题也是一样的啊，每个元素只能往比它小的元素去走，走到不能再走的时候就把这个路径上的所有点依次设个值，dp[i][j]中存所有路径最大的
longest就是dp这个array中最大的！
*/
public class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        
        int longest = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                helper(matrix, i, j, dp);
                longest = Math.max(longest, dp[i][j]);
            } 
        }
        return longest;
    }
    
    public int helper(int[][] matrix, int i, int j, int[][] dp) {
        if(dp[i][j] != 0) return dp[i][j];
        
        int longest = 0;
        if(i-1>=0 && matrix[i][j] > matrix[i-1][j]) 
            longest = Math.max(longest, helper(matrix, i-1, j, dp));
        if(i+1<matrix.length && matrix[i][j] > matrix[i+1][j]) 
            longest = Math.max(longest, helper(matrix, i+1, j, dp));
        if(j-1>=0 && matrix[i][j] > matrix[i][j-1]) 
            longest = Math.max(longest,helper(matrix, i, j-1, dp));
        if(j+1<matrix[0].length && matrix[i][j] > matrix[i][j+1]) 
            longest = Math.max(longest,helper(matrix, i, j+1, dp));
        
        dp[i][j] = longest + 1;
        return dp[i][j];
    }
}
