/*
Given two arrays of length m and n with digits 0-9 representing two numbers. 
Create the maximum number of length k <= m + n from digits of the two. 
The relative order of the digits from the same array must be preserved. Return an array of the k digits. 
You should try to optimize your time and space complexity.

Example 1:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
return [9, 8, 6, 5, 3]

Example 2:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
return [6, 7, 6, 0, 4]

Example 3:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
return [9, 8, 9]
*/

/*
唉，google最近的新题真的是好难。。。哭了
这题我看了这个blog，解法很详细很巧妙
http://algobox.org/create-maximum-number/
这道题可以一点点想，分解成很多sub problem
1）如果只是一个array，找k个最大的怎么办  public int[] maxArray(int[] nums, int k)
那就是greedy algorithm，比如用一个stack，当遇到nums[i]比stack.peek()大的时候，stack就pop，但是要保证剩余还有k个element，不然就直接push进stack就好了
2）如果两个array，但是包含all elements，类似于merge这两个array并得到最大的结果 这个倒是比较简单，就是如果nums[i]==nums[j]的时候，比较下后面的元素
3）那如何做出最后的这道题呢，有点像dp分解的那种了，就是可能nums1取i个元素，nums2取k-i个元素这样
*/
public class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        int[] ret = new int[k];
        for(int i = Math.max(0,k-m); i<=k && i<=n; i++) {
            int[] candidate = merge(maxArray(nums1,i), maxArray(nums2,k-i), k);
            if(greater(candidate, 0, ret, 0)) ret = candidate;
        }
        return ret;
    }
    
    public int[] merge(int[] nums1, int[] nums2, int k) {
        int[] ret = new int[k];
        for(int i = 0, j = 0, r = 0; r < k; r++) {
            ret[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        }
        return ret;
    }
    public boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while(i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return (j == nums2.length) || (i<nums1.length && nums1[i]>nums2[j]);
    }
    public int[] maxArray(int[] nums, int k) {
        int[] ret = new int[k];
        for(int i = 0, j = 0; i < nums.length; i++) {
            while(nums.length-i+j>k && j>0 && nums[i]>ret[j-1]) j--;
            if(j<k) ret[j++]=nums[i];
        }
        return ret;
    }
}
