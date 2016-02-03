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
        int m = matrix.length;
        if(m == 0) return list;
        int n = matrix[0].length;
        int limit = Math.min(m,n) / 2 - (Math.min(m,n)%2==0?1:0);       
        helper(matrix, 0, list, m, n, limit);
        return list;
    }
    public static void helper(int[][] matrix, int level, List<Integer> list, int m, int n, int limit) {
        if(level > limit) return;
        
        int i = level, j = level;
        for(; j < n-level; j++) {
            list.add(matrix[i][j]);
        }
        i++;j--;
        for(; i < m-level; i++) {
            list.add(matrix[i][j]);
        }
        j--;i--;
        for(; j >= level; j--) {
            if(level == m-level-1) break;
            list.add(matrix[i][j]);
        }
        i--;j++;
        for(; i > level; i--) {
            if(level == n-level-1) break;
            list.add(matrix[i][j]);
        }    
        helper(matrix, level+1, list, m, n, limit);
    }
}
