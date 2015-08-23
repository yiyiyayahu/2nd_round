/*
Given an array of integers, find out whether there are two distinct indices i and j in the array such that the difference between nums[i] and nums[j] 
is at most t and the difference between i and j is at most k.
*/

/*
TreeSet相当于BST，然后这个set里面只保存k的元素，复杂度就是O(nlogk)
*/
public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        for(int i = 0; i < nums.length; i++) {
            int low = nums[i] < Integer.MIN_VALUE + t ? Integer.MIN_VALUE:nums[i] - t;
            int high = nums[i] > Integer.MAX_VALUE - t - 1 ? Integer.MAX_VALUE:nums[i] + t + 1;
            if(t >= 0 && treeSet.subSet(low, high).size() > 0) return true;
            treeSet.add(nums[i]);
            if(i >= k) treeSet.remove(nums[i-k]);
        }
        return false;
    }
}
