/*
Follow up for N-Queens problem.
(no two queens share the same row, column, or diagonal.)

Now, instead outputting board configurations, return the total number of distinct solutions.
*/

/*
这道题现在做起来好多了，没有开始那么无措
开始没过是因为以为只考虑前后1位就行了，但是不能同一个对角线的话要把其他的非0点都考虑进去
*/
public class Solution {
    int count = 0;
    public int totalNQueens(int n) {
        int[] nums = new int[n];
        helper(1, nums, n);
        return count;
    }
    public void helper(int curr, int[] nums, int n) {
        if(curr == n+1) {
            count ++;            
            return;
        }
        for(int i = 0; i < nums.length; i++) {
          boolean isValid = true;
            if(nums[i] != 0) continue;
            
          for(int j = 0; j < nums.length; j++) {
            if(nums[j] == 0) continue;
            if(Math.abs(i-j) == Math.abs(curr - nums[j])) isValid = false;
          }
            if(isValid) {
                nums[i] = curr;
                helper(curr+1, nums, n);
                nums[i] = 0;
            }
        }
    }
}
