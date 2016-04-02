/*
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. 
You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. 
Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
*/


/*
这个dp觉得好难想啊
我觉得dp最好的方法就是draw a grid, fill in the number
dp的思路呢就是先想找dp这个arr是一维还是二维的，然后想递推是，最后想初始条件
那这道题呢，显然是二维数组，dp[i][j]表示[i,j)的maxCoints (not including j)

那就以题目中的例子[3,1,5,8]，这样的话grid应该存这些：
    0    1     2      3       4
0   *   [3]  [3,1] [3,1,5] [3,1,5,8]
1   *    *    [1]   [1,5]   [1,5,8]
2   *    *     *     [5]     [5,8]
3   *    *     *      *       [8]
4   *    *     *      *        *

发现我想要的结果就是dp[0][4]
那如何递归呢
[3,1,5,8] burst 3: dp[0][0] + 1*3*1 + dp[1][4]
          burst 1: dp[0][1] + 3*1*5 + dp[2][4]
          burst 5: dp[0][2] + 1*5*8 + dp[3][4]
          burst 8: dp[0][3] + 5*8*1 + dp[4][4]
但是呢，不能按照平常的顺序那么一行一行推。。。因为dp想来是从小推到大，所以就一个col一个col算，然后每个col从下往上算
比如j=1, 算dp[0,1]就ok了
j=2 -> dp[1,2] dp[0,2]
j=3 -> dp[2,3] dp[1,3] dp[0,3]
...
*/
public class Solution {
    public int maxCoins(int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;
        
        int[][] dp = new int[len+1][len+1];
        
        for(int j = 1; j <= len; j++) {
            for(int i = j-1; i >= 0; i--) {
                int max = 0;
                for(int k = i; k < j; k++) {
                    max = Math.max(max, dp[i][k]+dp[k+1][j]+(i==0?1:nums[i-1])*nums[k]*(j==len?1:nums[j]));
                }
                dp[i][j] = max;
            }
        }
        return dp[0][len];
    }
}
/*
这个解法TLE了。。。看来要用dp
*/
public class Solution {
    public int maxCoins(int[] nums) {
        int[] max = new int[1];
        Integer curr = new Integer(0);
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
      
        dfs(list, max, curr);
        return max[0];
    }
    public void dfs(LinkedList<Integer> list, int[] max, Integer curr) {
        if(list.size() == 0) {
        	max[0] = Math.max(max[0], curr);
            return;
        }
        for(int i = 0; i < list.size(); i++) {
            LinkedList<Integer> tmp = new LinkedList<>(list);
            int m = (i==0?1:tmp.get(i-1)) * tmp.get(i) * (i==list.size()-1?1:tmp.get(i+1));
            curr += m;
            tmp.remove(i);
            dfs(tmp, max, curr);
            curr -= m;
        }
    }
}
