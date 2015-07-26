/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
*/

/*
每次排除掉四分之一块
时间复杂度是:
T(n) = 3T(n/2) + c
O(n^(log3))
还有更好的解法
http://articles.leetcode.com/2010/10/searching-2d-sorted-matrix-part-ii.html

开始没想到怎么排除掉四分之一，后来发现可以每块的结果或一下，调用三次
要注意：
1. if(left == right && up == down) return matrix[left][up] == target; 
都相等的时候要处理，不然就死循环 因为binary search的指针必须移动，如果只有一个元素，指针不移动
2. 开始我犯的错误是left+rmid...但是rmid本身就是当前点的坐标啊，如果再left加一下就超长度了
*/
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        return helper(matrix, target, 0, 0, matrix.length-1, matrix[0].length-1);
    }
    
    public boolean helper(int[][] matrix, int target, int left, int up, int right, int down) {
        if(left > right || up > down) return false;
        if(left == right && up == down) return matrix[left][up] == target;
        int rmid = (left + right)/2;
        int cmid = (up + down)/2;
        if(target == matrix[rmid][cmid]) return true;
        if(target < matrix[rmid][cmid]) {
            return helper(matrix, target, left, up, rmid, cmid) ||
                helper(matrix, target, left, cmid+1, rmid, down) ||
                helper(matrix, target, rmid+1, up, right, cmid);
        } else {
            return helper(matrix, target, left, cmid+1, rmid, down) ||
                helper(matrix, target, rmid+1, up, right, cmid) ||
                helper(matrix, target, rmid+1, cmid+1, right, down);
        }
    }
}
