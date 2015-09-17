/*
 Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3. 
*/
/*
人家这个滑动窗口比我写的好多了
cursor是前一个字符的最后那个位置
*/
public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s == null || s.length() == 0) return 0;
        
    	int start = 0, end = 1, cursor = -1;
    	int maxLen = 0;
    	for(; end < s.length();end++) {
    		if(s.charAt(end) == s.charAt(end-1)) continue;
    		if(cursor >= 0 && s.charAt(cursor) != s.charAt(end)) {
    			maxLen = Math.max(maxLen, end-start);
    			start = cursor+1;
    		}
    		cursor = end-1;
    	}
    	maxLen = Math.max(maxLen, end-start);
    	return maxLen;  
    }
}

public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int start = 0, end = 0;
        int maxLen = 0;
        while(end < s.length()) {
            char c = s.charAt(end);
            if(map.size() < 2 || map.containsKey(c) ) {
                map.put(c, end);
            } else {
                maxLen = Math.max(maxLen, end - start);
                for(int i = end-1; i>=0; i--){
                	if(s.charAt(i) != s.charAt(end-1)) break;
                	else start=i;
                }
                map = new HashMap<Character, Integer>();
                map.put(s.charAt(start), start);
                map.put(c, end);
            }
            end ++;
        }
        maxLen = Math.max(maxLen, end - start);
        return maxLen;
    }
}
