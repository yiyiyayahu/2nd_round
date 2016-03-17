/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3
*/

/*
改进了一下union find的解法，受2的启发 - 13ms，还是很弱啊，看来其他的解法会好一点
*/
public class Solution {
    public int numIslands(char[][] grid) {
        if(grid.length == 0) return 0;
        
        int m = grid.length, n = grid[0].length;
        UF uf = new UF(m, n);
        Set<Integer> set = new HashSet<Integer>();
        int num = 0;
        for(int i = 0; i < m; i ++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '1') {
                    num ++;
                    int p = i*n + j;

                    if(i > 0 && grid[i-1][j] == '1' && uf.union(p, p-n)) num--;
                    if(i < m-1 && grid[i+1][j] == '1' && uf.union(p, p+n)) num--;
                    if(j > 0 && grid[i][j-1] == '1' && uf.union(p, p-1)) num--;
                    if(j < n-1 && grid[i][j+1] == '1' && uf.union(p, p+1)) num--;
                }
            }
        }
        
        return num;
    }
    
    class UF {
        int[] id;
        int[] size;
        public UF(int m, int n) {
            id = new int[m * n];
            size = new int[m * n];
            for(int i = 0; i < m * n; i++) {
                id[i] = i;
                size[i] = i;
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
    
        public boolean union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if(pRoot == qRoot) return false;
            if(size[pRoot] <= size[qRoot]) {
                id[pRoot] = qRoot;
                size[qRoot] += size[pRoot];
            } else {
                id[qRoot] = id[pRoot];
                size[pRoot] += size[qRoot];
            }
            return true;
        }
    }
}
/*
还是用的union find
时间一般般 17ms
*/
public class Solution {
    public int numIslands(char[][] grid) {
        if(grid.length == 0) return 0;
        
        int m = grid.length, n = grid[0].length;
        UF uf = new UF(m, n);
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < m; i ++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '1') {
                    int p = i*n + j;

                    if(i > 0 && grid[i-1][j] == '1') uf.union(p, p-n);
                    if(i < m-1 && grid[i+1][j] == '1') uf.union(p, p+n);
                    if(j > 0 && grid[i][j-1] == '1') uf.union(p, p-1);
                    if(j < n-1 && grid[i][j+1] == '1') uf.union(p, p+1);
                }
            }
        }
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '0') continue;
                int root = uf.find(i*n+j);
                if(!set.contains(root)) set.add(root);
            }
        }
        return set.size();
    }
    
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
}
