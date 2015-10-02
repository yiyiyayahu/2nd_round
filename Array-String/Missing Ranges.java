/*
Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
*/
public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> lists = new ArrayList<String>();
        if(nums == null) return lists;

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == lower) {
            	lower = nums[i]+1;
            	continue;
            } else {
	            int small = lower, large = nums[i]-1;
	            String s = "";
	            if(small == large) s = Integer.toString(small);
	            else s = small+"->"+large;
	            
	            lists.add(s);
	            lower = nums[i]+1;
            }
        }
        if(lower <= upper) {
            if(lower == upper) lists.add(Integer.toString(lower));
            else lists.add(lower+"->"+upper);
        }
        return lists;
    }
}
