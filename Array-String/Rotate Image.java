/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
*/

/*
这道题开始看cc150的时候看不太懂，现在总算是不怕它了
但是我总是有点小问题
貌似和上次犯了同样的错误，总之这个循环要注意一下
其实我是一圈一圈的rotate的，就是先最外圈，再里面一圈，所以i从0到n，j从i到n-1-i，而不是从0开始
这次写的和第一次刷的code是不一样的，我觉得这样想比较好
*/
public class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if(n <= 1) return;
        
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n-1-i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = tmp;
            }
        }
    }
}
