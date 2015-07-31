/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
*/

/*
注意stack里面的type要一致，开始stack里面放的是String，其实放Integer更好，因为只有Integer才push进stack
*/
public class Solution {
    public int evalRPN(String[] tokens) {
        if(tokens == null || tokens.length == 0) return 0; 
        Stack<Integer> stack = new Stack<Integer>();
        for(String s : tokens) {
            if(!isOperand(s)) stack.push(Integer.parseInt(s));
            else {
                int num1 = stack.pop();
                int num2 = stack.pop();
                int result = 0;
                if(s.equals("+")) result = num1 + num2;
                if(s.equals("-")) result = num2 - num1;
                if(s.equals("*")) result = num1 * num2;
                if(s.equals("/")) result = num2 / num1;
                stack.push(result);
            }
        }
        return stack.pop();
    }
    
    public boolean isOperand(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") ;
    }
}
