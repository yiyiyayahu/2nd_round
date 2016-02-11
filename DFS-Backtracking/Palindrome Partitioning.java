/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]
*/

/*
很欣慰的是，再看以前的代码，我发现我确实进步了，现在对于backtracking的思路掌握的还可以，撒花~~~
*/

public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<List<String>>();
        if(s == null || s.length() == 0) return ret;
        
        dfs(s, ret, new ArrayList<String>(), 0);
        return ret;
    }
    
    public void dfs(String s, List<List<String>> ret, List<String> list, int index) {
        if(index == s.length()) {
            ret.add(list);
            return;
        }
        
        int len = s.length();
        for(int i = index+1; i <= len; i++) {
            String s1 = s.substring(index, i);
            if(isPalindrome(s1)) {
                List<String> l = new ArrayList<String>(list);
                l.add(s1);
                dfs(s, ret, l, i);                
            }
        }
    }
    
    public boolean isPalindrome(String s) {
        int len = s.length();
        if(len == 1) return true;
        int start = 0, end = len-1;
        while(start <= end) {
            if(s.charAt(start++) != s.charAt(end--)) return false;
        }
        return true;
    }
}
