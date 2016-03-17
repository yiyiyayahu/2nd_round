/*
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
*/

/*
开始没懂什么意思。。。
其实这道题是这样的，一定要被包围的O才可以flip，也就是说如果some O与border上面的O想连，那就不算surrounded，这些O就都不被flip
BFS的做法呢，就是一圈一圈的，先把最外层的O找出来，放到queue里面，然后先把它们标记成#就是不flip的O
最后呢，就把还是O的flip成X，把标记成#的还原到O
*/

public class Solution {
    class Pair {
        int i;
        int j;
        private Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    
    public void solve(char[][] board) {
        if(board.length == 0) return;
        
        int m = board.length, n = board[0].length;
        Queue<Pair> queue = new LinkedList<Pair>();
        
        //add all 'O's of four boards into queue
        for(int i = 0; i < m; i++) {
            if(board[i][0] == 'O') queue.add(new Pair(i,0));
            if(board[i][n-1] == 'O') queue.add(new Pair(i,n-1));
        }
        
        for(int j = 1; j < n-1; j++) {
            if(board[0][j] == 'O') queue.add(new Pair(0,j));
            if(board[m-1][j] == 'O') queue.add(new Pair(m-1,j));
        }
        
        while(!queue.isEmpty()) {
            Pair p = queue.remove();
            int x = p.i, y = p.j;
            board[x][y] = '#';
            if(x > 0 && board[x-1][y] == 'O') queue.add(new Pair(x-1, y));
            if(x < m-1 && board[x+1][y] == 'O') queue.add(new Pair(x+1, y));
            if(y > 0 && board[x][y-1] == 'O') queue.add(new Pair(x, y-1));
            if(y < n-1 && board[x][y+1] == 'O') queue.add(new Pair(x, y+1));
        }
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O') board[i][j] = 'X';
                if(board[i][j] == '#') board[i][j] = 'O';
            }
        }
    }
}
