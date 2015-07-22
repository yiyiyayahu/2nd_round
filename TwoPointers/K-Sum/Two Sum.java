/*
Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, 
where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/

/*
我开始的时候傻了，明明放到map里面，知道一个数和target找另外一个数而已，何必再用一次binary search呢，直接找就好了
而且一遍循环就可以了，一边放一边找
time: O(n) space: O(n)
*/
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if(map.containsKey(target-n)) {
                return new int[]{map.get(target-n), i+1};
            }
            map.put(n, i+1);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
