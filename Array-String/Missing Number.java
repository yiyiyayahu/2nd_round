/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
*/

/*
直接算sum就好了嘛。。。
*/
public class Solution {
    public int missingNumber(int[] nums) {
        if(nums == null || nums.length == 0) return 1;
        
        int n = nums.length;
        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += nums[i];
        }
        return n * (n+1)/2 - sum;
    }
}

/*
和First Missing Positive差不多
*/

public class Solution {
    public int missingNumber(int[] nums) {
        if(nums == null || nums.length == 0) return 1;
        
        for(int i = 0; i < nums.length; ) {
            int index = nums[i];
            if(nums[i] == nums.length || nums[i] == i) i++;
            else swap(nums, i, index);
        }
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != i) return i;
        }
        return nums.length;
    }
    
    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }    
}
