/*
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/
public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if(nums == null || nums.length == 0) return 0;
        
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length-2; i++) {
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int t = target - nums[i];
            int start = i+1, end = nums.length-1;
            while(start < end) {
                int sum = nums[start] + nums[end];
                if(Math.abs(t-sum) < Math.abs(diff)) diff = t-sum;
                if(sum < t) start ++;
                else if(sum > t) end --;
                else return target;
            }
        }
        return target-diff;
    }
}
