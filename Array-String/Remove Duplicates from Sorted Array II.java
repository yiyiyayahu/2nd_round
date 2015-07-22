/*
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. 
It doesn't matter what you leave beyond the new length.
*/

/*
很巧妙的做法，可以扩展成allow N duplicates
time: O(n) space: O(1)
*/
public class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
        int len = nums.length;
        if(len <= 2) return len;
        int index = 2, tmp = nums[0];
        for(int i = 2; i < len; i++) {
            if(nums[i] != nums[index-2]) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}


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
