/*
Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/

/*
这个因为是square的，要考虑的东西就少很多，code也好写一点
*/

public class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int num = 1, max = n * n;
        for(int level = 0; level < n/2; level ++) {
            int i = level, j = level;
            while(j < n-level) matrix[i][j++] = num++;
            i++;j--;
            while(i < n-level) matrix[i++][j] = num++;
            i--;j--;
            while(j >= level) matrix[i][j--] = num++;
            i--;j++;
            while(i > level) matrix[i--][j] = num++;
        }
        if(n%2 == 1) matrix[n/2][n/2] = num;
        return matrix;
    }
}


public class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ret = new int[n][n];
        int start = 1;
        for(int level = 0; level <= (n-1)/2; level++) {
            int i = level, j = level;
            for(int j1 = j; j1 < n-j; j1++) {
                ret[i][j1] = start++;
            }
            for(int i1 = i+1; i1 < n-i; i1++) {
                ret[i1][n-1-j] = start++;
            }
            for(int j1 = n-2-j; j1 >= j; j1--) {
                ret[n-1-i][j1] = start++;
            }
            for(int i1 = n-2-i; i1 > i; i1--) {
                ret[i1][j] = start++;
            }
        }
        return ret;
    }
}
