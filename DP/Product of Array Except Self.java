/*
Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums 
except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of 
space complexity analysis.)
*/

/*
dp - 滚动数组 再熟练一下，我一般总是要把extra space的版本写出来再优化，试试能不能一遍就出来
*/
public class Solution {
    public int[] productExceptSelf(int[] nums) {
        if(nums == null || nums.length == 0) return nums;
        int n = nums.length;
        int[] output = new int[n];
        output[0] = 1; 
        for(int i = 1; i < n; i++) {
            output[i] = nums[i-1] * output[i-1];
        }
        int after = 1;
        for(int i = n-2; i>=0; i--) {
            after *= nums[i+1];
            output[i] *= after;
        }
        return output;
    }
}

public class Solution {
    public int[] productExceptSelf(int[] nums) {
        if(nums == null || nums.length == 0) return nums;
        int n = nums.length;
        int[] before = new int[n];
        int[] after = new int[n];
        
        before[0] = 1; after[n-1] = 1;
        for(int i = 1; i < n; i++) {
            before[i] = nums[i-1] * before[i-1];
        }
        for(int i = n-2; i>=0; i--) {
            after[i] = nums[i+1] * after[i+1];
        }
        for(int i = 0; i < n; i++) {
            before[i] *= after[i];
        }
        return before;
    }
}
