/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, 
compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
*/

/*
从左往右遍历一遍，左边最大值
从右往左遍历一遍，右边最大值
然后再从前往后遍历一遍，i这个点上可以蓄水的大小就是Math.min(leftmost[i], rightmost[i])-height[i]加和就好了
time: O(n) space: O(n)
*/
public class Solution {
    public int trap(int[] height) {
        if(height == null || height.length == 0) return 0;
        
        int len = height.length;
        int[] left = new int[len];
        int[] right = new int[len];
        int result = 0;
        for(int i = 0; i < len; i++) {
            left[i] = (i==0) ? height[i] : Math.max(left[i-1], height[i]);
        }
        for(int i = len-1; i >= 0; i--) {
            right[i] = (i==len-1)? height[i] : Math.max(right[i+1], height[i]);
        }
        for(int i = 0; i < len; i++) {
            result += Math.min(left[i], right[i]) - height[i];
        }
        return result;
    }
}
