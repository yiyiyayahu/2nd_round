/*
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, 
then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
*/

/*
这道题不是很好写。start表示从前面开始第一个不是0的位置，end表示从后面开始第一个不是1的位置。然后再用一个指针扫中间的
犯了两个错误：
1. i是从start到end，而不是从start+1到end-1
2. 不是所有情况都i++的，在把nums[2]换到末尾的时候，i要保持住，有可能会再换的
*/
public class Solution {
    public void sortColors(int[] nums) {
        if(nums == null || nums.length < 2) return;
        int len = nums.length;
        int start = 0, end = len-1;
        while(start < len && nums[start] == 0) start ++;
        while(end >= 0 && nums[end] == 2) end --;
        
        for(int i = start; i <= end; ) {
            if(nums[i] == 0) {
                swap(nums, i, start);
                start ++;
                i++;
            } else if(nums[i] == 2) {
                swap(nums, i, end);
                end --;
            } else {
                i++;
            }
        }
    }
    public void swap(int[] nums, int start, int end) {
        int tmp = nums[start];
        nums[start] = nums[end];
        nums[end] = tmp;
    }
}
