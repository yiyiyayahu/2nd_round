/*
Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Example 1:
Given nums = [1, -1, 5, -2, 3], k = 3,
return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

Example 2:
Given nums = [-2, -1, 2, 1], k = 1,
return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

Follow Up:
Can you do it in O(n) time?
*/

/*
这道题我记得去西雅图玩儿的时候小陈有说过。。。可是开始还是没想出来，只做出了O(n)的解法
后来呢，我的想法是arr[i]存i-n的total的值，这样就有点像two sum- [i,j] = arr[j] - arr[i-1]找到这样两个值相减等于k，然后再用一个maxLen track最大的就可以了
但是用arr呢查找很慢，要O(n)，所以就想到hashmap
我做的i-n的做法感觉不太好呢？
不是的，从前往后和从后往前的做法是一样的，我之所以做的不好是，我先precompute了这样一个hashmap出来，但是有些时候key相同的话会被覆盖，不管选择哪种覆盖都不好
所以一边走一边做就可以了，不用precompute

从前面向后找，每次放入map的key是[0,i]的total，value是index i
如果找到k呢，就maxLen设为i+1
不然呢，[j,i] = [0,i] - [0,j-1] -> k = sum - [0,j-1] -> [0,j-1] = sum - k，如果map.containsKey(sum-k) 那么就找到这样一个index j，然后maxLen和i-j比较
然后就是对于重复的处理，存小一点的那个i，因为我们是从前往后扫的，所以如果map已经有sum这个entry了，就不更新了
if(!map.containsKey(sum)) map.put(sum, i);
*/
public class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int len = nums.length;
        if(len == 0) return 0;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0, maxLen = 0;
        for(int i = 0; i < len; i++) {
            sum += nums[i];
            if(sum == k) {
                if(i+1 > maxLen) maxLen = i+1;
            } else if(map.containsKey(sum-k)) {
                int j = map.get(sum-k);
                if(i-j > maxLen) maxLen = i-j;
            }
            if(!map.containsKey(sum)) map.put(sum, i);
        }
        return maxLen;
    }
}

public class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if(nums.length == 0) return 0;

        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, maxNum = 0;
        for(int i = len-1; i>=0; i--) {
            sum += nums[i];
            if(sum == k) maxNum = Math.max(maxNum, len-i);
            if(map.containsKey(sum-k)) {
                int j = map.get(sum-k);
                if(j-i > maxNum) maxNum = j-i;
            }
            if(!map.containsKey(sum)) map.put(sum, i);
        }
        
        return maxNum;        
    }
}
