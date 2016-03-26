/*
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. 
You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
*/

/*
这道题好难想喔，一直在想best meeting point那道题的解法
很明显，不一样的啦，这题要用BFS，但是一直没想出来，就一直想找那个点，但是貌似找不出的。。。就只能一点点遍历
思路是这样的：
1. 看到一个building，从它开始bfs，计算周围的等于0的空地到这个building的距离
2. bfs的时候用两个arr，dist[i][j]表示i,j这个点到所有building距离的加和，nums[i][j]表示i,j这个点所能reach到的所有building的数目
 （bfs的过程中呢，记得要用一个boolean array来track有没有visited过，不然很容易就死循环了。。）
所以每次看到一个building遍历整个grid，每个空地都记录了两个值：dist[i][j]加上到新的building的距离，然后nums[i][j]就加上1如果能到这个新的building的话
然后遍历整个grid，找到那个最小的dist[i][j]并且要求nums[i][j]==buildingNo

所以遍历很多次诶！！！如果building的数目是k的话，时间复杂度是O(kmn) 空间复杂度是O(mn)
能不能优化呢~~~ 不过我觉得这个解法还ok啦
*/

public class Solution {
    public int shortestDistance(int[][] grid) {
        if(grid.length == 0) return -1;
        
        int m = grid.length, n = grid[0].length;
        int buildingNo = 0;
        int[][] dist = new int[m][n];
        int[][] nums = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    buildingNo ++;
                    bfs(grid, i, j, dist, nums);
                }
            }
        }
        
        int min = -1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0 && nums[i][j] == buildingNo && dist[i][j] != 0) {
                    if(min == -1 || dist[i][j] < min) min = dist[i][j];
                }
            }
        }
        return min;
    }
    
    private void bfs(int[][] grid, int row, int col, int[][] dist, int[][] nums) {
        int m = grid.length, n = grid[0].length;
        
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{row, col});
        int level = 0;
        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        
        boolean[][] visited = new boolean[m][n];
        while(!queue.isEmpty()) {
            level ++;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] curr = queue.remove();
                
                for(int j = 0; j < directions.length; j++){
                    int x = curr[0] + directions[j][0], y = curr[1] + directions[j][1];
                    if(x < 0 || x >= m || y < 0 || y >= n) continue;
                    if(!visited[x][y] && grid[x][y] == 0) {
                        visited[x][y] = true;
                        dist[x][y] += level;
                        nums[x][y] ++;
                        queue.add(new int[]{x, y});
                    }
                }
            }
        }
    }
}
