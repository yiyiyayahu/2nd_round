/*
 You are given a m x n 2D grid initialized with these three possible values.

    -1 - A wall or an obstacle.
    0 - A gate.
    INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.

Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF

After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
*/

/*
感觉这道题好像google当年的校园面的一道诶。。。想当初，我可是45分钟做两道题的节奏。。。
开始想的是dp，dfs，后来觉得bfs简单诶，只要找到gate，然后把周围的更新，一圈一圈来就可以了

dfs明明是可以的，也很简洁！自己没想出还赖人家算法。。。dfs哭晕在厕所，bfs可以，为啥我不可以
看看人家的简洁的code，我就给跪了
*/
public class Solution {
    private static int[] d = {0,1,0,-1,0};
    
    public void wallsAndGates(int[][] rooms) {
        if(rooms.length == 0) return;
        
        for(int i = 0; i < rooms.length; i++) {
            for(int j = 0; j < rooms[0].length; j++) {
                if(rooms[i][j] == 0) {
                    dfs(rooms, i, j);
                }
            }
        }
    }
    
    public void dfs(int[][] rooms, int i, int j) {
        for(int k = 0; k < 4; k++) {
            int p = i + d[k], q = j + d[k+1];
            if(p>=0 && p<rooms.length && q>=0 && q<rooms[0].length && rooms[p][q] > rooms[i][j]+1) {
                rooms[p][q] = rooms[i][j]+1;
                dfs(rooms, p, q);
            }
        }
    }
}

/*
我觉得下面的bfs做法有问题，现在没有会员了没法儿test。。。但是应该找最小的啊
下面这里的判断if(x-1 >= 0 && rooms[x-1][y] == Integer.MAX_VALUE)只是找INF，可是如果rooms[x-1][y]之前已经被set过一个大的值的话就不对了哇
我觉得应该改为if(x-1 >= 0 && rooms[x-1][y] > rooms[x][y]+1) rooms[x-1][y] = rooms[x][y]+1; (当然rooms[x][y]+1其实就是level)
*/
public class Solution {
    private class Pair {
        int x;
        int y;
        private Pair(int i, int j) {
            this.x = i;
            this.y = j;
        }
    }
    public void wallsAndGates(int[][] rooms) {
        if(rooms.length == 0) return;
        
        int m = rooms.length, n = rooms[0].length;
        Queue<Pair> queue = new LinkedList<>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(rooms[i][j] == 0) {
                    queue.add(new Pair(i,j));
                }
            }
        }
        int level = 0;
        while(!queue.isEmpty()) {
            int len = queue.size();
            level ++;
            for(int i = 0; i < len; i++) {
                Pair p = queue.remove();
                int x = p.x, y = p.y;
                if(x-1 >= 0 && rooms[x-1][y] == Integer.MAX_VALUE) {
                    rooms[x-1][y] = level;
                    queue.add(new Pair(x-1,y));
                }
                if(x+1 < m && rooms[x+1][y] == Integer.MAX_VALUE) {
                    rooms[x+1][y] = level;
                    queue.add(new Pair(x+1,y));
                }
                if(y-1 >= 0 && rooms[x][y-1] == Integer.MAX_VALUE) {
                    rooms[x][y-1] = level;
                    queue.add(new Pair(x,y-1));
                }
                if(y+1 < n && rooms[x][y+1] == Integer.MAX_VALUE) {
                    rooms[x][y+1] = level;
                    queue.add(new Pair(x,y+1));
                }
            } 
        }
    }
}
