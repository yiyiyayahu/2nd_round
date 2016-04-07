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
唉，还是学艺不精。。。思路是大概知道，但是我code写的好差好乱，思路不清楚
找到一个1，把周围的1都设为0，然后再查一共多少个1就是number of islands

开始是把数组遍历两遍，第一遍置0，第二遍找1的个数，其实完全没必要而且还可能count数错
只要一开始找到一个1的时候，就把周围的都置为0并且count++就可以了
*/

public class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    count ++;
                    dfs(i, j, grid);
                }
            }
        }
        return count;
    }
    
    public void dfs(int i, int j, char[][] grid) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return;
        if(grid[i][j] == '1') {
            grid[i][j] = '0';
            dfs(i-1, j, grid);
            dfs(i+1, j, grid);
            dfs(i, j-1, grid);
            dfs(i, j+1, grid);
        }
    }
}

/*
这次整个思路都是乱掉的。。。
知道是dfs遍历，但是没想好怎么遍历
我的思路是，从0,0开始dfs，遇到一个1的时候total++，然后把周围的都置为2
但是我一直在重复dfs，就是不光遇到一个'1'或者'2'我遍历它周围四个点，遇到'0'我也会遍历周围四个点，所以需要一个boolean[][] visited来track

还有一个问题就是我遇到一个'1' total就++，但是有可能因为我遍历顺序是乱的，即使两个连在一起，还没被置为2的时候我就重复算了一下
*/
