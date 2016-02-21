/*
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?
*/


/*
从左向右扫一遍，再从右向左扫一遍
从左往右扫的时候，只要当前的大于之前的，就+1，不然全部设为1
然后从右往左扫的时候再纠正一下
最后算个sum
*/
public class Solution {
    public int candy(int[] ratings) {
        int len = ratings.length;
        if(len == 0) return 0;
        
        int sum = 0;
        int[] dp = new int[len];
        dp[0] = 1;
        for(int i = 1; i < len; i++) {
            if(ratings[i] > ratings[i-1]) {
                dp[i] = dp[i-1] + 1;
            } else {
                dp[i] = 1;
            } 
        }
        sum += dp[len-1];
        for(int i = len-2; i >= 0; i--) {
            if(ratings[i] > ratings[i+1] && dp[i] <= dp[i+1]) dp[i] = dp[i+1] + 1;
            sum += dp[i];
        }
        return sum;
    }
}
