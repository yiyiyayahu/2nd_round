/*
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.


Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

[[-2,-3,3],
 [-5,-10,1],
 [10,30,-5]]
Notes:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
*/

/*
这道题tag里面有binary search，但是我还是按dp做的诶。。。
开始想错了，从左上角开始往右下角比较难做诶，比如-2,-3,3这条线，开始是需要5的血量，到3的时候又不是很敢直接更新成2，那如果3就是最右边的那个点，返回2+1=3的血量肯定是错的
所以从最右下角往上面做比较简单，发现血量超了不需要那么多就更新为0就好了
*/

public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon.length == 0) return 0;
        
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m][n];
        
        
        for(int i = m-1; i >= 0; i--) {
            for(int j = n-1; j >= 0; j--) {
                int min = 0;
                if(i < m-1 && j < n-1) min = Math.min(dp[i+1][j], dp[i][j+1]);
                else if(i < m-1) min = dp[i+1][j];
                else if(j < n-1) min = dp[i][j+1];
                
                if(dungeon[i][j] - min >= 0) dp[i][j] = 0;
                else dp[i][j] = min - dungeon[i][j];
            }
        }
        return dp[0][0] + 1;
    }
}
