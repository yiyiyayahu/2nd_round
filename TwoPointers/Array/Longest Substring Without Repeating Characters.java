/*
Given a string, find the length of the longest substring without repeating characters. 
For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. 
For "bbbbb" the longest substring is "b", with the length of 1.
*/

/*
但是下面的做法是iterate两次的，其实原来的做法蛮好的，做一个小小的改进就可以啦
就是与其检测index[c] != 0然后把start到index[c]之间都清空，不如直接看这个是否index[c]>=start，这样就不用清空了

有个小问题就是index也有0的问题，所以一种呢，就是初始化全部为-1 Arrays.fill(index, -1);还有一种就是存index+1这种。但是一定要注意处理喔
嗯呐，就酱
*/
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] index = new int[256];
        int start = 0;
        int maxLen = 0;
        for(int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if(index[c] > start) {
                start = index[c] ;
            }
            index[c] = end+1;
            maxLen = Math.max(maxLen, end-start+1);
        }
        return maxLen;           
    }
}

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] index = new int[256];
        Arrays.fill(index, -1);
        int start = 0;
        int maxLen = 0;
        for(int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if(index[c] >= start) {
                start = index[c] + 1;
            }
            index[c] = end;
            maxLen = Math.max(maxLen, end-start+1);
        }
        return maxLen;           
    }
}
/*
Leetcode上面的解法总是很简洁。。。其实我没有必要存一个index的arr，反正我发现repeated的character，要把之前的start-repeated的位置中间的char对应的值都重置
所以我可以直接用一个boolean arr，然后发现exist[s.charAt(j)]的时候，头指针i一直后移直到exist[s.charAt(j)]=false，同时i也移到了相应的位置
好巧妙
*/
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        boolean[] exist = new boolean[256];
        int i = 0, maxLen = 0;
        
        for(int j = 0; j < s.length(); j++) {
            while(exist[s.charAt(j)]) {
                exist[s.charAt(i)] = false;
                i++;
            }
            exist[s.charAt(j)] = true;
            maxLen = Math.max(j-i+1, maxLen);
        }
        return maxLen;
    }
}

/*
用一个index array来存每个字符出现的index
但是由于初始化都为0， 可以存index+1，或者其实也可以初始化为-1
两个指针分别挪，当发现当前的字符之前出现过，start就移到之前出现的那个index的下一位
要注意，start指针挪的时候，要把之前的字符从index里面清空
*/
public class Solution {
    public int lengthOfLongestSubstring(String s) {
	    int[] index = new int[256];
	    int start = 0, end = 0;
	    int maxLen = 0;
	    while(end < s.length()) {
	        char c = s.charAt(end);
	        if(index[c] == 0) {
	            index[c] = end+1;
	        } else {
	            maxLen = Math.max(maxLen, end-start);		            
	            
	            for(int i = start; i < index[c]-1; i++) {
	            	char ch = s.charAt(i);
	            	index[ch] = 0;
	            }	
	            start = index[c];
	            index[c] = end + 1;
	        }
	        end ++;
	    }
	    maxLen = Math.max(maxLen, end-start);
	    return maxLen;        
    }
}
