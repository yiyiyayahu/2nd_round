/*
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
*/

/*
和Maximum Subarray类似，只是因为负负得正的原因，要多maintain一个localMin的变量来保存当前最小的
开始没想清楚，写了几遍都不过，还是要再练一下。。。
没有Maximum Subarray那么好更新变量，因为要比较三个值: nums[i], nums[i]*localMax和nums[i]*localMin
*/

public class Solution {
    public int maxProduct(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int max = nums[0];
        int localMin = nums[0], localMax = nums[0];
        
        for(int i = 1; i < nums.length; i++) {
            int tmp = localMax;
            localMax = Math.max(Math.max(nums[i], nums[i]*localMax), nums[i]*localMin);
            localMin = Math.min(Math.min(nums[i], nums[i]*tmp), nums[i]*localMin);
            max = Math.max(localMax, max);
        }
        return max;
    }
}
