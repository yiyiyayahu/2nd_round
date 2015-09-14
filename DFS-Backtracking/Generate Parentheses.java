/*
 Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()" 
*/

public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        getParenthesis(0, 0, n, new StringBuilder(), list);
        return list;
    }
    
    public void getParenthesis(int left, int right, int n, StringBuilder sb, List<String> list) {
        if(left == right && left == n) {
            list.add(sb.toString());
            return;
        }
    
        if(left < n) {
            StringBuilder tmp = new StringBuilder(sb);
            tmp.append("(");
            getParenthesis(left+1, right, n, tmp, list);
        }
        if(right < left) {
            StringBuilder tmp = new StringBuilder(sb);
            tmp.append(")");
            getParenthesis(left, right+1, n, tmp, list);
        }
    }
}
