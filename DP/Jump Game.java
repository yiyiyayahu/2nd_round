/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
*/

/*
其实不用一维数组，用个int就行。算是greedy的解法
只要每次在跳的最远的范围内找到那个最大的就可以了，然后那个maxSteps可以大于等于len-1就是true的
i <= maxSteps && i < len这个判断条件蛮好的，code很简洁
*/
public class Solution {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        int len = nums.length;
        int maxSteps = 0;
        for(int i = 0; i <= maxSteps && i < len; i++) {
            maxSteps = Math.max(maxSteps, nums[i] + i);
            if(maxSteps >= len-1) return true;
        }
        return false;       
    }
}

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
