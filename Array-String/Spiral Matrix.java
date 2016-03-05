/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
*/

/*
这道题出了好多错。。。
1. matrix.length==0的时候return list; []
2. [2,3]只有一行的时候我还会调回来遍历下2.。。。{2,3,2}
然后为了改第2个，我经常出现少遍历的情况。。。
3. (Math.min(m,n)%2==0?1:0)这种运算符优先级很低的，要加括号。。。
这道题再好好做做。。。。
*/

public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        if(matrix.length == 0) return list;
        
        int m = matrix.length, n = matrix[0].length;
        int limit = Math.min(m,n)/2;
        if(Math.min(m,n)%2==0) limit--;
        int level = 0;
        
        while(level <= limit) {
            int i = level, j = level;
            while(j < n-level) list.add(matrix[i][j++]);
            j--;i++;
            while(i < m-level) list.add(matrix[i++][j]);
            i--;j--;
            while(j >= level) {
                if(level == m-level-1) break;
                list.add(matrix[i][j--]);
            }
            j++;i--;
            while(i > level) {
                if(level == n-level-1) break;
                list.add(matrix[i--][j]);
            }
            level ++;
        }
        return list;
    }
}


public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        if(matrix.length == 0) return list;
        
        int m = matrix.length, n = matrix[0].length;
        int limit = (Math.min(m,n)-1)/2;
        
        for(int level = 0; level <= limit; level ++) {
            int i = level, j = level;
            
            for(int j1 = j; j1 < n-j; j1++) {
                list.add(matrix[i][j1]);
            }
            for(int i1 = i+1; i1 < m-i; i1++) {
                list.add(matrix[i1][n-1-j]);
            }
            for(int j1 = n-2-j; j1 >= j && m-1-i != i; j1--) {
                list.add(matrix[m-1-i][j1]);
            }
            for(int i1 = m-2-i; i1 > i && n-1-j != j; i1--) {
                list.add(matrix[i1][j]);
            }
        }
        return list;
    }
}
