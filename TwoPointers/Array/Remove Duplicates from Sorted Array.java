/*
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. 
It doesn't matter what you leave beyond the new length.
*/

public class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
        int len = nums.length;
        int index = 1;
        for(int i = 1; i < len; i++) {
            if(nums[i] != nums[index-1]) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}

public class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        
        int tmp = nums[0];
        int j = 1;
        int len = nums.length;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] != tmp) {
                nums[j++] = nums[i];
                tmp = nums[i];
            } else {
                len --;
            }
        }
        return len;
    }
}
