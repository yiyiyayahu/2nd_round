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
https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/
这道题居然是binary indexed tree，之前不太晓得这个data structure诶，而且同类型的题目有四道，要好好研究一下，看来下面的是很naive的做法
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


/*
用binary indexed tree的话，我只是一层一层的用，所以update和query的时间复杂度变为O(mlogn)，但是从leetcode的时间上来看，并没有改善反而慢了，可能是构造的时间比较久？
*/
public class NumMatrix {
    int[][] tree;
    int[][] matrix;
    
    public NumMatrix(int[][] matrix) {
        if(matrix.length == 0) return;
        int m = matrix.length, n = matrix[0].length;
        this.matrix = matrix;
        tree = new int[m][n+1];
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                updateTree(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        updateTree(row, col, val-matrix[row][col]);
        matrix[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int total = 0;
        for(int i = row1; i <= row2; i++) {
            if(col1 == 0) total += getSum(i, col2);
            else total += getSum(i, col2) - getSum(i, col1-1);
        }
        return total;
    }
    
    private void updateTree(int i, int j, int val) {
        int index = j+1;
        while(index < tree[0].length) {
            tree[i][index] += val;
            index = getNext(index);
        }
    }
    
    private int getSum(int i, int j) {
        int index = j+1;
        int sum = 0;
        while(index > 0) {
            sum += tree[i][index];
            index = getParent(index);
        }
        return sum;
    }
    
    private int getParent(int index) {
        return index - (index&-index);
    }
    
    private int getNext(int index) {
        return index + (index&-index);
    }
}
