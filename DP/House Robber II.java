/*
Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery so that 
he will not get too much attention. This time, all houses at this place are arranged in a circle. 
That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.
*/

/*
两遍dp
0-(n-2)
1-(n-1)
最后取最大值
*/
public class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
        int len = nums.length;
        if(len == 1) return nums[0];
        int[] dp1 = new int[len-1];
        int[] dp2 = new int[len-1];
        
        dp1[0] = nums[0]; 
        dp2[0] = nums[1];
        for(int i = 1; i < len-1; i++) {
            dp1[i] = Math.max(i>=2?dp1[i-2]+nums[i]:nums[i], dp1[i-1]);
        }
        for(int i = 1; i < len-1; i++) {
            dp2[i] = Math.max(i>=2?dp2[i-2]+nums[i+1]:nums[i+1], dp2[i-1]);
        }
        return Math.max(dp1[len-2], dp2[len-2]);
    }
}
