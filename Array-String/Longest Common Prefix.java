/*
Write a function to find the longest common prefix string amongst an array of strings. 
*/

/*
最好是对于strs[0]的每个char看strs里面其他元素是不是相同，而不是两两比较
因为可能前几个的common prefix比较长
*/
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        if(strs.length == 1) return strs[0];
        StringBuilder common = new StringBuilder();
        for(int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for(int j = 1; j < strs.length; j++) {
                if(i >= strs[j].length() || strs[j].charAt(i) != c) 
                    return common.toString();
            }
            common.append(c);
        }
        return common.toString();       
    }
}
