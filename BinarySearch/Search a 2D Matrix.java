/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

    Integers in each row are sorted from left to right.
    The first integer of each row is greater than the last integer of the previous row.

For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]

*/

public boolean searchMatrix(int[][] matrix, int target) {
    if(matrix == null || matrix.length == 0) return false;
    int m = matrix.length, n = matrix[0].length;

    int start = 0, end = m * n - 1;
    while(start <= end) {
      int mid = (start + end)/2;
      int value = matrix[mid/n][mid%n];
      if(value == target) return true;
      if(value < target) start = mid  + 1;
      else end = mid - 1;
    }
    return false;
}

/*
two binary search的做法
时间复杂度是O(logn+logm)
好像和上面的也没差，上面的是O(log(nm)) = O(logn+logm)
不过下面这种写法很容易就out of range了，注意up的处理
*/
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        
        int up = 0, down = m-1;
        while(up < down) {
            int mid = (up + down)/2;
            if(matrix[mid][0] == target) {
                return true;
            } else if(matrix[mid][0] > target) {
                down = mid-1;
            } else if(matrix[mid][0]<target&&target<matrix[mid+1][0]){
                up = mid;
                break;
            } else {
                up = mid+1;
            }
        }
        
        int left = 0, right = n-1;
        while(left <= right) {
            int mid = (left + right)/2;
            if(matrix[up][mid] == target) {
                return true;
            } else if(matrix[up][mid] < target) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return false;
    }
}
