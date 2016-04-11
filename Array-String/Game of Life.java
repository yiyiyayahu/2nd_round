/*
 According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors 
(horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

    Any live cell with fewer than two live neighbors dies, as if caused by under-population.
    Any live cell with two or three live neighbors lives on to the next generation.
    Any live cell with more than three live neighbors dies, as if by over-population..
    Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Write a function to compute the next state (after one update) of the board given its current state.

Follow up:

    Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first 
    and then use their updated values to update other cells.
    In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems 
    when the active area encroaches the border of the array. How would you address these problems?

*/

/*
这道题倒是不难，无非就是不能一边遍历一边update，我就存下来next state然后再赋值好啦，最简单粗暴的解法。。。
怎么优化呢，就是怎么inplace呢。。。
而且完全不是dfs，只是遍历这个array的每个值就可以了
*/
public class Solution {
    public void gameOfLife(int[][] board) {
        if(board.length == 0) return;
        int m = board.length, n = board[0].length;

        int[][] next = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                next[i][j] = -1;
            }
        }       
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                dfs(board, i, j, next);
            }
        }     
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                board[i][j] = next[i][j];
            }
        }    
    }

    public void dfs(int[][] board, int i, int j, int[][] next) {
        if(next[i][j] != -1) return;
        int numOfNeighs = 0;
        for(int p = -1; p <= 1; p++) {
            for(int q = -1; q <= 1; q ++) {
                int x = i + p;
                int y = j + q;
                if(p == 0 && q == 0) continue;
                if(x < 0 || x >= board.length || y < 0 || y >= board[0].length) continue;
                if(board[x][y] == 1) numOfNeighs ++;
            }
        }  
        if(numOfNeighs < 2 || numOfNeighs > 3) {
            next[i][j] = 0;
        } else {
            if(board[i][j] == 1) {
                next[i][j] = 1;
            } else {
                if(numOfNeighs == 3) next[i][j] = 1;
                else next[i][j] = 0;
            }
        }
    }
}
