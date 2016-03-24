/*
Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
*/

/*
依旧是leetcode那本书上的简洁写法
就是first和last element比较不好处理，所以就加了一个lower-1，upper+1的这么一个fake range，if else就会少很多
*/
public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ranges = new ArrayList<>();
        int prev = lower-1;
        for(int i = 0; i <= nums.length; i++) {
            int curr = (i==nums.length) ? upper+1 : nums[i];
            if(curr-prev >= 2) {
                ranges.add(getRange(prev+1,curr-1));
            }
            prev = curr;
        }
        return ranges;
    }
    private String getRange(int from, int to) {
        return from==to? String.valueOf(from) : from + "->" + to;
    }
}

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
