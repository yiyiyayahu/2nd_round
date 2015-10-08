/*
 Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]

word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
*/

public class Solution {
    public boolean exist(char[][] board, String word) {
        if(word == null || word.length() == 0) return false;
        boolean[][] checked = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
        	for(int j =0; j < board[0].length; j++) {
        		if(helper(board, i, j, word, 0, checked)) return true;
        	}
        }
        return false;        
    }
    public boolean helper(char[][] board, int i, int j, String word, int index, boolean[][] checked) {
        if(index == word.length()) return true;
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;       
        if(checked[i][j] || board[i][j] != word.charAt(index)) return false;
        checked[i][j] = true;
        index ++;
    	if(helper(board, i-1, j, word, index, checked)) return true;
    	if(helper(board, i+1, j, word, index, checked)) return true;
    	if(helper(board, i, j-1, word, index, checked)) return true;
    	if(helper(board, i, j+1, word, index, checked)) return true;
    	checked[i][j] = false;
    	return false;       
    }   
}
