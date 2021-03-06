/*
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. 
(each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
*/

/*
这道题还好一次写对了，但是我总是不记得哪种算是insert，哪种算是delete
其实这样想就好了 (if word1="p" word2="s")
dp[0][0] -> "" "" 
dp[0][1] -> "" "s"
dp[1][0] - "p" ""

那dp[1][1]:
dp[i-1][j-1] replace; dp[i-1][j] insert; dp[i][j-1] delete;
*/

public class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];
        
        dp[0][0] = 0;
        for(int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for(int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else {
                    int tmp = Math.min(dp[i-1][j], dp[i][j-1]);
                    dp[i][j] = Math.min(tmp, dp[i-1][j-1]) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
