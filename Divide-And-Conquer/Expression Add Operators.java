/*
Given a string that contains only digits 0-9 and a target value, 
return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []
*/

/*
这道题虽然不是完全没思路，但是还是不太好写的。。。唉，最后这几天各种看答案，我对自己也是醉了
要注意的几点：
1. 如果当前位是'0'，那算完当前的就不要继续往下了，因为和后面的数字没办法组成一个valid number
2. 用Long，如果超出int 范围的话，毕竟这个num是个String
3. 对于乘法要注意，这也是为什么要把上一步操作的那个值存下来
"232", 8 得出2+3*2的时候，我们是从前往后的，开始会2+3=5，但是到2的时候，我们希望3*2
所以我们把之前的3存下来，eval减去多加的那个3，加上prev*curr=6，然后把6存下来
*/
public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> ret = new ArrayList<String>();
        if(num == null || num.length() == 0) return ret;
        dfs(num, target, 0, 0, 0, "", ret);
        return ret;
    }
    
    public void dfs(String num, int target, int index, long eval, long prev, String expression, List<String> ret) {
        if(index == num.length()) {
            if(eval == target) ret.add(expression);
            return;
        }
        for(int i = index; i < num.length(); i++) {
            if(num.charAt(index) == '0' && i > index) break;
            long curr = Long.parseLong(num.substring(index, i+1));
            if(index == 0) {
                dfs(num, target, i+1, curr, curr, expression+curr, ret);
            } else {
                dfs(num, target, i+1, eval+curr, curr, expression+"+"+curr, ret);
                dfs(num, target, i+1, eval-curr, -curr, expression+"-"+curr, ret);
                dfs(num, target, i+1, eval-prev+prev*curr, prev*curr, expression+"*"+curr, ret);
            }
        }
    }
}
