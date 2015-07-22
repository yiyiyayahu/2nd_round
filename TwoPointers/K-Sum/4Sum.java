/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
Find all unique quadruplets in the array which gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
*/

/*
Time: O(n^3)
*/
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0) return result;
        Arrays.sort(nums);
        int len = nums.length;
        for(int i = 0; i < len-3; i++) {
            if(i > 0 && nums[i] == nums[i-1]) continue;
            for(int j = i+1; j < len-2; j++) {
                if(j > i+1 && nums[j] == nums[j-1]) continue;
                
                int t = target - nums[i] - nums[j];;
                int start = j+1, end = len-1;
                
                while(start < end) {
                    int sum = nums[start] + nums[end];
                    if(sum < t) start ++;
                    else if(sum > t) end --;
                    else {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(nums[i]); list.add(nums[j]); list.add(nums[start]); list.add(nums[end]);
                        result.add(list);
                        start ++;
                        end --;
                        while(start < end && nums[start] == nums[start-1]) start ++;
                        while(start < end && nums[end] == nums[end+1]) end --;
                    }
                }
            }
        }
        return result;
    }
}
