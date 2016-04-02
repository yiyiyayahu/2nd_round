/*
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. 
You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. 
Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
*/

/*
这个解法TLE了。。。看来要用dp
*/
public class Solution {
    public int maxCoins(int[] nums) {
        int[] max = new int[1];
        Integer curr = new Integer(0);
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
      
        dfs(list, max, curr);
        return max[0];
    }
    public void dfs(LinkedList<Integer> list, int[] max, Integer curr) {
        if(list.size() == 0) {
        	max[0] = Math.max(max[0], curr);
            return;
        }
        for(int i = 0; i < list.size(); i++) {
            LinkedList<Integer> tmp = new LinkedList<>(list);
            int m = (i==0?1:tmp.get(i-1)) * tmp.get(i) * (i==list.size()-1?1:tmp.get(i+1));
            curr += m;
            tmp.remove(i);
            dfs(tmp, max, curr);
            curr -= m;
        }
    }
}
