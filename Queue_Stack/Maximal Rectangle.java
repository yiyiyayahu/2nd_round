/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area. 
*/

/*
这个题同样好难。但是如果一层一层的算，每一层都转化为largest rectangle就还好
复杂度是O(n^2)
*/

public class Solution {
    public int maximalRectangle(char[][] matrix) {
    	if(matrix == null || matrix.length == 0) return 0;
    	int rows = matrix.length, cols = matrix[0].length;
    	int maxx = 0;
    	int[] arr = new int[cols];
    	for(int i = 0; i < rows; i++) {
    		for(int j = 0; j < cols; j++) {
    			if(matrix[i][j] == '1') arr[j]++;
    			else arr[j] = 0;
    		}
    		maxx = Math.max(maxx, largestRectangleArea(arr));
    	}
    	return maxx;
    }
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
