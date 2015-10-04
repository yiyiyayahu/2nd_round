/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.
*/

public class Solution {
    public void solveSudoku(char[][] board) {
        addNumber(board, 0, 0);
    }

    public boolean addNumber(char[][] board, int i, int j) {
    	if(i == board.length) return true;
        if(j == board[0].length) {
            return addNumber(board, i+1, 0);
        } 
        
        if(board[i][j] == '.') {
            for(char c = '1'; c <= '9'; c++) {
                if(isValid(board, i, j, c)) {
                    board[i][j] = c;
                    if(addNumber(board, i, j+1)) return true;
                    else board[i][j] = '.';
                }
            }
        } else {
        	return addNumber(board, i, j+1);
        }
        
        return false;
    }
    public boolean isValid(char[][] board, int i, int j, char value) {
    	for(int y = 0; y < board[0].length; y ++) {
    		if(y != j && board[i][y] == value) {
    			return false;
    		}
    	}
    	
    	for(int x = 0; x < board.length; x ++) {
    		if(x != i && board[x][j] == value) {
    			return false;
    		}
    	}    	
    	
    	for(int x  = i/3*3; x < i/3*3 + 3; x ++) {
    		for(int y = j/3*3; y < j/3*3 + 3; y ++) {
    			if( (x != i || y != j) && board[x][y]==value )
    				return false;
    		}
    	}   	
    	return true;
    }
}
