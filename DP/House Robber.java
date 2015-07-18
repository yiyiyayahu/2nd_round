/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, 
the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.
*/

/*
时间O(n) 空间O(n)
*/
public class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int len = nums.length;
        
        int[] dp = new int[len];
        dp[0] = nums[0]; 
        for(int i = 1; i < len; i++) {
            dp[i] = Math.max(i>=2?dp[i-2]+nums[i]:nums[i], dp[i-1]);
        }
        return dp[len-1];
    }
}

/*
如果把dp[1]也单列出来就要注意几点：
1. 如果len==1怎么办
2. dp[1] = Math.max(nums[0],nums[1]);而不是nums[1]
*/
public class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int len = nums.length;
        if(len == 1) return nums[0];
        
        int[] dp = new int[len];
        dp[0] = nums[0]; dp[1] = Math.max(nums[0],nums[1]);
        for(int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
        }
        return dp[len-1];
    }
}
