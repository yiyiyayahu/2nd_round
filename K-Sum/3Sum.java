/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
*/

/*
time: O(n^2) 注意对于重复数字的处理
*/
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0) return result;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-2; i++) {
            if(i>0 && nums[i] == nums[i-1]) continue;
            
            int target = 0-nums[i];
            int start = i+1, end = nums.length-1;
            while(start < end) {
                if(start > i+1 && nums[start] == nums[start-1]) {
                    start ++;continue;
                }
                if(end < nums.length-1 && nums[end] == nums[end+1]) {
                    end --; continue;
                }
                int sum = nums[start] + nums[end];
                if(sum < target) {
                    start ++;
                } else if(sum > target) {
                    end --;
                } else {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[i]); list.add(nums[start]); list.add(nums[end]);
                    result.add(list);
                    start ++;
                    end --;
                }
            }
        }
        return result;
    }
}
