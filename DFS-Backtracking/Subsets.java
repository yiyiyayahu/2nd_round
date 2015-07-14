/*
Given a set of distinct integers, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0) return result;
        Arrays.sort(nums);
        for(int i = 0; i <= nums.length; i++) {
            helper(nums, i, 0, new ArrayList<Integer>(), result);
        }
        return result;
    }
    public void helper(int[] nums, int size, int curr, List<Integer> list, List<List<Integer>> result) {
        if(list.size() == size) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i = curr; i < nums.length; i++) {
            list.add(nums[i]);
            helper(nums, size, i+1, list, result);
            list.remove(list.size()-1);
        }
    }
}
