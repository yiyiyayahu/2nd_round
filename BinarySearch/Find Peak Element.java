/*
A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

click to show spoilers.

Note:
Your solution should be in logarithmic complexity.
*/

/*
开始还是没想到思路，如果是O(logn)的话肯定是binary search，但是如何做呢
其实就是如果nums[mid]<nums[mid-1]的话就在start,mid-1之中肯定有个值。反之，如果nums[mid]<nums[mid+1], mid+1,end之间肯定有个peak
*/

public class Solution {
    public int findPeakElement(int[] nums) {
        int start = 0, end = nums.length-1;
        while(start <= end) {
            int mid = (start + end)/2;
            if(mid-1>=0 && nums[mid]<nums[mid-1]) {
                end = mid-1;
            } else if(mid+1 < nums.length && nums[mid]<nums[mid+1]) {
                start = mid+1;
            } else {
                return mid;
            }
        }
        return 0;
    }
}
