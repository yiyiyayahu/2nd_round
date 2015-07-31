/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.
*/

/*
这道题的关键就是把infix转成postfix的

算法是：
list用来存最后的结果
stack来存运算符

遇到数字，放到list里面
遇到operator，先check stack里面的，把stack里面所有优先级大于等于它的都pop出来add到list里面去，然后再把当前运算符加上

最后如果stack不是空得话，再把剩余的运算符加到list里面

这样就保证了顺序，好巧妙的做法啊，明天再研究一下，把I做出来
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

                while( !stack.isEmpty() && getCode(stack.peek()) >= getCode(c)) {
                    list.add(stack.pop());
                } 
                stack.push(c);
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
