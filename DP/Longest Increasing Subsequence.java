/*
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. 
Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
*/

/*
O(n^2)的解法还挺好想的，再想想O(nlogn)肿么做
*/
public class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
        int len = nums.length;
        int maxLen = 0;
        int[] dp = new int[len];
        for(int i = 0; i < nums.length; i++) {
            int n = nums[i];
            dp[i] = 1;
            for(int j = i-1; j >= 0; j--) {
                if(nums[j] < n) dp[i] = Math.max(dp[i], 1 + dp[j]);
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}
