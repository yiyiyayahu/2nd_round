/*
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated. 
*/

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[] check = new boolean[9];
    
        int rows = board.length, cols = board[0].length;
    
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(board[i][j] == '.') continue;
                int curr = board[i][j] - '1';
                if(check[curr]) return false;
                else check[curr] = true;
            }
            check = new boolean[9];
        }
        for(int j = 0; j < cols; j++) {
            for(int i = 0; i < rows; i++) {
                if(board[i][j] == '.') continue;
                int curr = board[i][j] - '1';
                if(check[curr]) return false;
                else check[curr] = true;
            }
            check = new boolean[9];
        }
    
        for(int i = 0; i < rows; i+=3) {
            for(int j = 0; j < cols; j+=3) {
                for(int ii = i; ii < i+3; ii ++) {
                    for(int jj = j; jj < j + 3; jj++) {
                        if(board[ii][jj] == '.') continue;
                        int curr = board[ii][jj] - '1';
                        if(check[curr]) return false;
                        else check[curr] = true;
                    }
                }
                check = new boolean[9];
            }
        }
    
        return true;             
    }
}
