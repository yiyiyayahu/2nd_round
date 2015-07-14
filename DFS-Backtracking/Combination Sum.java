/*
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C 
where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 
*/

/*
if(i > 0 && candidates[i] == candidates[i-1]) continue;这个是为了去重~
*/

public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        dfs(candidates, 0, target, new ArrayList<Integer>(), result);
        return result;
    }
    
    public void dfs(int[] candidates, int curr, int target, List<Integer> list, List<List<Integer>> result) {
        if(target < 0) return;
        if(target == 0) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        
        for(int i = curr; i < candidates.length; i++) {
            if(i > 0 && candidates[i] == candidates[i-1]) continue;
            list.add(candidates[i]);
            dfs(candidates, i, target-candidates[i], list, result);
            list.remove(list.size()-1);
        }
    }
}
