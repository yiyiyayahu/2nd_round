/*
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
*/

/*
先做出O(m+n)的解法
constant space无非就是利用第一行和第一列来执行rows和cols的功能，只是要先把第一列检测一遍
*/
public class Solution {
    public void setZeroes(int[][] matrix) {
        if(matrix.length == 0) return;
        int m = matrix.length, n = matrix[0].length;
        boolean[] rows= new boolean[m];
        boolean[] cols = new boolean[n];
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(rows[i] || cols[j]) 
                    matrix[i][j] = 0;
            }
        }
    }
}
