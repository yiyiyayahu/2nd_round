/*
Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
*/

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0) return result;
        
        helper(nums, new boolean[nums.length], new ArrayList<Integer>(), result);
        return result;
    }
    
    public void helper(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> result) {
        if(list.size() == nums.length) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(used[i]) continue;
            else {
                list.add(nums[i]);
                used[i] = true;
                helper(nums, used, list, result);
                list.remove(list.size()-1);
                used[i] = false;
            }
        }
    }
}
