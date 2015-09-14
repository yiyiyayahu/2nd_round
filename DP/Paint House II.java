/*
There are a row of n houses, each house can be painted with one of the k colors. 
The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, 
costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... 
Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?
*/

/*
如果是O(nk)的复杂度的话，肯定不会像I那么做，那样找每层的min还要k个循环，就一共是O(nk^2)了
这道题比较巧妙的是，因为只要每两个房子的颜色不同就可以了，所以对于每一层的结果，只要track最小值和次小值就足够了
*/
public class Solution {
    public int minCostII(int[][] costs) {
        if(costs.length == 0) return 0;
        int n = costs.length, k = costs[0].length;
        
        int min1 = 0, min2 = 0;
        int[] count = new int[k];
        for(int i = 0; i < n; i++) {
            int tmp1 = min1, tmp2 = min2;

            min1 = Integer.MAX_VALUE;
            min2 = Integer.MAX_VALUE;
            for(int j = 0; j < k; j++) {
                count[j] = (count[j]==tmp1? tmp2 : tmp1) + costs[i][j];
                if(min1 <= count[j]) {
                    min2 = Math.min(count[j], min2);
                } else {
                    min2 = min1;
                    min1 = count[j];
                }
            }
        }
        return min1;
    }
}
