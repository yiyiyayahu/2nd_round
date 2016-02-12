/*
 Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut. 
*/

/*
我的想法是dp[i]代表0-i之间的minCut
dp[i] = min(dp[k-1])+1 if(k到i是palindrome)
        0(if0到k是palindrome)
果断TLE 哭
*/

public class Solution {
    public int minCut(String s) {
        if(s == null || s.length() == 0) return 0;

        int len = s.length();
        int[] dp = new int[len];
        dp[0] = 0;
        for(int i = 1; i < len; i++) {
            if(isPalindrome(s, 0, i)) {
                dp[i] = 0;
                continue;
            }
            int tmp = Integer.MAX_VALUE;
            for(int k = 1; k <= i; k++) {
                if(isPalindrome(s, k, i)) {
                    tmp = Math.min(tmp, dp[k-1]);
                }
            }
            dp[i] = tmp + 1;
        }
        return dp[len-1];       
    }
    
    public boolean isPalindrome(String s, int start, int end) {
        while(start <= end) {
            if(s.charAt(start++) != s.charAt(end--)) return false;
        }
        return true;
    }
}
