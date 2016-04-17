/*
Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
*/

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
        int len = nums.length;
        int minLen = len+1;
        int start = 0, end = start, sum = 0;
        while(end < len) {
            sum += nums[end];
            if(sum >= s) {
                //check to see if start can move further
                while(sum - nums[start] >= s) {
                    sum -= nums[start++];
                }
                minLen = Math.min(minLen, end-start+1);
                sum -= nums[start++];
            } 
            end ++;
        }
        return minLen==len+1? 0 : minLen;
    }
}
/*
code写的简洁一点
*/
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        int minLen = len+1;
        int sum = 0;
        
        int start = 0, end = start;
        while(start < len) {
            while(sum < s && end < len) {
                sum += nums[end++];
            }
            int tmp = (sum>=s) ? end-start:len+1;
            minLen = Math.min(minLen, tmp);
            sum -= nums[start++];
        }
        if(minLen == len+1) return 0;
        return minLen;
    }
}
/*
这道题还蛮简单的，就是two pointer一点点移，时间复杂度O(n)
我这里又没仔细检查array的bound
sum += nums[++end]; end指针后移的时候没有注意看end是不是最后一位，前面要加一句：if(end == len-1) break;
*/
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int minLen = 0;
        int sum = 0;
        
        int start = 0, end = start;
        int len = nums.length;
        while(end < len) {
            sum += nums[end];
            if(sum >= s) break;
            end ++;
        }
        
        if(sum < s) return 0;
        if(minLen == 0 || end - start + 1 < minLen) minLen = end-start+1;
        
        while(start < len && end < len) {
            sum -= nums[start++];
            if(sum >= s) {
                minLen = Math.min(minLen, end-start+1);
                continue;
            }
            if(end == len-1) break;
            sum += nums[++end];
            if(sum >= s) minLen = Math.min(minLen, end-start+1);
        }
        return minLen;
    }
}
