/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
*/

/*
如果只用一个int min来track最小值显然是不够的，因为它可能被pop走
所以用一个minstack就可以了，思路还挺简单地
问题是这里minStack.peek().equals(stack.peek())一定要用equals不能用==
因为stack里面的是Integer而不是int，如果把minStack.peek()和stack.peek()分别赋给两个int，直接==还是可以的
*/
class MinStack {
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();
    
    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty() || minStack.peek() >= x) minStack.push(x);
    }

    public void pop() {
        if(!minStack.isEmpty() && minStack.peek().equals(stack.peek())) minStack.pop();
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
