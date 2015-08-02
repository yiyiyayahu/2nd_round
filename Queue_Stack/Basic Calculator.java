/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), 
the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
Note: Do not use the eval built-in library function.
*/


/*
如果按照跟2差不多的方法，把infix转换成postfix的话，唯一的区别就是括号
1. 遇到(，push到stack里面 (起到一个marker的作用
2. 遇到)，一直pop直到遇到(
*/
public class Solution {
    public int calculate(String s) {
        if(s == null || s.length() == 0) return 0;
        
		List<Object> list = infixToPostfix(s);
		Stack<Integer> stack = new Stack<Integer>();
		for(Object o : list) {
			if(o instanceof Integer) stack.push((Integer) o); 
			else {
				int a = stack.pop();
				int b = stack.pop();
				if(o.equals('+')) stack.push(a+b);
				if(o.equals('-')) stack.push(b-a);
				if(o.equals('*')) stack.push(a*b);
				if(o.equals('/')) stack.push(b/a);
			}
		}
		return stack.pop();
    }
    
    public List<Object> infixToPostfix(String s) {
        s = s.replaceAll(" ", "");
        List<Object> list = new ArrayList<Object>();
        Stack<Character> stack = new Stack<Character>();
        
        int num = 0;
        boolean prevIsNum = false;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if(c >= '0' && c <= '9') {
                num = num*10 + c-'0';
                prevIsNum = true;
                if(i == s.length()-1) list.add(num);
            } else {
                if(prevIsNum) {
                    list.add(num);
                    num = 0;
                    prevIsNum = false;
                }
                if(c == '(') {
                    stack.push(c);
                } else if(c == ')') {
                    while(!stack.isEmpty() && stack.peek() != '('){
                        list.add(stack.pop());
                    }
                    stack.pop();
                } else {
                    while( !stack.isEmpty() && getCode(stack.peek()) >= getCode(c)) {
                        list.add(stack.pop());
                    } 
                    stack.push(c);
                }
            }
        }
        while(!stack.isEmpty()) list.add(stack.pop());
        return list;
    }
    
    public int getCode(char c) {
		switch(c) {
			case '+': return 1;
			case '-': return 1;
			case '*': return 2;
			case '/': return 2;
			default: return 0;
		}
	}
}
