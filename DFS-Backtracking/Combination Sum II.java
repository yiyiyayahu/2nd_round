/*
Given a collection of candidate numbers (C) and a target number (T), 
find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 
*/

/*
while(i < candidates.length-1 && candidates[i+1] == candidates[i]) i++; 这个是为了去重！！！木有想粗来
*/

public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
            list.add(candidates[i]);
            dfs(candidates, i+1, target-candidates[i], list, result);
            list.remove(list.size()-1);
            while(i < candidates.length-1 && candidates[i+1] == candidates[i]) i++;
        }
    }
}
