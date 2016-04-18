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
意思就是[1,1,2,5,6,7], 从index=0开始的1，然后向后recursive [1,1,....]
但是这个时候，从index=1开始的1就没必要了，所以要去掉

其实可以在前面去掉也可以在后面去掉，无非就是第二个1没有必要再count了
在前面去掉就是for loop里面先判断
if(i != curr && candidates[i] == candidates[i-1]) continue;

在后面去掉就是当前的i做完之后，在for循环最后加一句
while(i < candidates.length-1 && candidates[i+1] == candidates[i]) i++;

我觉得第一种貌似更好理解，但是要注意i != curr
*/

public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        helper(candidates, 0, target, new ArrayList<Integer>(), ret);
        return ret;
    }
    
    public void helper(int[] candidates, int curr, int target, List<Integer> list, List<List<Integer>> ret) {
        if(target == 0) {
            ret.add(new ArrayList<Integer>(list));
            return;
        }
        int len = candidates.length;
        for(int i = curr; i < len; i++) {
            if(i != curr && candidates[i] == candidates[i-1]) continue;
            int newTarget = target-candidates[i];
            if(newTarget < 0) break;
            list.add(candidates[i]);
            helper(candidates, i+1, newTarget, list, ret);
            list.remove(list.size()-1);
        }
    }
}

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
