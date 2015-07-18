/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].
*/
/*
开始忘记sort了。。。
和之前subsetII对于重复的处理是一样的，都是前一个弄完了，后面的就可以直接跳过
*/
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0) return result;
        Arrays.sort(nums);
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
                while(i < nums.length-1 && nums[i] == nums[i+1]) i++; 
            }
        }
    }
}
