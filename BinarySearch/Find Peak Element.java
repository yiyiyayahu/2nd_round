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
https://leetcode.com/discuss/68999/java-solution-and-explanation-using-invariants
这道题其实一直没太明白为什么nums[mid]<nums[mid-1]，可以证明start to mid-1 has a peak
这个post解释的很详细
如果这个不等式成立的话
nums[left]>nums[left-1] && nums[right]>nums[right+1]
那么从left到right一定有个peak
1）如果一直是递减的，left是peak
2）如果一直是递增的，right是peak
3）如果先递增再递减，那么中间一定有个peak
所以，如果按照这个思路nums[mid+1]>nums[mid] && nums[right]>nums[right+1] 满足这个条件，所以mid+1,right中间一定有个peak
nums[left]>nums[left-1] && nums[mid-1]>nums[mid] start,mid-1中间一定有个peak

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
