/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]
*/

/*
The counter will increase when it is ‘(‘ and decrease when it is ‘)’. 
Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.
Thus, we need to remove ')' in the prefix
To prevent duplicates, 
    1. we keep track of the last removal position of ')' and always remove ')' after that position
    2. we restrict ourself to remove the first ) in a series of concecutive )s.
    (EX: s = ()), we can remove s[1] or s[2] but the result is the same (). )
*/
public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ret = new ArrayList<>();
        remove(s, ret, 0, 0, new char[]{'(', ')'});
        return ret;
    }

    public void remove(String s, List<String> ret, int index, int lastRemoval, char[] par) {
        int count = 0, i = index;
        for(; i < s.length(); i++) {
            if(s.charAt(i) == par[0]) count++;
            if(s.charAt(i) == par[1]) count--; 
            if(count < 0) break;
        }
        if(count < 0) {
            for(int j = lastRemoval; j <= i; j++) {
                if(s.charAt(j) == par[1] && (j==lastRemoval || s.charAt(j-1) != par[1])) {
                    remove(s.substring(0,j) + s.substring(j+1, s.length()), ret, i, j, par);
                }
            }
        } else {
            String reversed = new StringBuilder(s).reverse().toString();
            if (par[0] == '(') // finished left to right
                remove(reversed, ret, 0, 0, new char[]{')', '('});
            else // finished right to left
                ret.add(reversed);
        }
    }
}
