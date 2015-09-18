/*
 Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity. 
*/

public class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int maxLen = 1;
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        for(int i = 0; i < nums.length; i++) {
            
            if(!set.contains(nums[i])) continue;

            int start = nums[i]-1, end = nums[i]+1, len = 1;
            while(set.contains(start)) {
                len ++;
                set.remove(start);
                start --;
            }
            while(set.contains(end)) {
                len ++;
                set.remove(end);
                end ++;
            }
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;        
    }
}
