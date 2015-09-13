/*
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. 
The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color 
green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.
*/

/*
改成六个变量来存就好了，space就是O(1) time O(n)
*/
public class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        if(n == 0) return 0;
        int ret = 0;
        
        int r = 0, b = 0, g = 0;
        for(int i = 0; i < n; i++) {
            int rr = r, bb = b, gg = g;
            r = costs[i][0] + Math.min(bb, gg);
            b = costs[i][1] + Math.min(rr, gg);
            g = costs[i][2] + Math.min(rr, bb);
            
        }
        ret = Math.min(Math.min(r,b), g);
        return ret;
    }
}

public class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        if(n == 0) return 0;
        int ret = 0;
        
        int[][] count = new int[n][3];

        for(int i = 0; i < n; i++) {
            count[i][0] = costs[i][0] + (i<1?0:Math.min(count[i-1][1], count[i-1][2]));
            count[i][1] = costs[i][1] + (i<1?0:Math.min(count[i-1][0], count[i-1][2]));
            count[i][2] = costs[i][2] + (i<1?0:Math.min(count[i-1][0], count[i-1][1]));
        }
        ret = Math.min(Math.min(count[n-1][0], count[n-1][1]), count[n-1][2]);
        return ret;
    }
}
