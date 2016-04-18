/*
Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        ret.add(list);
        helper(nums, 0, list, ret);
        return ret;
    }
    
    public void helper(int[] nums, int curr, List<Integer> list, List<List<Integer>> ret) {
        if(curr == nums.length) return;
        for(int i = curr; i < nums.length; i++) {
            if(i != curr && nums[i] == nums[i-1]) continue;
            list.add(nums[i]);
            ret.add(new ArrayList<Integer>(list));
            helper(nums, i+1, list, ret);
            list.remove(list.size()-1);
        }
    }
}
/*
这个去重其实就是比如1，2，2 弄完第一个2之后我第二个2就可以不用再考虑了
比如1 -> [1], [1,2] 这个时候就不用remove 2再加第二个2了，直接到[1,2,2]
    2 -> [2], [2,2] 到这里所有情况也遍历完了，所以就不用再考虑第二个2了
*/
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0) return result;
        Arrays.sort(nums);
        result.add(new ArrayList<Integer>());
        helper(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }
    public void helper(int[] nums, int curr, List<Integer> list, List<List<Integer>> result) {
        for(int i = curr; i < nums.length; i++) {
            list.add(nums[i]);
            result.add(new ArrayList<Integer>(list));
            helper(nums, i+1, list, result);
            while(i < nums.length-1 && nums[i] == nums[i+1]) i++;
            list.remove(list.size()-1);
        }
    }
}
