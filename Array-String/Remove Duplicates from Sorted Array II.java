/*
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. 
It doesn't matter what you leave beyond the new length.
*/
public class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
        int len = nums.length;
        int j = 1, tmp = nums[0];
        for(int i = 1; i < len; i++) {
            if(nums[i] != tmp || i==len-1 ||nums[i] != nums[i+1]) {
                nums[j++] = nums[i];
                tmp = nums[i];
            } 
        }
        return j;
    }
}
