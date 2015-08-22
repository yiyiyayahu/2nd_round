/*
有点二了。。。这个很明显是DP，开始居然没想出来，因为想的是要存word对应true/false
实际上只要存当前index之前的substring是不是就可以了呀
也就是dp[i]代表wordBreak(s.substring(0,i+1), wordDict)
那这样就好办了
dp[i]是true的条件是：
1) dp[i]本来就在wordDict里面
2) 在(0,i)中存在这样一个j -> d[j]是true，并且j+1到i的substring在wordDict里面
程序就比较好写了。但是拼写错误神马的要注意：开始把wordDict写成dict了。。。
*/
public class Solution {
    public boolean wordBreak(String s, Set<String> wordDict) {
        if(s == null || s.length() == 0) return false;

        int len = s.length();
        boolean[] dp = new boolean[len];
        
        dp[0] = false;
        for(int i = 0; i < s.length(); i++) {
            String sub1 = s.substring(0,i+1);
            if(wordDict.contains(sub1)) {
                dp[i] = true;
                continue;
            }
            for(int j = 0; j < i; j++) {
                if(dp[j]==true) {
                    String sub2 = s.substring(j+1, i+1);
                    if(wordDict.contains(sub2)) dp[i] = true;
                }
            }
        }
        return dp[len-1];
    }
}
