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

/*
还有一种很巧妙的方法
sign: 遇到的符号，对下一个num有效
num： 当前遇到的数字
看到(: 把之前的ret和sign存起来，然后ret，sign重置，有点像开始了一轮新的计算，stack里面存的是连接
看到): 记得要先ret+=sign*num
1+2-(4+5)
ret=1 -> sign=1 -> ret+=sign*num ret=3 -> stack存-1，3
之后计算括号里面的，+: ret=4 -> ): ret+=5 ret=9 ret=ret*preSign+prevSet=9*(-1)+3=-6
*/
public class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0, sign = 1;
        int ret = 0;
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                num = num*10 + (c-'0');
            } else if(c == '+') {
                ret += sign * num;
                sign = 1;
                num = 0;
            } else if(c == '-') {
                ret += sign * num;
                sign = -1;
                num = 0;
            } else if(c == '(') {
                stack.push(ret);
                stack.push(sign);
                sign = 1;
                ret = 0;
            } else if(c == ')'){
            	ret += sign * num;
            	num = 0;
                int prevSign = stack.pop();
                int prevRet = stack.pop();
                ret = ret * prevSign + prevRet;
            }
        }
        if(num != 0) ret += sign * num;
        return ret;
    }
}
