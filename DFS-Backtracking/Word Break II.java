/*
 Given a string s and a dictionary of words dict, 
 add spaces in s to construct a sentence where each word is a valid dictionary word.
Return all such possible sentences.
For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].
A solution is ["cats and dog", "cat sand dog"]. 
*/

/*
这道题其实是DFS加剪枝，剪枝的时候要用到DP （word break I主要就是DP）
如果直接DFS的话有很多重复计算，会TLE的
剪枝用DP，notFound[i]表示在(i+1,n)的区间里找不到可能的分解方法，如果是true的话，找到i就可以停止不用找下去了
helper里面就是从当前开始的index往后找，找到一个放到sb里面，接着从当前的i+1开始再调用helper函数
*/
public class Solution {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> list = new ArrayList<String>();
        boolean[] notFound = new boolean[s.length()];
        helper(s, 0, wordDict, new StringBuilder(), notFound, list);
        return list;
    }
    public void helper(String s, int index, Set<String> wordDict, StringBuilder sb, boolean[] notFound, List<String> list) {
        if(index == s.length()) {
            sb.deleteCharAt(sb.length() - 1);
            list.add(sb.toString());
            return;
        }
        for(int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i+1);
            StringBuilder tmp = new StringBuilder(sb);
            if(wordDict.contains(sub) && !notFound[i]) {
                sb.append(sub).append(" ");
                int presize = list.size();
                helper(s, i+1, wordDict, sb, notFound, list);
                if(list.size() == presize) notFound[i] = true;
                sb = tmp;
            }
        }
    }
}
