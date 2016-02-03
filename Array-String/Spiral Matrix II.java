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
