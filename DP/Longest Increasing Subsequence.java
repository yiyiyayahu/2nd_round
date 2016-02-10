/*
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. 
Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
*/


/*
O(nlogn)的做法想了好久。。。后来还是看的别人的解法想出来的，实际上是遍历一遍数组，然后和二分法结合做出来的。。。
是这样的，遍历数组的过程中，maintain一个arr来存这个increasing subsequence - arr，
遍历的时候，在这个新建成的数组arr中查找当前元素的位置，然后判断是应该修改哪个元素或者append一个新元素
过程中maintain这个arr的maxLen就是最后的结果
简化的例子[10, 2, 5, 3, 7]
arr - [10, 0, 0, 0, 0] maxLen = 1
    -> [2, 0, 0, 0, 0] maxLen = 1
    -> [2, 5, 0, 0, 0] maxLen = 2
    -> [2, 3, 0, 0, 0] maxLen = 2
    -> [2, 3, 7, 0, 0] maxLen = 3

但是其实还可以优化，仔细想想，这个maxLen无非就是track arr最长的长度，而又因为arr是immutable的，如果曾经最长过，这个最远的点的位置是不改变的
所以有人的做法是新建一个ArrayList，最后的结果就是这个ArrayList的size
不过其实想想，用arr来做也挺好的，不用resize length，不改了
*/
public class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
        int len = nums.length;
        int[] arr = new int[len];
        int maxLen = 0;
        arr[0] = nums[0];
        for(int i = 1; i < len; i++) {
            int pos = bst(arr, nums[i], 0, maxLen);
            if(pos > maxLen) {
                maxLen ++;
            } 
            arr[pos] = nums[i];
        }
        return maxLen+1;
    }
    
    public int bst(int[] arr, int num, int start, int end) {
        while(start <= end) {
            int mid = (start + end)/2;
            if(num < arr[mid]) {
                end = mid - 1;
            } else if(num == arr[mid]) {
            	return mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
/*
O(n^2)的解法还挺好想的，再想想O(nlogn)肿么做
*/
public class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
        int len = nums.length;
        int maxLen = 0;
        int[] dp = new int[len];
        for(int i = 0; i < nums.length; i++) {
            int n = nums[i];
            dp[i] = 1;
            for(int j = i-1; j >= 0; j--) {
                if(nums[j] < n) dp[i] = Math.max(dp[i], 1 + dp[j]);
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}
