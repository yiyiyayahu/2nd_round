/*
Find all possible combinations of k numbers that add up to a number n, 
given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Ensure that numbers within the set are sorted in ascending order.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]

*/

public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        dfs(k, n, 1, new ArrayList<Integer>(), result);
        return result;
    }
    
    public void dfs(int k, int n, int curr, List<Integer> list, List<List<Integer>> result) {
        if(k < 0) return;
        if(k == list.size() && n == 0) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i = curr; i <= 9; i++) {
            list.add(i);
            dfs(k, n-i, i+1, list, result);
            list.remove(list.size()-1);
        }
    }
}
