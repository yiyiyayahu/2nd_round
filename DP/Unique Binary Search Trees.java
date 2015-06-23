/*
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

/*
这道题我是这样想的:
1.dp[i]就是i能组成多少unique BST，
2.那接下来的问题就是递推公式
比如1，2，3：1为root的时候，2，3能组成多少个f(0)*f(2)，同理，2为root的时候，1和3分别能组成多少个f(1)*f(1), 3为root，f(2)*f(0)
所以dp就是dp[i] = sum(dp[j]*dp[i-1-j]) (0=<j<i)
3.初始化dp[0]=1,dp[1]=1

时间复杂度是O(n^2)，时间上每次求解i个结点的二叉查找树数量的需要一个i步的循环，比如求解3的时候要循环3次-> O(1+2+...+n)=O(n^2)
空间复杂度O(n)
这道题的模型正好是卡特兰数的定义。当然这道题还可以用卡特兰数的通项公式来求解，这样时间复杂度就可以降低到O(n)
*/
public class Solution {
    public int numTrees(int n) {
        if(n <= 0) return 0;
        if(n == 1) return 1;
        
        int[] nums = new int[n+1];
        nums[0] = 1; nums[1] = 1;
        for(int i = 2; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                nums[i] += nums[j] * nums[i-j-1];
            }
        }
        return nums[n];
    }
}
