/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
*/

/*
这道题很简单，当时算是我dp的入门题了吧，当时还是CC上面的题
一般判断dp应该就是recursive能做，dp也差不多可以，因为dp就是把中间的值存起来防止重复计算的一种优化
dp的思路：
1. 想好中间变量存的是什么dp[i]表示什么
2. 递推公式是什么，比如dp[i]怎样由之前的值得到
3. 初始值怎么设
我现在的做法是时间复杂度O(n)，空间复杂度O(n)，但是其实是可以优化的
一般dp数组的题可以想想滚动数组
这道题其实只用到前面两个值，是不是只要dp[2]就够了呢
*/

public class Solution {
    public int climbStairs(int n) {
        if(n <= 0) return 0;
        if(n == 1) return 1;
        int[] ways = new int[n+1];
        ways[0] = 0; ways[1] = 1; ways[2] = 2;
        for(int i = 3; i <= n; i++) {
            ways[i] = ways[i-1] + ways[i-2];
        }
        return ways[n];
    }
}
/*
改进算法
*/
public class Solution {
    public int climbStairs(int n) {
        if(n <= 0) return 0;
        if(n == 1) return 1;
        int[] ways = new int[2];
        ways[0] = 1; ways[1] = 2;
        for(int i = 2; i < n; i++) {
            int tmp = ways[0];
            ways[0] = ways[1];
            ways[1] = tmp + ways[1];
        }
        return ways[1];
    }
}
