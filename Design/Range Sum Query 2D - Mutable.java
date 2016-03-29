/*
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:

Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10

Note:

    The matrix is only modifiable by the update function.
    You may assume the number of calls to update and sumRegion function is distributed evenly.
    You may assume that row1 ≤ row2 and col1 ≤ col2.
*/

/*
题目倒是不难。。。
不过开始还是想复杂了，最开始觉得sum[i][j]是从(0,0)-(i,j)的和，其实没必要啦，只要每一行都maintain一个当前行的sum就可以了
还有，我觉得很奇怪诶。。就是有可能input是invalid，那constructor里面也可以直接return的？
*/
public class NumMatrix {
    int[][] sum;
    int[][] matrix;
    public NumMatrix(int[][] matrix) {
        if(matrix.length == 0) return;
        
        int m = matrix.length, n = matrix[0].length;
        this.matrix = matrix;
        sum = new int[m][n];
        for(int i = 0; i < m; i++) {
            sum[i][0] = matrix[i][0];
            for(int j = 1; j < n; j++) {
                sum[i][j] = sum[i][j-1] + matrix[i][j];
            }
        }
    }

    public void update(int row, int col, int val) {
        int orig = matrix[row][col];
        int diff = val - orig;
        
        for(int j = col; j < sum[0].length; j++) {
            sum[row][j] += diff;
        }
        matrix[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int total = 0;
        for(int i = row1; i <= row2; i++) {
            if(col1 == 0) total += sum[i][col2];
            else total += sum[i][col2] - sum[i][col1-1];
        }
        return total;
    }
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);
