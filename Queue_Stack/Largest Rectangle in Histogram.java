/*
 Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, 
 find the area of largest rectangle in the histogram.

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10. 
*/

/*
我比较习惯这种写法。。。
*/
public class Solution {
    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0) return 0;
        
        int len = heights.length;
        Stack<Integer> stack = new Stack<>();
        int largest = 0;
        for(int i = 0; i < len; i++) {
            while(!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int tmp = heights[stack.pop()] * (stack.isEmpty()? i : i-stack.peek()-1);
                largest = Math.max(largest, tmp);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()) {
            int tmp = heights[stack.pop()] * (stack.isEmpty() ? len : len-stack.peek()-1);
            largest = Math.max(largest, tmp);
        }
        return largest;
    }
}

/*
这道题好难的说。。。
就是用一个stack maintain一个递增的序列，stack里面存index
如果比peek大就push进去，不然就把里面大的一点点pop出来同时计算面积
如果stack是空，说明当前值是最小的，面积就是当前的height乘以自己的index
不然的话，就是stack的peek对应的height乘以当前index与stack peek的距离-1

trick就是把height copy到h数组，h数组多一位存0，这样当数组遍历完，stack里面还有值的情况也给处理掉了
*/
public class Solution {
    public int largestRectangleArea(int[] height) {
        if(height == null) return 0;
        int[] h = new int[height.length + 1];
        h = Arrays.copyOf(height, height.length + 1);

        Stack<Integer> stack = new Stack<Integer>();
        int largest = 0;
        for(int i = 0; i < h.length; ) {
            if(stack.isEmpty() || h[i] >= h[stack.peek()]) {
                stack.push(i++);
            } else {           
                int index = stack.pop();
                int tmp = h[index] * (stack.isEmpty()? i : i-stack.peek()-1);
                largest = Math.max(largest, tmp);
            }
        }
        return largest;
    }
}
