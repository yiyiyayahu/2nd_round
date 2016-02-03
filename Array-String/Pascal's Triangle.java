/*
Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/

/*
这里要注意不能直接l.get(i)=l.get(i)+l.get(i-1);然后arraylist是set(index,value)不是insert
*/
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        helper(list, new ArrayList<Integer>(), 0, numRows);
        return list;
    }
    
    public void helper(List<List<Integer>> list, List<Integer> currList, int n, int numRows) {
        if(n == numRows) return;
        List<Integer> l = new ArrayList<Integer>(currList);
        l.add(1);
        for(int i = n-1; i > 0; i--) {
            int x=l.get(i)+l.get(i-1);
            l.set(i, x);
        }
        list.add(l);
        helper(list, l, n+1, numRows);
    }
}
