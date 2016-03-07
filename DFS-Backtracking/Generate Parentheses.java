/*
 Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()" 
*/

/*
用一个char[] arr要高效很多。。。这样就不用每次新建StringBuilder了
*/
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        char[] str = new char[n*2];
        helper(n, n, 0, str, list);
        return list;
    }
    
    public void helper(int left, int right, int index, char[] str, List<String> list) {
        if(left < 0 || left > right) return;
        if(left == 0 && right == 0) {
            String s = String.copyValueOf(str);
            list.add(s);
            return;
        }
        
        if(left > 0) {
            str[index] = '(';
            helper(left-1, right, index+1, str, list);
        }
        if(right > left) {
            str[index] = ')';
            helper(left, right-1, index+1, str, list);
        }
    }
}

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
