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

/*
DFS很容易出现stack overflow，只有加上剪枝才能过test case，然后下面的是一种剪枝方法
我觉得实际面试的时候应该不会那么严格
the first time i wrote this code, i got stack overflow error. 
because the base case i used was if (row < 0 || row >= board.length || ...) return, 
now row > 1, the recursion stops when row == 1, not row < 0 anymore, thus we can save 2 levels of recursive calls, 
that is when row == 0 and row == 1, we can prevent the stack frame of jvm from going deeper for 2 more levels, so the stack overflow error can be prevented. 
(this is actually a corner case -- you can also prevent the stack overflow error by adjusting the "max-recursion-levels-allowed" parameter of the jvm.)
*/

public class Solution {
    public void solve(char[][] board) {
        if(board.length == 0) return;
        int m = board.length, n = board[0].length;
        //flip all O on the edges and the adjacent O to #
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 || i == m-1 || j == 0 || j == n-1) {
                    if(board[i][j] == 'O') dfs(board, i, j, '#');
                }
            }
        }
        //flip back # to O
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O') board[i][j] = 'X';
                if(board[i][j] == '#') board[i][j] = 'O';
            }
        }
    }

    public void dfs(char[][] board, int i, int j, char replace) {
        int m = board.length, n = board[0].length;
        if(i < 0 || i >= m || j < 0 || j >= n) return;

        if(board[i][j] == 'O') {
            board[i][j] = replace;
            if(i > 1) dfs(board, i-1, j, replace);
            if(i < m-2) dfs(board, i+1, j, replace);
            if(j > 1) dfs(board, i, j-1, replace);
            if(j < n-2) dfs(board, i, j+1, replace);
        }
    }
}

/*
错误代码！！！这个解法在输入很大很长的时候会出现stack overflow。。。
*/
public class Solution {
    public void solve(char[][] board) {
        if(board.length == 0) return;
        int m = board.length, n = board[0].length;
        //flip all O on the edges and the adjacent O to #
        for(int i = 0; i < m; i++) {
            if(board[i][0] == 'O') dfs(board, i, 0, '#');
            if(board[i][n-1] == 'O') dfs(board, i, n-1, '#');
        }
        for(int j = 0; j < n; j++) {
            if(board[0][j] == 'O') dfs(board, 0, j, '#');
            if(board[m-1][j] == 'O') dfs(board, m-1, j, '#');
        }
        //flip back # to O
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O') board[i][j] = 'X';
                if(board[i][j] == '#') board[i][j] = 'O';
            }
        }
    }

    public void dfs(char[][] board, int i, int j, char replace) {
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        int[] index = {-1,0,1,0,-1};
        if(board[i][j] == 'O') {
            board[i][j] = replace;
            dfs(board, i-1, j, replace);
            dfs(board, i+1, j, replace);
            dfs(board, i, j-1, replace);
            dfs(board, i, j+1, replace);
        }
    }
}
/*
下面这个union find的解法其实复杂度并不好，只是最近在学习union find
做法是这样的：
1. 先遍历一遍board，把所有connected 'O' -> root
2. 遍历一遍边界，把所有边界的'O'的root放到一个set里面
3. 最后遍历一遍board，把所有不是和边界'O'同一个root的'O'设为'X'
*/
public class Solution {
    class UF {
        int[] id;
    
        public UF(int m, int n) {
            id = new int[m * n];
            for(int i = 0; i < m * n; i++) {
                id[i] = i;
            }
        }
    
        public int find(int p) {
            while(p != id[p]) {
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
        }
    
        public boolean isConnected(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            return pRoot == qRoot;
        }
    
        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            id[pRoot] = qRoot;
        }
    }
    
    public void solve(char[][] board) {
        if(board.length == 0) return;
        int m = board.length, n = board[0].length;
        UF uf = new UF(m, n);
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O') {
                    int p = i*n + j;
                    if(i > 0 && board[i-1][j] == 'O') uf.union(p, p-n);
                    if(i < m-1 && board[i+1][j] == 'O') uf.union(p, p+n);
                    if(j > 0 && board[i][j-1] == 'O') uf.union(p, p-1);
                    if(j < n-1 && board[i][j+1] == 'O') uf.union(p, p+1);
                }
            }
        }
        
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < m; i++) {
            int p = uf.find(i*n);
            if(!set.contains(p)) set.add(p);
            int q = uf.find(i*n + n-1);
            if(!set.contains(q)) set.add(q);
        }
        for(int j = 0; j < n; j++) {
            int p = uf.find(j);
            if(!set.contains(p)) set.add(p);
            int q = uf.find((m-1)*n+j);
            if(!set.contains(q)) set.add(q);
        }
        
        for(int i = 0; i < m; i ++) {
            for(int j = 0; j < n; j++) {
                int p = i*n+j;
                if(board[i][j] == 'O' && !set.contains(uf.find(p)) ) {
                    board[i][j] = 'X';
                } 
            }
        }
    }
}
