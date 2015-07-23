/*
Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
*/

public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        int i = 0, j = nums.length-1;
        while(i <= j) {
            int mid = (i+j)/2;
            if(target == nums[mid]) {
                int m = mid, n = mid;
                while(m >= 0 && nums[m] == target) m--;
                while(n < nums.length && nums[n] == target) n++;
                result[0] = m+1; result[1] = n-1;
                return result;
            } else if(target > nums[mid]) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        result[0] = -1; result[1] = -1;
        return result;
    }
}
