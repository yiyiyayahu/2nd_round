/*
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, 
you and your friend take turns to flip two consecutive "++" into "--". 
The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.
*/

/*
优化版，防止重复计算
*/
public class Solution {
    public boolean canWin(String s) {
        if(s == null || s.length() <= 1) return false;
        return dfs(s);
    }
    public boolean dfs(String s) {
        StringBuilder sb = new StringBuilder(s);
        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
        
        for(int i = 0; i < s.length()-1; i++) {
            if(s.charAt(i) == s.charAt(i+1) && s.charAt(i) == '+') {
                sb.setCharAt(i, '-');
                sb.setCharAt(i+1, '-');
                String str = sb.toString();
                if(!map.containsKey(str)) {
                    boolean canWin = dfs(str);
                    map.put(str, canWin);
                } 
                if(!map.get(str)) return true;
                
                sb.setCharAt(i, '+');
                sb.setCharAt(i+1, '+');
            }
        }
        return false;
    }
}
/*
唉，好久没做题了，果然就没感觉了
这道题是一道DFS-backtracking的题
开始我naive的在枚举。。。其实就是如果把++换了，看剩下的字符还能不能赢的问题，就是dfs嘛
*/
public class Solution {
    public boolean canWin(String s) {
        if(s == null || s.length() <= 1) return false;
        return dfs(s);
    }
    public boolean dfs(String s) {
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0; i < s.length()-1; i++) {
            if(s.charAt(i) == s.charAt(i+1) && s.charAt(i) == '+') {
                sb.setCharAt(i, '-');
                sb.setCharAt(i+1, '-');
                if(!dfs(sb.toString())) return true;
                sb.setCharAt(i, '+');
                sb.setCharAt(i+1, '+');
            }
        }
        return false;
    }
}
