/*
Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
*/

public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> lists = new ArrayList<String>();
        if(nums == null || nums.length == 0) return lists;
        
        int start = nums[0], end = nums[0];
        for(int i = 1; i <= nums.length; i++) {
            if(i < nums.length && nums[i] == end + 1) end ++;
            else {
                if(start == end) lists.add(String.valueOf(start));
                else lists.add(start + "->" + end);
                if(i < nums.length) {
                    start = nums[i];
                    end = nums[i];
                }
            }
        }
        return lists;
    }
}
