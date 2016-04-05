/*
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
*/

/*
这道题绕来绕去的挺复杂，可以就拿例子一点点想
我开始觉得great太长了，就想eat，然后发现，诶，貌似只要len<=3的怎么组合都ok的
然后有点卡住了，想不出不行的例子。。。
其实abcd bdac就是false的，就不能这么穿插着来
所以其实就是把s1,s2切开，看left(s1) left(s2), right(s1) right(s2)是不是scramble的，如果是，return true
或者left(s1) right(s2), left(s2) right(s1)是不是scramble，是-> return true
如果上面两个都不是，那就return false
但是这样的话呢，其实复杂度就是O(n^4)可以想想dp
*/

public class Solution {
    public boolean isScramble(String s1, String s2) {
        int len = s1.length();
        if(!hasSameChars(s1, s2)) return false;        
        if(len <= 3 || s1.equals(s2)) return true;
        
        for(int i = 1; i < len; i++) {
            if(isScramble(s1.substring(0,i), s2.substring(0,i)) && isScramble(s1.substring(i),s2.substring(i))) return true;
            if(isScramble(s1.substring(0,i), s2.substring(len-i)) && isScramble(s1.substring(i), s2.substring(0,len-i)))
                return true;
        }

        return false;
    }
    
    public boolean hasSameChars(String s1, String s2) {
        int len = s1.length();
        int[] arr = new int[26];
        for(int i = 0; i < len; i++) {
            int index = s1.charAt(i)-'a';
            arr[index] ++;
        }
        for(int i = 0; i < len; i++) {
            int index = s2.charAt(i)-'a';
            arr[index] --;
            if(arr[index] < 0) return false;
        }
        return true;
    }
}

/*
dp的做法，怎么觉得也没好到哪里去呢
*/
public class Solution {
    public boolean isScramble(String s1, String s2) {
        int len = s1.length();
        
        int[] arr = new int[26];
        for(int i = 0; i < len; i++) {
            int index = s1.charAt(i)-'a';
            arr[index] ++;
        }
        for(int i = 0; i < len; i++) {
            int index = s2.charAt(i)-'a';
            arr[index] --;
            if(arr[index] < 0) return false;
        }   
        
        if(len <= 3 || s1.equals(s2)) return true;
        
        boolean[][][] dp = new boolean[len][len][len+1];
        
        for(int k = 1; k <= len; k++) {
            for(int i = 0; i <= len-k; i++) {
                for(int j = 0; j <= len-k; j++) {
                    if(k==1) {
                        dp[i][j][k] = (s1.charAt(i)==s2.charAt(j));
                        continue;
                    }
                    dp[i][j][k] = false;
                    for(int l = 1; l <= k-1; l++) {
                        if((dp[i][j][l] && dp[i+l][j+l][k-l]) || (dp[i][j+k-l][l] && dp[i+l][j][k-l])) {
                            dp[i][j][k] = true;
                            break;
                        }
                    }
                }
            }
        }
        
        return dp[0][0][len];
    }
}
