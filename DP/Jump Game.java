/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
*/

public class Solution {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0) return true;
                
        int len = nums.length;
        boolean[] canJump = new boolean[len];
        canJump[0] = true;
        
        for(int i = 1; i < len; i ++) {
            for(int j = i-1; j >= 0; j--) {
                int steps = nums[j];
                if(canJump[j] && steps >= i-j) {
                    canJump[i] = true;
                    break;
                }
            }
        }
        return canJump[len-1];
    }
}
