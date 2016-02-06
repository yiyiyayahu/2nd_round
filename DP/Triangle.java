/*
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]

The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle. 
*/

/*
虽然是个triangle，但是其实就是个可以压缩成一维dp的题
只有边上两个点是特殊的，dp[0]=dp[0]+l.get(0). j=n-1: dp[j]=dp[j-1]+l.get(j)
其他的都是dp[j] = Math.min(dp[j], dp[j-1]) + list.get(j);
但是我这个做法就是还要最后扫描一下数组，找到minTotal

以前我是从下面向上做，最后就会归结到dp[0]那里，就少了一步扫描了
*/

public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        if(len == 0) return 0;
        
        int[] dp = new int[len];
        int minTotal = Integer.MAX_VALUE;
        
        for(int i = 0; i < len; i++) {
            List<Integer> list = triangle.get(i);
            for(int j = list.size()-1; j >= 0; j--) {
                if(j == list.size()-1) {
                    dp[j] = list.get(i)+(j>0?dp[j-1]:0);
                } else if(j == 0) {
                    dp[j] = dp[j] + list.get(j);
                } else {
                    dp[j] = Math.min(dp[j], dp[j-1]) + list.get(j);
                }
            }
        }
        for(int i = 0; i < len; i++) {
            if(dp[i] < minTotal) minTotal = dp[i];
        }
        return minTotal;       
    }
}
