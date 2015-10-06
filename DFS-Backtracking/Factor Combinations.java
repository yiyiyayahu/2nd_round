/*
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.

Write a function that takes an integer n and return all possible combinations of its factors.

Note:

    Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
    You may assume that n is always positive.
    Factors should be greater than 1 and less than n.

Examples:
input: 1
output:

[]

input: 37
output:

[]

input: 12
output:

[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]

input: 32
output:

[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]

*/

public class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();

        helper(n, new ArrayList<Integer>(), ret, 2);
        return ret;
    }

    public void helper(int n, List<Integer> list, List<List<Integer>> ret, int start) {
        if(n == 1) {
        	if(list.size() > 1) {
        		ret.add(new ArrayList<Integer>(list));
        	}
        	return;
        }
        for(int i = start; i <= n; i++) {
            if(n%i == 0) {
                list.add(i);
                helper(n/i, list, ret, i);
                list.remove(list.size()-1);
            }
        }
    }    
}

/*
开始的code长下面这样，总是不行，就是总是不晓得怎么把[2,6][6,2]这种duplicates去掉
*/

    public void helper(int n, List<Integer> list, List<List<Integer>> ret, int orig) {
        if(n < orig) {
        	list.add(n);
            ret.add(new ArrayList<Integer>(list));
            list.remove(list.size()-1);
        }
        for(int i = 2; i < n; i++) {
            if(n%i == 0) {
                list.add(i);
                helper(n/i, list, ret, orig);
                list.remove(list.size()-1);
            }
        }
    }
