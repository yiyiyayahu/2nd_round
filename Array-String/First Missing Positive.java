/*
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
*/

/*
第二遍还是没想出来思路。。。。用数组的index这个信息，很巧妙
还有一点没想到的就是[1,1]这种情况，会死循环的，所以直接跳过不swap就好了
*/
public class Solution {
    public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0) return 1;
        
        for(int i = 0; i < nums.length; ) {
            int index = nums[i]-1;
            if(nums[i] <= 0 || nums[i] >= nums.length || nums[i] == i+1 || nums[i] == nums[index]) i++;
            else swap(nums, i, index);
        }
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != i+1) return i+1;
        }
        return nums.length+1;
    }
    
    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
