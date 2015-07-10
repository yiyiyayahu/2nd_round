/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
*/

/*
不用helper函数的 O(logn) O(1)
*/
public class Solution {
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length - 1;
        
        while(start < end && nums[start] > nums[end]) {
            if(start == end - 1) return nums[end];
            int mid = (start + end)/2;
            if(nums[mid] < nums[start]) {
                end = mid;
            } else if(nums[mid] > nums[end]) {
                start = mid;
            }
        }
        return nums[start];
    }
}


public class Solution {
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        return helper(nums, 0, nums.length-1);
    }
    public int helper(int[] nums, int start, int end) {
        if(start == end) return nums[start];
        if(start == end - 1) return Math.min(nums[start], nums[end]);
        int mid = (start + end)/2;
        if(nums[mid] < nums[start]) {
            return helper(nums, start, mid);
        } else if(nums[mid] > nums[end]) {
            return helper(nums, mid, end);
        } 
        return nums[start];
    }
}
