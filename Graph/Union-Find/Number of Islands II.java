/*
A 2d grid map of m rows and n columns is initially filled with water. 
We may perform an addLand operation which turns the water at position (row, col) into a land. 
Given a list of positions to operate, count the number of islands after each addLand operation.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]

Challenge:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
*/

/*
真心觉得union find的方法好神奇啊
这道题我觉得我的时间复杂度满足需求了，22ms beat了65%的人
就一点点用union find
开始想的是，每次上下左右扫一遍，如果有1就和前面一样，没有就+1不就好了。但是我忘了一点，比如上面例子[1,1]的加入可以让三个island合为一个

然后union我知道怎么做了，比较愁的是怎么find一共多少个connected components，每次都要扫一遍么。。。太费时间了哇
其实只要一个小小的modification就可以了，union加一个返回值，如果发现，诶，p和q本来就在一个component里面，不用union，那就返回false，不然就返回true
所以每次union返回true的时候都说明又合并了一个island，num--就可以了
四周都扫过之后的num就是最后的值，直接加到list里面就ok了。好棒啊
*/
public class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> list = new ArrayList<>();
        
        if(m == 0 && n == 0) return list;
        UF uf = new UF(m, n);
        int[][] grid = new int[m][n];
        int num = 0;
        for(int k = 0; k < positions.length; k++) {
            int i = positions[k][0], j = positions[k][1];
            grid[i][j] = 1;
            num ++;
            int p = i*n+j;
            if(i > 0 && grid[i-1][j] == 1 && uf.union(p, p-n)) num--;
            if(i < m-1 && grid[i+1][j] == 1 && uf.union(p, p+n)) num--;
            if(j > 0 && grid[i][j-1] == 1 && uf.union(p, p-1)) num--;
            if(j < n-1 && grid[i][j+1] == 1 && uf.union(p, p+1)) num--;
            list.add(num);
        }
        return list;
    }
    
    class UF {
        int[] id;
        int[] size;
        public UF(int m, int n) {
            id = new int[m*n];
            size = new int[m*n];
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
