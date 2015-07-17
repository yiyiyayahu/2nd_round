/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/

/*
又犯了类似的错误。。。result.add(new ArrayList<Integer>(list));不能直接result.add(list);不然结果会被改掉！
时间复杂度比较恐怖 O(n!) 空间复杂度O(n)
*/

public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(k > n || k == 0) return result;
        dfs(n, k, 1, new ArrayList<Integer>(), result);
        return result;
    }
    
    public void dfs(int n, int k, int curr, List<Integer> list, List<List<Integer>> result) {
        if(list.size() == k) {
            result.add(new ArrayList<Integer>(list)); 
            return;
        }
        for(int i = curr; i <= n; i++) {
            list.add(i);
            dfs(n, k, i+1, list, result);
            list.remove(list.size()-1);
        }
    }
}
