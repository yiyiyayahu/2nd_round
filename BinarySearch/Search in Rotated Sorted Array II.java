/*
Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Write a function to determine if a given target is in the array.
*/

/*
有重复就是可能nums[start] == nums[mid]，找不到pivot，那就只能start一位一位移
time: O(n) space: O(1)
*/
public class Solution {
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return false;
        int start = 0, end = nums.length-1;
        while(start <= end) {
            int mid = (start + end)/2;
            if(nums[mid] == target) return true;
            
            if(nums[start] < nums[mid]) {
            	if(nums[start] <= target && nums[mid] > target) {
                    end = mid-1;
                } else {
                    start = mid + 1;
                }
            } else if(nums[start] > nums[mid]) {
                if(nums[end] >= target && nums[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                start ++;
            }
        }
        return false;
    }
}
