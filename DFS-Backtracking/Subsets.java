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

/*
很巧妙诶，只要每次都直接add到result里面就可以啦，不用像之前那样考虑size的问题

时间复杂度O(2^n) 因为每个元素都有放入和不放入两种情况
空间复杂度O(n)
*/
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
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
            list.remove(list.size()-1);
        }
    }
}

/*
这个是cc150上面的做法，因为每个元素可以放入-1和不放入-0，所以可以用一个bit vector来表示 0-2^n-1
比如[1,2,3] 000-001-----111这么多种, 000表示空，001表示[1]
然后判断每一位是不是1很巧妙，用了bit manipulation，(i&1) == 1表示这一位是1，101 & 001 = 1，然后往后挪一位接着比101->10 ...
*/
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        int max = 1 << nums.length;
        for(int k = 0; k < max; k++) {
            List<Integer> list = convertIntToSet(k, nums);
            ret.add(list);
        }
        return ret;
    }
    
    public List<Integer> convertIntToSet(int k, int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        int index = 0;
        for(int i = k; i > 0; i >>= 1) {
            if((i&1) == 1) {
                list.add(nums[index]);
            }
            index ++;
        }
        return list;
    }
}

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
