/*
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.

click to show more practice.

More practice:
If you have figured out the O(n) solution, 
try coding another solution using the divide and conquer approach, which is more subtle.
*/

/*
这种DP其实是局部最优和全局最优，（诶，感觉和tree里面那个maximum path sum差不多诶，就是可以start和end at any node，也是要maintain一个全局最优和局部最优）
就是max来表示全局最优，result来表示局部最优，当result<0的时候发现加上之前的负值完全不起作用，就重置为0重新开始
这个开始max不能设为0因为有可能全部是负数
Time: O(n) Space: O(1)
*/

public class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int result = 0;
        for(int i = 0; i < nums.length; i++) {
            if(result < 0) result = 0;
            result += nums[i];
            max = Math.max(max, result);
        }
        return max;
    }
}
