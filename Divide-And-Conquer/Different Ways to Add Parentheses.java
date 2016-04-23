/*
Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers 
and operators. The valid operators are +, - and *.

Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]
*/

/*
这道题倒是不难想，就按照运算符分割，然后recursive就可以了

这里用一个map存之前计算过的string比较巧妙
*/

public class Solution {
    Map<String, List<Integer>> map = new HashMap<>();
    
    public List<Integer> diffWaysToCompute(String input) {
        if(map.containsKey(input)) return map.get(input);
        
        List<Integer> ret = new ArrayList<>();
        
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == '+' || c == '-' || c == '*') {
                List<Integer> list1 = diffWaysToCompute(input.substring(0,i));
                List<Integer> list2 = diffWaysToCompute(input.substring(i+1));
                for(int v1 : list1) {
                    for(int v2 : list2) {
                        if(c == '+') ret.add(v1 + v2);
                        if(c == '-') ret.add(v1 - v2);
                        if(c == '*') ret.add(v1 * v2);
                    }
                }
            }
        }
        if(ret.isEmpty()) ret.add(Integer.parseInt(input));
        map.put(input, ret);
        return ret;
    }
}
