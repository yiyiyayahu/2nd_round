/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
The algorithm should run in linear time and in O(1) space.

Hint:

How many majority elements could it possibly have?
*/

/*
这题我没想出。。。还是延续上一题的摩尔投票
如果每个都appear more than ⌊ n/3 ⌋ times算majority的话，那么最多两个
所以其实是先通过摩尔投票选出两个number，然后再验证是否符合条件
code写的时候再注意一下
*/

public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        int len = nums.length;
        if(len == 0) return list;
        
        int m = 0, n = 0, cm = 0, cn = 0;
        for(int i = 0; i < len; i++) {
            if(nums[i] == m) {
                cm ++;
            } else if(nums[i] == n) {
                cn ++;
            } else if(cm == 0) {
                m = nums[i];
                cm = 1;
            } else if(cn == 0) {
                n = nums[i];
                cn = 1;
            } else {
                cm --;
                cn--;
            }
        }
        cm = 0; cn = 0;
        for(int i = 0; i < len; i++) {
            if(nums[i] == m) cm ++;
            else if(nums[i] == n) cn ++;
        }
        if(cm > len/3) list.add(m);
        if(cn > len/3) list.add(n);
        return list;
    }
}
