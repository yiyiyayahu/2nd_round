/*
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
*/

/*
这题明明不难的，可是自己总是写错
开始会想成two pointer，但是如果s1.charAt(i) == s2.charAt(j) == s3.charAt(i+j+1)，就不知道是应该i++还是j++
后来想到dp，很显然二维dp，dp[i][j]表示和当前s1.substring(0,i+1), s2.substring(0,j+1)的match是true还是false
犯了两个错误：
1. index开始写错了，i,j对应的是i+j+1而不是i+j，后来改成了i-1,j-1,i+j-1为了适应dp的index
2. base case写的不对，开始以为只要考虑第一个字符就可以了，也就是dp[0][1]和dp[1][0]，但其实不是的
比如"aa","ab","abaa"，开始dp[0][j]表示s1这边没有match的，看s2这边会不会match，会match多少，显然dp[0][1]和dp[0][2]都应该是true啊
*/

public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len = s3.length();
        if(len1 == 0) return s3.equals(s2);
        if(len2 == 0) return s3.equals(s1);
        if(len != len1 + len2) return false;
        
        boolean[][] dp = new boolean[len1+1][len2+1];
        dp[0][0] = true;
        for(int i = 1; i <= len1; i++) {
        	if(s3.charAt(i-1) == s1.charAt(i-1)) dp[i][0] = dp[i-1][0];
        }
        for(int j = 1; j <= len2; j++) {
        	if(s3.charAt(j-1) == s2.charAt(j-1)) dp[0][j] = dp[0][j-1];
        }

        for(int i = 1; i <= len1; i++) {
            for(int j = 1; j <= len2; j++) {
                char curr = s3.charAt(i+j-1);
                if(curr == s1.charAt(i-1)) dp[i][j] |= dp[i-1][j];
                if(curr == s2.charAt(j-1)) dp[i][j] |= dp[i][j-1];
            }
        }
        
        return dp[len1][len2];
    }
}
