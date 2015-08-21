/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' 
both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
*/

public class Solution {
    List<List<String>> lists = new ArrayList<List<String>>();

    public List<List<String>> solveNQueens(int n) {
        int[] nums = new int[n];
        helper(1, nums, n);
        return lists;
    }
    
    public void helper(int curr, int[] nums, int n) {
        if(curr == n+1) {
            lists.add(getNQueens(nums, n));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
        	boolean isValid = true;
            if(nums[i] != 0) continue;
            
        	for(int j = 0; j < nums.length; j++) {
        		if(nums[j] == 0) continue;
        		int indexDiff = Math.abs(i-j);
        		int diff = Math.abs(curr - nums[j]);
        		if(indexDiff == diff) isValid = false;
        	}
            if(isValid) {
                nums[i] = curr;
                helper(curr+1, nums, n);
                nums[i] = 0;
            }
        }
    }
    
    public List<String> getNQueens(int[] nums, int n) {
        List<String> list = new ArrayList<String>();
        
        for(int i = 0; i < nums.length; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 1; j < nums[i]; j++) sb.append(".");
            sb.append("Q");
            for(int j = nums[i]+1; j <= n; j++) sb.append(".");
            list.add(sb.toString());
        }
        return list;
    }
}
